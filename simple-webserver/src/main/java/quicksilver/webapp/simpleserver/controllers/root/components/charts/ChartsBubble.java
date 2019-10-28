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
import quicksilver.webapp.simpleui.bootstrap4.charts.TSBubbleChartPanel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSCard;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.quick.QuickBodyPanel;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import tech.tablesaw.api.Table;

public class ChartsBubble extends AbstractComponentsChartsPage {

    public ChartsBubble() {
        super();
        toolbar.setActiveButton("Bubble");
    }

    protected BSPanel createContentPanelCenter() {

        QuickBodyPanel body = new QuickBodyPanel();

        // Add Chart
        Table bubbleTable = TSDataSetFactory.createSampleCountryEconomicData().getTSTable();

        body.addRowOfColumns(
                new BSCard(new TSBubbleChartPanel(bubbleTable, "bubbleDiv1", "Population", "GDP", "GDP_Capita", 900, 200, true) ,
                        "Bubble Chart")
        );

        body.addRowOfColumns(
                new BSCard(new TSBubbleChartPanel(bubbleTable, "bubbleDiv2", "Population", "GDP", "GDP_Capita", 450, 200, false) ,
                        "Bubble Chart"),
                new BSCard(new TSBubbleChartPanel(bubbleTable, "bubbleDiv3", "Population", "GDP", "GDP_Capita", 450, 200, false) ,
                        "Bubble Chart")
        );

        body.addRowOfColumns(
                new BSCard(new TSBubbleChartPanel(bubbleTable, "bubbleDiv4", "Population", "GDP", "GDP_Capita", 300, 200, false) ,
                        "Bubble Chart"),
                new BSCard(new TSBubbleChartPanel(bubbleTable, "bubbleDiv5", "Population", "GDP", "GDP_Capita", 300, 200, false) ,
                        "Bubble Chart"),
                new BSCard(new TSBubbleChartPanel(bubbleTable, "bubbleDiv6", "Population", "GDP", "GDP_Capita", 300, 200, false) ,
                        "Bubble Chart")
        );

        body.doLayout();

        BSPanel panel = new BSPanel();
        panel.add(new HTMLLineBreak(1));
        panel.add(body);
        panel.add(new HTMLLineBreak(2));

        return panel;

    }

}
