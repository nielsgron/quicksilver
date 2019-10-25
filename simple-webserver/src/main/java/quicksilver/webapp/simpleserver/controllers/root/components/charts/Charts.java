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
import quicksilver.webapp.simpleui.bootstrap4.charts.*;
import quicksilver.webapp.simpleui.bootstrap4.components.BSCard;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.quick.QuickBodyPanel;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import tech.tablesaw.api.Table;
import tech.tablesaw.columns.Column;

public class Charts extends AbstractComponentsChartsPage {

    public Charts() {
    }

    protected BSPanel createContentPanelCenter() {

        QuickBodyPanel body = new QuickBodyPanel();

        Table economicTable = TSDataSetFactory.createSampleCountryEconomicData().getTSTable();
        Table stockEquitiesTable = TSDataSetFactory.createSampleStockMarketEquities().getTSTable();
        Table stockPricesTable = TSDataSetFactory.createSampleStockPrices().getTSTable();

        // Add Vertical Bar Chart
        // Add Horizontal Bar Chart
        Table hBarTable = createPieDataSet(true);

        body.addRowOfColumns(
            addVBarChart(hBarTable, "vbarDiv", "Vertical Bar Chart"),
            addHBarChart(hBarTable, "hbarDiv", "Horizontal Bar Chart")
        );

        // Add Line Chart
        // Add Area Chart
        Table lineTable = createPieDataSet(true);
        Table areaTable = createPieDataSet(true);

        body.addRowOfColumns(
            addLineChart(lineTable, "lineDiv", "Line Chart"),
            addAreaChart(areaTable, "areaDiv", "Area Chart")
        );

        // Add Pie Chart
        // Add Timeseries Chart
        Table pieTable = createPieDataSet(true);
        Table timeSeriesTable = TSDataSetFactory.createSampleStockPrices().getTSTable();

        body.addRowOfColumns(
            addPieChart(pieTable, "pieDiv", "Pie Chart"),
            addTimeseriesChart(timeSeriesTable, "timeseriesDiv", "Timeseries Chart")
        );

        // Add Scatter Plot Chart
        // Add Bubble Chart
        Table scatterTable = economicTable;
        Table bubbleTable = economicTable;

        body.addRowOfColumns(
            addScatterChart(scatterTable, "scatterDiv", "Scatter Chart", "Population", "GDP"),
            addBubbleChart(bubbleTable, "bubbleDiv", "Bubble Chart", "Population", "GDP", "GDP_Capita")
        );

        // Add Histogram Chart
        // Add Heatmap Chart
        Table histogramTable = createPieDataSet(true);
        Table heatmapTable = createMarketDataSet();

        body.addRowOfColumns(
            addHistogramChart(histogramTable, "histogramDiv", "Histogram Chart"),
            addHeatmapChart(heatmapTable, "heatmapDiv", "Heatmap Chart")
        );

        // Add Candlestick Chart
        // Add Open High Low Close Chart
        Table candleStickTable = TSDataSetFactory.createSampleStockPrices().getTSTable();
        Table ohlcTable = TSDataSetFactory.createSampleStockPrices().getTSTable();

        body.addRowOfColumns(
            addCandlestickChart(candleStickTable, "candlestickDiv", "Candlestick Chart"),
            addOHLCChart(ohlcTable, "ohlcDiv", "OHLC Chart")
        );

        // Add Treemap Chart
        // Add Sunburst Chart
        Table treemapTable = createMarketDataSet();
        Table sunburstTable = createMarketDataSet();

        body.addRowOfColumns(
            addTreemapChart(treemapTable, "treemapDiv", "Treemap Chart", "Sector", "Company"),
            addSunburstChart(sunburstTable, "sunburstDiv", "Sunburst Chart", "Company", "MarketCap")
        );

        body.doLayout();

        BSPanel panel = new BSPanel();
        panel.add(new HTMLLineBreak(1));
        panel.add(body);
        panel.add(new HTMLLineBreak(2));

        return panel;
    }

    public static BSCard addVBarChart(Table table, String divName, String header) {
        TSVerticalBarChartPanel chart = new TSVerticalBarChartPanel(table, divName);
        return new BSCard(chart, header);
    }

    public static BSCard addHBarChart(Table table, String divName, String header) {
        TSHorizontalBarChartPanel chart = new TSHorizontalBarChartPanel(table, divName);
        return new BSCard(chart, header);
    }

    public static BSCard addLineChart(Table table, String divName, String header) {
        TSLineChartPanel chart = new TSLineChartPanel(table, divName);
        return new BSCard(chart, header);
    }

    public static BSCard addAreaChart(Table table, String divName, String header) {
        TSAreaChartPanel chart = new TSAreaChartPanel(table, divName);
        return new BSCard(chart, header);
    }

