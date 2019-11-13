/*
 * Copyright 2018-2019 Niels Gron and Contributors All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package quicksilver.webapp.simpleserver.controllers.root.components.charts;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.function.DoubleFunction;
import java.util.stream.Collectors;
import quicksilver.commons.data.TSDataSetFactory;
import quicksilver.webapp.simpleserver.controllers.root.components.tables.TableData;
import quicksilver.webapp.simpleui.bootstrap4.charts.TSFigurePanel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSCard;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.quick.QuickBodyPanel;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import tech.tablesaw.aggregate.AggregateFunction;
import tech.tablesaw.aggregate.AggregateFunctions;
import tech.tablesaw.api.ColumnType;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.columns.Column;
import tech.tablesaw.columns.numbers.DoubleColumnType;
import tech.tablesaw.columns.strings.StringColumnType;
import tech.tablesaw.plotly.event.EventHandler;

public class ChartsTreemap extends AbstractComponentsChartsPage {

    public ChartsTreemap() {
        super();
        toolbar.setActiveButton("Treemap");
    }

    public static Table loadLargeStocks() throws IOException {
        Table treemapTable;

        InputStream inputStream = TableData.class.getResourceAsStream("company-stocks.csv");
        treemapTable = Table.read().csv(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        //treemapTable = treemapTable.sampleN(100);

        DoubleColumn marketCapColumn = (DoubleColumn)treemapTable.column("MarketCap");
        treemapTable = treemapTable.where(marketCapColumn.isGreaterThan(75000)); // Greater then 75 Billion market cap (113 rows)

        System.out.println("Stocks Table Row Count: " + treemapTable.rowCount());

        //XXX: See https://github.com/jtablesaw/tablesaw/pull/703 ,until then convert % by hand to numbers
        StringColumn change = (StringColumn) treemapTable.column("Change");
        Column numericChange = DoubleColumn.create("ChangeAsNumber");
        //apparently mapInto doesn't append, but replace. Need to grow it to size()
        numericChange = numericChange.emptyCopy(change.size());

        change.mapInto((String p) -> {
            double percFactor = 1.0;
            if (p.endsWith("%")) {
                percFactor = 100.0;
                p = p.substring(0, p.length() - 1);
            }
            try {
                return Double.parseDouble(p) / percFactor;
            } catch (NumberFormatException nfe) {
                return DoubleColumnType.missingValueIndicator();
            }

        }, numericChange);

        treemapTable.addColumns(numericChange);

        //System.out.println(treemapTable.structure());
        return unique(aggregate(treemapTable), "ids");
    }

    static AggregateFunction<StringColumn, String> firstString = new AggregateFunction<StringColumn, String>("First") {
        @Override
        public String summarize(StringColumn v) {
            return v.isEmpty() ? StringColumnType.missingValueIndicator() : v.get(0);
        }

        @Override
        public boolean isCompatibleColumn(ColumnType type) {
            return type == returnType();
        }

        @Override
        public ColumnType returnType() {
            return ColumnType.STRING;
        }
    };

    public static Table unique(Table treemapTable, String idCol) {
        List<Column<?>> columns = treemapTable.columns().stream().filter(c -> !idCol.equals(c.name())).collect(Collectors.toList());
        List<String> columnNames = columns.stream().map(Column::name).collect(Collectors.toList());
        //System.out.println(columnNames);
        treemapTable = treemapTable.summarize(columnNames, AggregateFunctions.first, firstString).by(idCol);
        treemapTable.columns().forEach(c -> {
            if (c.name().startsWith("First [")) {
                String newName = c.name().substring("First [".length());
                if (newName.endsWith("]")) {
                    newName = newName.substring(0, newName.length() - 1);
                }
                c.setName(newName);
            }
        });

        return treemapTable;
    }

    public static Table aggregate(Table treemapTable) {

        Table view = treemapTable.select("Ticker", "Industry", "Sector", "Change", "ChangeAsNumber", "MarketCap");

        //create IDs for each ticker/industry/sector
        StringColumn tickerIdColumn = view.stringColumn("Ticker").map((t) -> {
            return "t-" + t;
        });
        tickerIdColumn.setName("ids");
        StringColumn industryIdColumn = view.stringColumn("Industry").map((t) -> {
            return "i-" + t;
        });
        industryIdColumn.setName("ids");
        StringColumn sectorIdColumn = view.stringColumn("Sector").map((t) -> {
            return "s-" + t;
        });
        sectorIdColumn.setName("ids");

        //create views
        Table tickerIndustryView = view.select("Ticker", "Change", "ChangeAsNumber", "MarketCap");
        tickerIndustryView.addColumns(tickerIdColumn,
                industryIdColumn.copy().setName("Parent"));
        tickerIndustryView.stringColumn("Ticker").setName("Label");
        // --> [ids, Label (ie. Ticker), Parent (ie. Industry ids), Change, ChangeAsNumber, MarketCap]

        //aggregate Industry MarketCap and ChangeAsNumber
        Table industryAggregation = tickerIndustryView.summarize(Arrays.asList("MarketCap", "ChangeAsNumber"),
                AggregateFunctions.sum, AggregateFunctions.mean).by("Parent");
        industryAggregation.column("Parent").setName("ids");
        industryAggregation.column("MEAN [ChangeAsNumber]").setName("ChangeAsNumber");
//        industryAggregation.column("SUM [MarketCap]").setName("MarketCap");
        //XXX: here I set MarketCap to 0 since branchvalues = remainder by default. TODO: Set branchvalues=total
        industryAggregation.addColumns(industryAggregation.doubleColumn("ChangeAsNumber").map((t) -> {
            return 0d;
        }).setName("MarketCap"));
        industryAggregation = industryAggregation.select("ids", "MarketCap", "ChangeAsNumber");
        // --> [ids (ie. Industry ids), ChangeAsNumber, MarketCap]

        //System.out.println(industryAggregation);

        Table industryView = view.select("Industry" /*, "Change", "ChangeAsNumber", "MarketCap" */);
        industryView.addColumns(industryIdColumn,
                sectorIdColumn.copy().setName("Parent"));
        industryView.stringColumn("Industry").setName("Label");
        // [ids (. ie Industry ids), Label (ie. Industry), Parent (ie. Sector ids)]

        Table industrySectorView = industryView.joinOn("ids").inner(industryAggregation);
        // [ids (. ie Industry ids), Label (ie. Industry), Parent (ie. Sector ids), ChangeAsNumber, MarketCap]
        industrySectorView.addColumns(industrySectorView.doubleColumn("ChangeAsNumber").mapInto(new DoubleFunction<String>() {
            @Override
            public String apply(double value) {
                return String.format("%.2f%%", value);
            }
        }, StringColumn.create("Change")));
        // [ids (. ie Industry ids), Label (ie. Industry), Parent (ie. Sector ids), ChangeAsNumber, MarketCap, Change (ie. with %)]
        //System.out.println(industrySectorView);

        //aggregate Sector MarketCap and ChangeAsNumber
        Table sectorAggregation = industrySectorView.summarize(Arrays.asList("MarketCap", "ChangeAsNumber"),
                AggregateFunctions.sum, AggregateFunctions.mean).by("Parent");
        sectorAggregation.column("Parent").setName("ids");
        sectorAggregation.column("MEAN [ChangeAsNumber]").setName("ChangeAsNumber");
