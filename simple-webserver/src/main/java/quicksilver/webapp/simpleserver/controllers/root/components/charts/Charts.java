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

        Layout.LayoutBuilder horBarLayoutBuilder = TSHorizontalBarChartPanel.createLayoutBuilder(500, 200, false);
        Layout horBarLayout = horBarLayoutBuilder.build();

        Layout.LayoutBuilder verBarLayoutBuilder = TSVerticalBarChartPanel.createLayoutBuilder(500, 200, false);
        Layout verBarLayout = verBarLayoutBuilder.build();

        body.addRowOfColumns(
                new BSCard(new TSVerticalBarChartPanel(verBarLayout, hBarTable, "vbarDiv", "Country", "GDP"),
                        "Vertical Bar Chart"),
                new BSCard(new TSHorizontalBarChartPanel(horBarLayout, hBarTable, "hbarDiv", "Country", "GDP"),
                        "Horizontal Bar Chart")
        );

        // Add Line Chart
        // Add Area Chart
        Table lineTable = economicTable;
        Table areaTable = economicTable;

        Layout.LayoutBuilder lineLayoutBuilder = TSLineChartPanel.createLayoutBuilder(500, 200, false);
        Layout lineLayout = lineLayoutBuilder.build();

        Layout.LayoutBuilder areaLayoutBuilder = TSAreaChartPanel.createLayoutBuilder(500, 200, false);
        Layout areaLayout = areaLayoutBuilder.build();

        body.addRowOfColumns(
                new BSCard(new TSLineChartPanel(lineLayout, lineTable, "lineDiv", "Country", "GDP"),
                        "Line Chart"),
                new BSCard(new TSAreaChartPanel(areaLayout, areaTable, "areaDiv", "Country", "GDP"),
                        "Area Chart")
        );

        // Add Pie Chart
        // Add Timeseries Chart
        Table pieTable = economicTable;
        Table timeSeriesTable = stockPricesTable;

        Layout.LayoutBuilder pieLayoutBuilder = TSPieChartPanel.createLayoutBuilder(500, 200, false);
        Layout pieLayout = pieLayoutBuilder.build();

        Layout.LayoutBuilder timeSeriesLayoutBuilder = TSTimeSeriesChartPanel.createLayoutBuilder(500, 200, false);
        Layout timeSeriesLayout = timeSeriesLayoutBuilder.build();

        body.addRowOfColumns(
                new BSCard(new TSPieChartPanel(pieLayout, pieTable, "pieDiv", "Country", "GDP"),
                        "Pie Chart"),
                new BSCard(new TSTimeSeriesChartPanel(timeSeriesLayout, timeSeriesTable, "timeseriesDiv", "Date", "Close"),
                        "Timeseries Chart")
        );

        // Add Scatter Plot Chart
        // Add Bubble Chart
        Table scatterTable = economicTable;
        Table bubbleTable = economicTable;

        Layout.LayoutBuilder scatterLayoutBuilder = TSScatterChartPanel.createLayoutBuilder(500, 200, false);
        Layout scatterLayout = scatterLayoutBuilder.build();

        Layout.LayoutBuilder bubbleLayoutBuilder = TSBubbleChartPanel.createLayoutBuilder(500, 200, false);
        Layout bubbleLayout = bubbleLayoutBuilder.build();

        body.addRowOfColumns(
                new BSCard(new TSScatterChartPanel(scatterLayout, scatterTable, "scatterDiv", "Population", "GDP") ,
                        "Scatter Chart"),
                new BSCard(new TSBubbleChartPanel(bubbleLayout, bubbleTable, "bubbleDiv", "Population", "GDP", "GDP_Capita") ,
                        "Bubble Chart")
        );

        // Add Histogram Chart
        // Add Heatmap Chart
        Table histogramTable = economicTable;
        Table heatmapTable = stockEquitiesTable;

        Layout.LayoutBuilder histoLayoutBuilder = TSHistogramChartPanel.createLayoutBuilder(500, 200, false);
        Layout histoLayout = histoLayoutBuilder.build();

        Layout.LayoutBuilder heatLayoutBuilder = TSHeatMapChartPanel.createLayoutBuilder(500, 200, false);
        Layout heatLayout = heatLayoutBuilder.build();

        body.addRowOfColumns(
                new BSCard(new TSHistogramChartPanel(histoLayout, histogramTable, "histogramDiv", "Population") ,
                        "Histogram Chart"),
                new BSCard(new TSHeatMapChartPanel(heatLayout, heatmapTable, "heatmapDiv", "Company", "Sector") ,
                        "Heatmap Chart")
        );

        // Add Candlestick Chart
        // Add Open High Low Close Chart
        Table candleStickTable = stockPricesTable;
        Table ohlcTable = stockPricesTable;

        Layout.LayoutBuilder candleLayoutBuilder = TSCandlestickChartPanel.createLayoutBuilder(500, 200, false);
        Layout candleLayout = candleLayoutBuilder.build();

        Layout.LayoutBuilder ohlcLayoutBuilder = TSOHLCChartPanel.createLayoutBuilder(500, 200, false);
        Layout ohlcLayout = ohlcLayoutBuilder.build();

        body.addRowOfColumns(
                new BSCard(new TSCandlestickChartPanel(candleLayout, candleStickTable, "candlestickDiv", "Date", "Open", "High", "Low", "Close") ,
                        "Candlestick Chart"),
                new BSCard(new TSOHLCChartPanel(ohlcLayout, ohlcTable, "ohlcDiv", "Date", "Open", "High", "Low", "Close") ,
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