    public static BSCard addPieChart(Table table, String divName, String header) {
        TSPieChartPanel chart = new TSPieChartPanel(table, divName);
        return new BSCard(chart, header);
    }

    public static BSCard addSunburstChart(Table table, String divName, String header, String groupColName, String numberColName) {
        TSSunburstChartPanel chart = new TSSunburstChartPanel(table, divName, groupColName, numberColName);
        return new BSCard(chart, header);
    }

    public static BSCard addScatterChart(Table table, String divName, String header, String xCol, String yCol) {
        TSScatterChartPanel chart = new TSScatterChartPanel(table, divName, xCol, yCol);
        return new BSCard(chart, header);
    }

    public static BSCard addBubbleChart(Table table, String divName, String header, String xCol, String yCol, String sizeColumn) {
        TSBubbleChartPanel chart = new TSBubbleChartPanel(table, divName, xCol, yCol, sizeColumn);
        return new BSCard(chart, header);
    }

    public static BSCard addHistogramChart(Table table, String divName, String header) {
        TSHistogramChartPanel chart = new TSHistogramChartPanel(table, divName);
        return new BSCard(chart, header);
    }

    public static BSCard addTreemapChart(Table table, String divName, String header, String col, String parentCol) {
        TSTreeMapChartPanel chart = new TSTreeMapChartPanel(table, divName, col, parentCol);
        return new BSCard(chart, header);
    }

    public static BSCard addHeatmapChart(Table table, String divName, String header) {
        TSHeatMapChartPanel chart = new TSHeatMapChartPanel(table, divName);
        return new BSCard(chart, header);
    }

    public static BSCard addTimeseriesChart(Table table, String divName, String header) {
        TSTimeSeriesChartPanel chart = new TSTimeSeriesChartPanel(table, divName);
        return new BSCard(chart, header);
    }

    public static BSCard addCandlestickChart(Table table, String divName, String header) {
        TSCandlestickChartPanel chart = new TSCandlestickChartPanel(table, divName);
        return new BSCard(chart, header);
    }

    public static BSCard addOHLCChart(Table table, String divName, String header) {
        TSOHLCChartPanel chart = new TSOHLCChartPanel(table, divName);
        return new BSCard(chart, header);
    }

    public static Table createMarketDataSet() {

        return TSDataSetFactory.createSampleStockMarketEquities().getTSTable();
    }


    public static Table createPieDataSet() {
        return createPieDataSet(false);
    }

    public static Table createPieDataSet(boolean withSize) {

        String[] colNames;
        Class[] classNames;
        String[] nameValues = new String[] {"US", "China", "Canada", "France"};

        if ( withSize ) {
            colNames = new String[]{"Name", "Value", "Size"};
            classNames = new Class[]{String.class, Double.class, Double.class};
        } else {
            colNames = new String[]{"Name", "Value"};
            classNames = new Class[]{String.class, Double.class};
        }

        return createRandomPercentNVDataSet(nameValues, withSize).getTSTable();

    }

    public static TSDataSet createRandomPercentNVDataSet(String[] nameValues, boolean withSize) {

        String[] colNames;
        Class[] colClass;

        if ( withSize ) {
            colNames = new String[] {"Name", "Value", "Size"};
            colClass = new Class[] { String.class, Double.class, Double.class };
        } else {
            colNames = new String[] {"Name", "Value"};
            colClass = new Class[] { String.class, Double.class };
        }

        int rowCount = nameValues.length;

        // Get the column names and types
        Column[] colObjects;

        if ( withSize ) {
            colObjects = new Column[3];
            colObjects[2] = TSDataSetFactory.createColumnByJavaType(colClass[2], colNames[2]);
        } else {
            colObjects = new Column[2];
        }
        colObjects[0] = TSDataSetFactory.createColumnByJavaType(colClass[0], colNames[0]);
        colObjects[1] = TSDataSetFactory.createColumnByJavaType(colClass[1], colNames[1]);

        // Construct DataSet
        TSDataSetMeta meta = new TSDataSetMeta(colObjects, colNames, colClass);
        TSDataSet dataSet = new TSDataSet(meta);

        // Populate rows
        for ( int i = 0; i < rowCount; i++ ) {
            // Get the row values
            Object[] row = new Object[colNames.length];
            //for (int j = 0; j < colNames.length; j++) {
            row[0] = nameValues[i];
            row[1] = createRandomPercentData();
            if ( withSize ) {
                row[2] = createRandomPercentData();
            }
            //}
            dataSet.addRow(row);
        }

        return dataSet;
    }

    public static Object createRandomPercentData() {
        return Math.random();
    }

}
