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
import quicksilver.webapp.simpleui.bootstrap4.charts.TSFigurePanel;
import quicksilver.webapp.simpleui.bootstrap4.charts.TSHorizontalBarChartPanel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSCard;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.quick.QuickBodyPanel;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.components.Layout;

public class ChartsHBar extends AbstractComponentsChartsPage {

    public ChartsHBar() {
        super();
        toolbar.setActiveButton("HBar");
    }

    protected BSPanel createContentPanelCenter() {

        QuickBodyPanel body = new QuickBodyPanel();

        // Add Chart
        //Table table = Charts.createPieDataSet(true);
        Table table = TSDataSetFactory.createSampleCountryEconomicData().getTSTable();

        Layout.LayoutBuilder layoutBuilder = TSHorizontalBarChartPanel.createLayoutBuilder(900, 200, false);

        body.addRowOfColumns(
            new BSCard( new TSHorizontalBarChartPanel(layoutBuilder.build(), table, "div1", "Country", "GDP"), "Country GDP (Nominal)")
        );

        layoutBuilder = TSHorizontalBarChartPanel.createLayoutBuilder(450, 200, false);

        body.addRowOfColumns(
            new BSCard( new TSHorizontalBarChartPanel(layoutBuilder.build(), table, "div2", "Country", "GDP"), "Country GDP (Nominal)"),
            new BSCard( new TSHorizontalBarChartPanel(layoutBuilder.build(), table, "div3", "Country", "Population"), "Country Population")
        );

        layoutBuilder = TSHorizontalBarChartPanel.createLayoutBuilder(300, 200, false);

        body.addRowOfColumns(
            new BSCard( new TSHorizontalBarChartPanel(layoutBuilder.build(), table, "div4", "Country", "GDP"), "Country GDP (Nominal)"),
            new BSCard( new TSHorizontalBarChartPanel(layoutBuilder.build(), table, "div5", "Country", "GDP_Capita"), "Country GDP (Per Capita)"),
            new BSCard( new TSHorizontalBarChartPanel(layoutBuilder.build(), table, "div6", "Country", "Population"), "Country Population")
        );

        body.doLayout();

        BSPanel panel = new BSPanel();
        panel.add(new HTMLLineBreak(1));
        panel.add(body);
        panel.add(new HTMLLineBreak(2));

        return panel;

    }

}
