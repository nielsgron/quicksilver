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
import quicksilver.webapp.simpleui.bootstrap4.charts.TSLineChartPanel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSCard;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.quick.QuickBodyPanel;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import tech.tablesaw.api.Table;

public class ChartsLine extends AbstractComponentsChartsPage {

    public ChartsLine() {
        super();
        toolbar.setActiveButton("Line");
    }

    protected BSPanel createContentPanelCenter() {

        QuickBodyPanel body = new QuickBodyPanel();

        // Add Chart
        Table lineTable = TSDataSetFactory.createSampleCountryEconomicData().getTSTable();

        body.addRowOfColumns(
                new BSCard(new TSLineChartPanel(lineTable, "div1", "Country", "GDP", 900, 200, true),
                        "Wide Chart")
        );

        body.addRowOfColumns(
                new BSCard(new TSLineChartPanel(lineTable, "div2", "Country", "GDP", 450, 200, false),
                        "Line Chart"),
                new BSCard(new TSLineChartPanel(lineTable, "div3", "Country", "GDP", 450, 200, false),
                        "Line Chart")
        );

        body.addRowOfColumns(
                new BSCard(new TSLineChartPanel(lineTable, "div4", "Country", "GDP", 300, 200, false),
                        "Narrow Chart"),
                new BSCard(new TSLineChartPanel(lineTable, "div5", "Country", "GDP", 300, 200, false),
                        "Narrow Chart"),
                new BSCard(new TSLineChartPanel(lineTable, "div6", "Country", "GDP", 300, 200, false),
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
