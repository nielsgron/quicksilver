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
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import quicksilver.commons.data.TSDataSetFactory;
import quicksilver.webapp.simpleui.bootstrap4.charts.TSSunburstChartPanel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSCard;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.quick.QuickBodyPanel;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.components.Layout;

public class ChartsSunburst extends AbstractComponentsChartsPage {

    public ChartsSunburst() {
        super();
        toolbar.setActiveButton("Sunburst");
    }

    static String resource(String name, String def) {
        try {
            return IOUtils.toString(ChartsSunburst.class.getResourceAsStream(name), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            return def;
        }
    }

    protected BSPanel createContentPanelCenter() {

        QuickBodyPanel body = new QuickBodyPanel();

        // Add Chart
        Table sunburstTable;

        try {
            sunburstTable = ChartsTreemap.loadLargeStocks();
        } catch (Exception e) {
            sunburstTable = TSDataSetFactory.createSampleStockMarketEquities().getTSTable();
        }

        Layout.LayoutBuilder layoutBuilder = TSSunburstChartPanel.createLayoutBuilder(1000, 400, true);
        Layout layout = layoutBuilder.build();

        body.addRowOfColumns(
                new BSCard(new TSSunburstChartPanel(layout, sunburstTable, "sunburstDiv1",
                        (String targetName, String divName) -> {
                            return resource("sunburst-doubleclick-handler.js", "")
                                    .replaceAll("targetName", targetName);
                        },
                        "Company", "Industry", "Sector", "MarketCap"),
                        "Sunburst Chart")
        );

        layoutBuilder = TSSunburstChartPanel.createLayoutBuilder(450, 200, false);
        layout = layoutBuilder.build();

        body.addRowOfColumns(
                new BSCard(new TSSunburstChartPanel(layout, sunburstTable, "sunburstDiv2", "Company", "Sector", "MarketCap") ,
                        "Sunburst Chart"),
                new BSCard(new TSSunburstChartPanel(layout, sunburstTable, "sunburstDiv3", "Company", "Sector", "MarketCap") ,
                        "Sunburst Chart")
        );

        layoutBuilder = TSSunburstChartPanel.createLayoutBuilder(300, 200, false);
        layout = layoutBuilder.build();

        body.addRowOfColumns(
                new BSCard(new TSSunburstChartPanel(layout, sunburstTable, "sunburstDiv4", "Company", "Sector", "MarketCap") ,
                        "Sunburst Chart"),
                new BSCard(new TSSunburstChartPanel(layout, sunburstTable, "sunburstDiv5", "Company", "Sector", "MarketCap") ,
                        "Sunburst Chart"),
                new BSCard(new TSSunburstChartPanel(layout, sunburstTable, "sunburstDiv6", "Company", "Sector", "MarketCap") ,
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
