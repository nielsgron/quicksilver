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
        Table hBarTable = economicTable;

        body.addRowOfColumns(
                new BSCard(new TSVerticalBarChartPanel(hBarTable, "vbarDiv", "Country", "GDP", 500, 200, false),
                        "Vertical Bar Chart"),
                new BSCard(new TSHorizontalBarChartPanel(hBarTable, "hbarDiv", "Country", "GDP", 500, 200, false),
                        "Horizontal Bar Chart")
        );

        // Add Line Chart
        // Add Area Chart
        Table lineTable = economicTable;
        Table areaTable = economicTable;

        body.addRowOfColumns(
                new BSCard(new TSLineChartPanel(lineTable, "lineDiv", "Country", "GDP", 500, 200, false),
                        "Line Chart"),
                new BSCard(new TSAreaChartPanel(areaTable, "areaDiv", "Country", "GDP", 500, 200, false),
                        "Area Chart")
        );

        // Add Pie Chart
        // Add Timeseries Chart
        Table pieTable = economicTable;
        Table timeSeriesTable = stockPricesTable;

        body.addRowOfColumns(
                new BSCard(new TSPieChartPanel(pieTable, "pieDiv", "Country", "GDP", 500, 200, false),
                        "Pie Chart"),
                new BSCard(new TSTimeSeriesChartPanel(timeSeriesTable, "timeseriesDiv", "Date", "Close", 500, 200, false),
                        "Timeseries Chart")
        );

        // Add Scatter Plot Chart
        // Add Bubble Chart
        Table scatterTable = economicTable;
        Table bubbleTable = economicTable;

        body.addRowOfColumns(
                new BSCard(new TSScatterChartPanel(scatterTable, "scatterDiv", "Population", "GDP", 500, 200, false) ,
                        "Scatter Chart"),
                new BSCard(new TSBubbleChartPanel(bubbleTable, "bubbleDiv", "Population", "GDP", "GDP_Capita", 500, 200, false) ,
                        "Bubble Chart")
        );

        // Add Histogram Chart
        // Add Heatmap Chart
        Table histogramTable = economicTable;
        Table heatmapTable = stockEquitiesTable;

        body.addRowOfColumns(
                new BSCard(new TSHistogramChartPanel(histogramTable, "histogramDiv", "Population", 500, 200, false) ,
                        "Histogram Chart"),
                new BSCard(new TSHeatMapChartPanel(heatmapTable, "heatmapDiv", "Company", "Sector", 500, 200, false) ,
                        "Heatmap Chart")
        );

        // Add Candlestick Chart
        // Add Open High Low Close Chart
        Table candleStickTable = stockPricesTable;
        Table ohlcTable = stockPricesTable;

        body.addRowOfColumns(
                new BSCard(new TSCandlestickChartPanel(candleStickTable, "candlestickDiv", "Date", "Open", "High", "Low", "Close", 500, 200, false) ,
                        "Candlestick Chart"),
                new BSCard(new TSOHLCChartPanel(ohlcTable, "ohlcDiv", "Date", "Open", "High", "Low", "Close", 500, 200, false) ,
                        "OHLC Chart")
        );

        // Add Treemap Chart
        // Add Sunburst Chart
        Table treemapTable = stockEquitiesTable;
        Table sunburstTable = stockEquitiesTable;

        body.addRowOfColumns(
                new BSCard(new TSTreeMapChartPanel(treemapTable, "treemapDiv", 500, 200, false, "Company", "Sector") ,
                        "Treemap Chart"),
                new BSCard(new TSSunburstChartPanel(sunburstTable, "sunburstDiv", 500, 200, false, "Company", "Sector", "MarketCap") ,
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
