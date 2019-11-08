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
import quicksilver.webapp.simpleui.bootstrap4.charts.TSScatterChartPanel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSCard;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.quick.QuickBodyPanel;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.components.Layout;

public class ChartsScatter extends AbstractComponentsChartsPage {

    public ChartsScatter() {
        super();
        toolbar.setActiveButton("Scatter");
    }

    protected BSPanel createContentPanelCenter() {

        QuickBodyPanel body = new QuickBodyPanel();

        // Add Chart
        Table scatterTable = TSDataSetFactory.createSampleCountryEconomicData().getTSTable();

        Layout.LayoutBuilder layoutBuilder = TSScatterChartPanel.createLayoutBuilder(1000, 200, false);
        Layout layout = layoutBuilder.build();

        body.addRowOfColumns(
                new BSCard(new TSScatterChartPanel(layout, scatterTable, "scatterDiv1", "Population", "GDP") ,
                        "Scatter Chart")
        );

        layoutBuilder = TSScatterChartPanel.createLayoutBuilder(450, 200, false);
        layout = layoutBuilder.build();

        body.addRowOfColumns(
                new BSCard(new TSScatterChartPanel(layout, scatterTable, "scatterDiv2", "Population", "GDP") ,
                        "Scatter Chart"),
                new BSCard(new TSScatterChartPanel(layout, scatterTable, "scatterDiv3", "Population", "GDP") ,
                        "Scatter Chart")
        );

        layoutBuilder = TSScatterChartPanel.createLayoutBuilder(300, 200, false);
        layout = layoutBuilder.build();

        body.addRowOfColumns(
                new BSCard(new TSScatterChartPanel(layout, scatterTable, "scatterDiv4", "Population", "GDP") ,
                        "Scatter Chart"),
                new BSCard(new TSScatterChartPanel(layout, scatterTable, "scatterDiv5", "Population", "GDP") ,
                        "Scatter Chart"),
                new BSCard(new TSScatterChartPanel(layout, scatterTable, "scatterDiv6", "Population", "GDP") ,
                        "Scatter Chart")
        );

        body.doLayout();

        BSPanel panel = new BSPanel();
        panel.add(new HTMLLineBreak(1));
        panel.add(body);
        panel.add(new HTMLLineBreak(2));

        return panel;

    }

}