//        sectorAggregation.column("SUM [MarketCap]").setName("MarketCap");
        //XXX: here I set MarketCap to 0 since branchvalues = remainder by default. TODO: Set branchvalues=total
        sectorAggregation.addColumns(sectorAggregation.doubleColumn("ChangeAsNumber").map((t) -> {
            return 0d;
        }).setName("MarketCap"));
        sectorAggregation = sectorAggregation.select("ids", "MarketCap", "ChangeAsNumber");
        // --> [ids (ie. Sector ids), ChangeAsNumber, MarketCap]

        //System.out.println(sectorAggregation);

        Table sectorView = view.select("Sector" /*, "Change", "ChangeAsNumber", "MarketCap"*/);
        sectorView.addColumns(sectorIdColumn);
        sectorView.addColumns(view.stringColumn("Sector").map((t) -> {
            //no parent
            return "";
        }).setName("Parent"));
        sectorView.stringColumn("Sector").setName("Label");
        // [ids (. ie Sector ids), Label (ie. Sector), Parent (ie. nothing/empty string)]

        Table sectorFinalView = sectorView.joinOn("ids").inner(sectorAggregation);
        // [ids (. ie Sector ids), Label (ie. Sector), Parent (ie. nothing/empty string), ChangeAsNumber, MarketCap]
        sectorFinalView.addColumns(sectorFinalView.doubleColumn("ChangeAsNumber").mapInto(new DoubleFunction<String>() {
            @Override
            public String apply(double value) {
                return String.format("%.2f%%", value);
            }
        }, StringColumn.create("Change")));
        // [ids (. ie Sector ids), Label (ie. Sector), Parent (ie. nothing/empty string), ChangeAsNumber, MarketCap, Change (ie. with %)]
        //System.out.println(sectorFinalView);

        //append views into one
        //now they are all: ids, Label, Parent(with IDS), Change, ChangeAsNumber, MarketCap
        Table all = tickerIndustryView.emptyCopy().append(tickerIndustryView).append(industrySectorView).append(sectorFinalView);
        //System.out.println(all.toString());

        return all;
    }

    static String color(double minRange, double maxRange, Double d) {
        if (d == null) {
            return "grey";
        }

        //limit boundary between -10% to +10%
        if (d > maxRange) {
            d = maxRange;
        } else if (d < minRange) {
            d = minRange;
        }

        double[] red = new double[]{255, 0, 0};
        double[] green = new double[]{0, 255, 0};

        double value = (d - minRange) / (maxRange - minRange);

        int[] c = new int[3];
        StringBuilder sb = new StringBuilder("#");
        for (int i = 0; i < 3; i++) {
            c[i] = (int) (value * green[i] + (1 - value) * red[i]);
            String hex = Integer.toHexString(c[i]);
            if (hex.length() == 1) {
                sb.append("0");
            }
            sb.append(hex);
        }

        return sb.toString();
    }

    protected BSPanel createContentPanelCenter() {

        QuickBodyPanel body = new QuickBodyPanel();

        // Add Chart
        Table treemapTable;

        double boundary;
        try {
            treemapTable = loadLargeStocks();
            DoubleColumn numericChange = (DoubleColumn) treemapTable.column("ChangeAsNumber");
            final double minChange = numericChange.min();
            final double maxChange = numericChange.max();

            //limit boundary between -10% to +10% or less
            final double minRange = Math.max(minChange, -10);
            final double maxRange = Math.min(maxChange, 10);

            //boundary (-range,+range)
            boundary = Math.max(Math.abs(minRange), Math.abs(maxRange));

            final double fBoundary = boundary;
            StringColumn color = numericChange.mapInto((Double t) -> {
                if (t == null) {
                    return "#7f7f7f";
                } else {
                    return color(-fBoundary, +fBoundary, t);
                }
            }, StringColumn.create("Color", numericChange.size()));

            treemapTable.addColumns(color);

        } catch ( Throwable e ) {
            treemapTable = TSDataSetFactory.createSampleStockMarketEquities().getTSTable();
            e.printStackTrace();

            //-10% to +10%
            boundary = 10d;
        }

        ChartBuilder stockBuilder = ChartBuilder.createBuilder()
                .dataTable(treemapTable)
                .chartType(ChartBuilder.CHART_TYPE.TREEMAP)
                .rowColumns("ids")
                .labelColumns("Label")
                .dataColumns(/* parent: */ "Parent")
                .sizeColumn(/* values: */ "MarketCap")
                .detailColumns(/* text: */ "Change")
                .colorColumn(/* marker.colors: */ "ChangeAsNumber")
                .layout(1043, 500, false);

        //TODO: Event handler
        EventHandler clickHandler = new EventHandler() {
            @Override
            public String asJavascript(String targetName, String divName) {
                return ChartsSunburst.resource("treemap-doubleclick-handler.js", "")
                        .replaceAll("targetName", targetName);
            }
        };

        body.addRowOfColumns(
                new BSCard(new TSFigurePanel(stockBuilder.divName("treemapDiv1").build(), "treemapDiv1"),
                        "Treemap Chart")
        );

        Table bsdTable = TSDataSetFactory.createSampleFamilyTreeData().getTSTable();


        ChartBuilder builder = ChartBuilder.createBuilder()
                        .dataTable(bsdTable)
                        .chartType(ChartBuilder.CHART_TYPE.TREEMAP)
                        .rowColumns("Name")
                        .dataColumns("Parent");
        builder.layout(486, 200, false);

        body.addRowOfColumns(
                new BSCard(new TSFigurePanel(builder.divName("treemapDiv2").build(), "treemapDiv2"),
                        "Treemap Chart"),
                new BSCard(new TSFigurePanel(builder.divName("treemapDiv3").build(), "treemapDiv3"),
                        "Treemap Chart")
        );

        builder.layout(300, 200, false);

        body.addRowOfColumns(
                new BSCard(new TSFigurePanel(builder.divName("treemapDiv4").build(), "treemapDiv4"),
                        "Treemap Chart"),
                new BSCard(new TSFigurePanel(builder.divName("treemapDiv5").build(), "treemapDiv5"),
                        "Treemap Chart"),
                new BSCard(new TSFigurePanel(builder.divName("treemapDiv6").build(), "treemapDiv6"),
                        "Treemap Chart")
        );

        body.doLayout();

        BSPanel panel = new BSPanel();
        panel.add(new HTMLLineBreak(1));
        panel.add(body);
        panel.add(new HTMLLineBreak(2));

        return panel;

    }

}
