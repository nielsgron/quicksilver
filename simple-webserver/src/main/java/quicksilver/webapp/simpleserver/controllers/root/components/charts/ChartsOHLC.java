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

import quicksilver.commons.data.TSDataSetFactory;
import quicksilver.webapp.simpleserver.controllers.root.components.tables.TableData;
import quicksilver.webapp.simpleui.bootstrap4.charts.TSOHLCChartPanel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSCard;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.quick.QuickBodyPanel;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.components.Layout;

import java.time.LocalDate;

public class ChartsOHLC extends AbstractComponentsChartsPage {

    public ChartsOHLC() {
        super();
        toolbar.setActiveButton("OHLC");
    }

    protected BSPanel createContentPanelCenter() {

        QuickBodyPanel body = new QuickBodyPanel();

        // Add Chart
        Table ohlcTable = null;

        try {
            ohlcTable = TableData.loadStockPrices(LocalDate.of(2019, 9, 10), LocalDate.of(2019, 9, 24));
        } catch ( Exception e ) {
            e.printStackTrace();
            ohlcTable = TSDataSetFactory.createSampleStockPrices().getTSTable();
        }

        Layout.LayoutBuilder layoutBuilder = TSOHLCChartPanel.createLayoutBuilder(1000, 200, false);
        Layout layout = layoutBuilder.build();

        body.addRowOfColumns(
                new BSCard(new TSOHLCChartPanel(layout, ohlcTable, "ohlcDiv1", "Date", "Open", "High", "Low", "Close") ,
                        "OHLC Chart")
        );

        layoutBuilder = TSOHLCChartPanel.createLayoutBuilder(450, 200, false);
        layout = layoutBuilder.build();

        body.addRowOfColumns(
                new BSCard(new TSOHLCChartPanel(layout, ohlcTable, "ohlcDiv2", "Date", "Open", "High", "Low", "Close") ,
                        "OHLC Chart"),
                new BSCard(new TSOHLCChartPanel(layout, ohlcTable, "ohlcDiv3", "Date", "Open", "High", "Low", "Close") ,
                        "OHLC Chart")
        );

        layoutBuilder = TSOHLCChartPanel.createLayoutBuilder(300, 200, false);
        layout = layoutBuilder.build();

        body.addRowOfColumns(
                new BSCard(new TSOHLCChartPanel(layout, ohlcTable, "ohlcDiv4", "Date", "Open", "High", "Low", "Close") ,
                        "OHLC Chart"),
                new BSCard(new TSOHLCChartPanel(layout, ohlcTable, "ohlcDiv5", "Date", "Open", "High", "Low", "Close") ,
                        "OHLC Chart"),
                new BSCard(new TSOHLCChartPanel(layout, ohlcTable, "ohlcDiv6", "Date", "Open", "High", "Low", "Close") ,
                        "OHLC Chart")
        );

        body.doLayout();

        BSPanel panel = new BSPanel();
        panel.add(new HTMLLineBreak(1));
        panel.add(body);
        panel.add(new HTMLLineBreak(2));

        return panel;

    }

}
