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
import quicksilver.webapp.simpleui.bootstrap4.charts.TSPieChartPanel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSCard;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.quick.QuickBodyPanel;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.components.Layout;

public class ChartsPie extends AbstractComponentsChartsPage {

    public ChartsPie() {
        super();
        toolbar.setActiveButton("Pie");
    }

    protected BSPanel createContentPanelCenter() {

        QuickBodyPanel body = new QuickBodyPanel();

        // Add Chart
        Table pieTable = TSDataSetFactory.createSampleCountryEconomicData().getTSTable();

        Layout.LayoutBuilder layoutBuilder = TSPieChartPanel.createLayoutBuilder(1000, 200, true);
        Layout layout = layoutBuilder.build();

        body.addRowOfColumns(
                new BSCard(new TSPieChartPanel(layout, pieTable, "pieDiv1", "Country", "GDP"),
                        "Pie Chart")
        );

        layoutBuilder = TSPieChartPanel.createLayoutBuilder(450, 200, false);
        layout = layoutBuilder.build();

        body.addRowOfColumns(
                new BSCard(new TSPieChartPanel(layout, pieTable, "pieDiv2", "Country", "GDP"),
                        "Pie Chart"),
                new BSCard(new TSPieChartPanel(layout, pieTable, "pieDiv3", "Country", "GDP"),
                        "Pie Chart")
        );

        layoutBuilder = TSPieChartPanel.createLayoutBuilder(300, 200, false);
        layout = layoutBuilder.build();

        body.addRowOfColumns(
                new BSCard(new TSPieChartPanel(layout, pieTable, "pieDiv4", "Country", "GDP"),
                        "Pie Chart"),
                new BSCard(new TSPieChartPanel(layout, pieTable, "pieDiv5", "Country", "GDP"),
                        "Pie Chart"),
                new BSCard(new TSPieChartPanel(layout, pieTable, "pieDiv6", "Country", "GDP"),
                        "Pie Chart")
        );

        body.doLayout();

        BSPanel panel = new BSPanel();
        panel.add(new HTMLLineBreak(1));
        panel.add(body);
        panel.add(new HTMLLineBreak(2));

        return panel;

    }

}
