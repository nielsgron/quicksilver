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
import quicksilver.commons.data.TSDataSetFactory;
import quicksilver.webapp.simpleserver.controllers.root.components.tables.TableData;
import quicksilver.webapp.simpleui.bootstrap4.charts.TSFigurePanel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSCard;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.quick.QuickBodyPanel;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.columns.Column;
import tech.tablesaw.columns.numbers.DoubleColumnType;

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
        return treemapTable;
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
                .columnsForViewColumns("Ticker", "Industry", "Sector")
                .columnForSize(/* values: */ "ZERO [MarketCap]")
                //TODO: how to aggregate Change?
                .columnsForLabels(/* text: */ "Change")
                .columnForColor(/* marker.colors: */ "ChangeAsNumber")
                .layout(1043, 500, false);

        stockBuilder.eventHandler((String targetName, String divName) -> {
            return ChartsSunburst.resource("treemap-doubleclick-handler.js", "")
                    .replaceAll("targetName", targetName);
        });

        body.addRowOfColumns(
                new BSCard(new TSFigurePanel(stockBuilder.divName("treemapDiv1").build(), "treemapDiv1"),
                        "Treemap Chart")
        );

        ChartBuilder builder = ChartBuilder.createBuilder()
                        .dataTable(treemapTable.sampleN(20))
                        .chartType(ChartBuilder.CHART_TYPE.TREEMAP)
                        .columnsForViewColumns("Ticker", "Industry");
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
