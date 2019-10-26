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
import quicksilver.webapp.simpleui.bootstrap4.charts.TSSunburstChartPanel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSCard;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.quick.QuickBodyPanel;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import tech.tablesaw.api.Table;

public class ChartsSunburst extends AbstractComponentsChartsPage {

    public ChartsSunburst() {
        super();
        toolbar.setActiveButton("Sunburst");
    }

    protected BSPanel createContentPanelCenter() {

        QuickBodyPanel body = new QuickBodyPanel();

        // Add Chart
        Table sunburstTable = TSDataSetFactory.createSampleStockMarketEquities().getTSTable();

        body.addRowOfColumns(
                new BSCard(new TSSunburstChartPanel(sunburstTable, "sunburstDiv1", "Company", "MarketCap", 900, 200, false) ,
                        "Sunburst Chart")
        );

        body.addRowOfColumns(
                new BSCard(new TSSunburstChartPanel(sunburstTable, "sunburstDiv2", "Company", "MarketCap", 450, 200, false) ,
                        "Sunburst Chart"),
                new BSCard(new TSSunburstChartPanel(sunburstTable, "sunburstDiv3", "Company", "MarketCap", 450, 200, false) ,
                        "Sunburst Chart")
        );

        body.addRowOfColumns(
                new BSCard(new TSSunburstChartPanel(sunburstTable, "sunburstDiv4", "Company", "MarketCap", 300, 200, false) ,
                        "Sunburst Chart"),
                new BSCard(new TSSunburstChartPanel(sunburstTable, "sunburstDiv5", "Company", "MarketCap", 300, 200, false) ,
                        "Sunburst Chart"),
                new BSCard(new TSSunburstChartPanel(sunburstTable, "sunburstDiv6", "Company", "MarketCap", 300, 200, false) ,
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
