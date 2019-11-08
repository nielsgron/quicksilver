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
import quicksilver.webapp.simpleui.bootstrap4.charts.TSAreaChartPanel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSCard;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.quick.QuickBodyPanel;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.components.Layout;

public class ChartsArea extends AbstractComponentsChartsPage {

    public ChartsArea() {
        super();
        toolbar.setActiveButton("Area");
    }

    protected BSPanel createContentPanelCenter() {

        QuickBodyPanel body = new QuickBodyPanel();

        // Add Chart
        Table areaTable = TSDataSetFactory.createSampleCountryEconomicData().getTSTable();

        Layout.LayoutBuilder layoutBuilder = TSAreaChartPanel.createLayoutBuilder(1000, 200, false);
        Layout layout = layoutBuilder.build();

        body.addRowOfColumns(
                new BSCard(new TSAreaChartPanel(layout, areaTable, "areaDiv1", "Country", "GDP"),
                        "Wide Chart")
        );

        layoutBuilder = TSAreaChartPanel.createLayoutBuilder(450, 200, false);
        layout = layoutBuilder.build();

        body.addRowOfColumns(
                new BSCard(new TSAreaChartPanel(layout, areaTable, "areaDiv2", "Country", "GDP"),
                        "Half Width Chart"),
                new BSCard(new TSAreaChartPanel(layout, areaTable, "areaDiv3", "Country", "GDP"),
                "Half Width Chart")
        );

        layoutBuilder = TSAreaChartPanel.createLayoutBuilder(300, 200, false);
        layout = layoutBuilder.build();

        body.addRowOfColumns(
                new BSCard(new TSAreaChartPanel(layout, areaTable, "areaDiv4", "Country", "GDP"),
                        "Narrow Chart"),
                new BSCard(new TSAreaChartPanel(layout, areaTable, "areaDiv5", "Country", "GDP"),
                        "Narrow Chart"),
                new BSCard(new TSAreaChartPanel(layout, areaTable, "areaDiv6", "Country", "GDP"),
                        "Narrow Chart")
        );

        body.doLayout();

        BSPanel panel = new BSPanel();
        panel.add(new HTMLLineBreak(1));
        panel.add(body);
        panel.add(new HTMLLineBreak(2));

        return panel;

    }

}