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

import quicksilver.commons.data.TSDataSet;
import quicksilver.commons.data.TSDataSetFactory;
import quicksilver.commons.data.TSDataSetMeta;
import quicksilver.webapp.simpleserver.controllers.root.components.tables.TableData;
import quicksilver.webapp.simpleserver.controllers.root.components.tables.Tables;
import quicksilver.webapp.simpleui.bootstrap4.charts.*;
import quicksilver.webapp.simpleui.bootstrap4.components.BSCard;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.quick.QuickBodyPanel;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import tech.tablesaw.api.DateColumn;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.columns.Column;
import tech.tablesaw.columns.numbers.DoubleColumnType;
import tech.tablesaw.plotly.components.Layout;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

public class Charts extends AbstractComponentsChartsPage {

    public Charts() {
    }

    protected BSPanel createContentPanelCenter() {

        QuickBodyPanel body = new QuickBodyPanel();

        Table economicTable = TSDataSetFactory.createSampleCountryEconomicData().getTSTable();
        Table stockEquitiesTable = TSDataSetFactory.createSampleStockMarketEquities().getTSTable();
        Table stockPricesTable = null;

        try {
            stockPricesTable = TableData.loadStockPrices(LocalDate.of(2019, 9, 10), LocalDate.of(2019, 9, 24));
        } catch ( Exception e ) {
            stockEquitiesTable = TSDataSetFactory.createSampleStockPrices().getTSTable();
        }

        // Add Vertical Bar Chart
        // Add Horizontal Bar Chart
        Table hBarTable = economicTable;

        ChartBuilder vbarChartBuilder = ChartBuilder.createBuilder()
                .dataTable(hBarTable)
                .chartType(ChartBuilder.CHART_TYPE.VERTICAL_BAR)
                .layout(500, 200, false)
                .rowColumns("Country")
                .dataColumns("GDP")
                ;
        ChartBuilder hbarChartBuilder = ChartBuilder.createBuilder()
                .dataTable(hBarTable)
                .chartType(ChartBuilder.CHART_TYPE.HORIZONTAL_BAR)
                .layout(500, 200, false)
                .rowColumns("Country")
                .dataColumns("GDP")
                ;

        body.addRowOfColumns(
                new BSCard(new TSFigurePanel(vbarChartBuilder.divName("vbarDiv").build(), "vbarDiv"),
                        "Vertical Bar Chart"),
                new BSCard(new TSFigurePanel(hbarChartBuilder.divName("hbarDiv").build(), "hbarDiv"),
                        "Horizontal Bar Chart")
        );

        // Add Line Chart
        // Add Area Chart
        Table lineTable = economicTable;
        Table areaTable = economicTable;

        ChartBuilder lineChartBuilder = ChartBuilder.createBuilder()
                .dataTable(lineTable)
                .chartType(ChartBuilder.CHART_TYPE.LINE)
                .layout(500, 200, false)
                .rowColumns("Country")
                .dataColumns("GDP")
                ;
        ChartBuilder areaChartBuilder = ChartBuilder.createBuilder()
                .dataTable(areaTable)
                .chartType(ChartBuilder.CHART_TYPE.AREA)
                .layout(500, 200, false)
                .rowColumns("Country")
                .dataColumns("GDP")
                ;

        body.addRowOfColumns(
                new BSCard(new TSFigurePanel(lineChartBuilder.divName("lineDiv").build(), "lineDiv"),
                        "Line Chart"),
                new BSCard(new TSFigurePanel(areaChartBuilder.divName("areaDiv").build(), "areaDiv"),
                        "Area Chart")
        );

        // Add Pie Chart
        // Add Timeseries Chart
        Table pieTable = economicTable;
        Table timeSeriesTable = stockPricesTable;

        ChartBuilder pieChartBuilder = ChartBuilder.createBuilder()
                .dataTable(pieTable)
                .chartType(ChartBuilder.CHART_TYPE.PIE)
                .layout(500, 200, false)
                .rowColumns("Country")
                .dataColumns("GDP")
                ;
        ChartBuilder timeseriesChartBuilder = ChartBuilder.createBuilder()
                .dataTable(timeSeriesTable)
                .chartType(ChartBuilder.CHART_TYPE.TIMESERIES)
                .layout(500, 200, false)
                .rowColumns("Date")
                .dataColumns("Close")
                ;

        body.addRowOfColumns(
                new BSCard(new TSFigurePanel(pieChartBuilder.divName("pieDiv").build(), "pieDiv"),
                        "Pie Chart"),
                new BSCard(new TSFigurePanel(timeseriesChartBuilder.divName("timeseriesDiv").build(), "timeseriesDiv"),
                        "Timeseries Chart")
        );

        // Add Scatter Plot Chart
        // Add Bubble Chart
        Table scatterTable = economicTable;
        Table bubbleTable = economicTable;

        DoubleColumn oldColumn = (DoubleColumn) bubbleTable.column("GDP_Capita");
        DoubleColumn newColumn = DoubleColumn.create("GDP_Capita");
        newColumn = newColumn.emptyCopy(oldColumn.size());

        oldColumn.mapInto((Double p) -> { return p / 1000; }, newColumn);
        bubbleTable.replaceColumn(newColumn);

        ChartBuilder scatterChartBuilder = ChartBuilder.createBuilder()
                .dataTable(scatterTable)
                .chartType(ChartBuilder.CHART_TYPE.SCATTERPLOT)
                .rowColumns("Population")
                .dataColumns("GDP")
                .layout(500, 200, false)
                .axisTitles("Population", "GDP")
                ;
        ChartBuilder bubbleChartBuilder = ChartBuilder.createBuilder()
                .dataTable(bubbleTable)
                .chartType(ChartBuilder.CHART_TYPE.BUBBLE)
                .rowColumns("Population")
                .dataColumns("GDP")
                .sizeColumn("GDP_Capita")
                .layout(500, 200, false)
                .axisTitles("Population", "GDP")
                ;

        body.addRowOfColumns(
                new BSCard(new TSFigurePanel(scatterChartBuilder.divName("scatterDiv").build(), "scatterDiv"),
                        "Scatter Chart"),
                new BSCard(new TSFigurePanel(bubbleChartBuilder.divName("bubbleDiv").build(), "bubbleDiv"),
                        "Bubble Chart")
        );

        // Add Histogram Chart
        // Add Heatmap Chart
        Table histogramTable = economicTable;
        Table heatmapTable = stockEquitiesTable;

        ChartBuilder histogramChartBuilder = ChartBuilder.createBuilder()
                .dataTable(histogramTable)
                .chartType(ChartBuilder.CHART_TYPE.HISTOGRAM)
                .layout(500, 200, false)
                //.rowColumns("Country")
                .dataColumns("Population")
                ;
        ChartBuilder heatmapChartBuilder = ChartBuilder.createBuilder()
                .dataTable(heatmapTable)
                .chartType(ChartBuilder.CHART_TYPE.HEATMAP)
                .layout(500, 200, false)
                .rowColumns("Company", "Sector")
                //.dataColumns("Close")
                ;

        body.addRowOfColumns(
                new BSCard(new TSFigurePanel(histogramChartBuilder.divName("histogramDiv").build(), "histogramDiv"),
                        "Histogram Chart"),
                new BSCard(new TSFigurePanel(heatmapChartBuilder.divName("heatmapDiv").build(), "heatmapDiv"),
                        "Heatmap Chart")
        );

        // Add Candlestick Chart
        // Add Open High Low Close Chart
        Table candleStickTable = stockPricesTable;
        Table ohlcTable = stockPricesTable;

        ChartBuilder candlestickChartBuilder = ChartBuilder.createBuilder()
                .dataTable(candleStickTable)
                .chartType(ChartBuilder.CHART_TYPE.CANDLESTICK)
                .layout(500, 200, false)
                .rowColumns("Date")
                .dataColumns("Open", "High", "Low", "Close")
                ;
        ChartBuilder ohlcChartBuilder = ChartBuilder.createBuilder()
                .dataTable(ohlcTable)
                .chartType(ChartBuilder.CHART_TYPE.OHLC)
                .layout(500, 200, false)
                .rowColumns("Date")
                .dataColumns("Open", "High", "Low", "Close")
                ;

        body.addRowOfColumns(
                new BSCard(new TSFigurePanel(candlestickChartBuilder.divName("candlestickDiv").build(), "candlestickDiv"),
                        "Candlestick Chart"),
                new BSCard(new TSFigurePanel(ohlcChartBuilder.divName("ohlcDiv").build(), "ohlcDiv"),
                        "OHLC Chart")
        );

        // Add Treemap Chart
        // Add Sunburst Chart
        Table treemapTable = stockEquitiesTable;
        Table sunburstTable = stockEquitiesTable;

        Layout.LayoutBuilder treeLayoutBuilder = TSTreeMapChartPanel.createLayoutBuilder(500, 200, false);
        Layout treeLayout = treeLayoutBuilder.build();

        Layout.LayoutBuilder sunburstLayoutBuilder = TSTreeMapChartPanel.createLayoutBuilder(500, 200, false);
        Layout sunburstLayout = sunburstLayoutBuilder.build();

        body.addRowOfColumns(
                new BSCard(new TSTreeMapChartPanel(treeLayout, treemapTable, "treemapDiv", "Company", "Sector") ,
                        "Treemap Chart"),
                new BSCard(new TSSunburstChartPanel(sunburstLayout, sunburstTable, "sunburstDiv", "Company", "Sector", "MarketCap") ,
                        "Sunburst Chart")
        );

        body.doLayout();

        BSPanel panel = new BSPanel();
        panel.add(new HTMLLineBreak(1));
        panel.add(body);
        panel.add(new HTMLLineBreak(2));

        return panel;
    }


}
