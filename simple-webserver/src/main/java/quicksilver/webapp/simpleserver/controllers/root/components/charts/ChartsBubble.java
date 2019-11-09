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
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.columns.numbers.DoubleColumnType;
import tech.tablesaw.plotly.components.Axis;
import tech.tablesaw.plotly.components.Grid;
import tech.tablesaw.plotly.components.Layout;

public class ChartsBubble extends AbstractComponentsChartsPage {

    public ChartsBubble() {
        super();
        toolbar.setActiveButton("Bubble");
    }

    protected BSPanel createContentPanelCenter() {

        QuickBodyPanel body = new QuickBodyPanel();

        // Add Chart
        Table bubbleTable = TSDataSetFactory.createSampleCountryEconomicData().getTSTable();

        DoubleColumn oldColumn = (DoubleColumn) bubbleTable.column("GDP_Capita");
        DoubleColumn newColumn = DoubleColumn.create("GDP_Capita_Ratio");
        newColumn = newColumn.emptyCopy(oldColumn.size());
        oldColumn.mapInto((Double p) -> { return p / 1000; }, newColumn);
        bubbleTable.addColumns(newColumn);

        Layout.LayoutBuilder layoutBuilder = TSBubbleChartPanel.createLayoutBuilder(1000, 200, 5, 35, 45, 5, false);
        layoutBuilder.xAxis(Axis.builder().title("Population").build());
        layoutBuilder.yAxis(Axis.builder().title("GDP").build());
        //layoutBuilder.grid(Grid.builder().build());
        //layoutBuilder.hoverMode(Layout.HoverMode.FALSE);
        Layout layout = layoutBuilder.build();

        body.addRowOfColumns(
                new BSCard(new TSBubbleChartPanel(layout, bubbleTable, "bubbleDiv1", "Population", "GDP", "GDP_Capita_Ratio") ,
                        "Bubble Chart")
        );

        layoutBuilder = TSBubbleChartPanel.createLayoutBuilder(450, 200, false);
        layout = layoutBuilder.build();

        body.addRowOfColumns(
                new BSCard(new TSBubbleChartPanel(layout, bubbleTable, "bubbleDiv2", "Population", "GDP", "GDP_Capita_Ratio") ,
                        "Bubble Chart"),
                new BSCard(new TSBubbleChartPanel(layout, bubbleTable, "bubbleDiv3", "Population", "GDP", "GDP_Capita_Ratio") ,
                        "Bubble Chart")
        );

        layoutBuilder = TSBubbleChartPanel.createLayoutBuilder(300, 200, false);
        layout = layoutBuilder.build();

        body.addRowOfColumns(
                new BSCard(new TSBubbleChartPanel(layout, bubbleTable, "bubbleDiv4", "Population", "GDP", "GDP_Capita_Ratio") ,
                        "Bubble Chart"),
                new BSCard(new TSBubbleChartPanel(layout, bubbleTable, "bubbleDiv5", "Population", "GDP", "GDP_Capita_Ratio") ,
                        "Bubble Chart"),
                new BSCard(new TSBubbleChartPanel(layout, bubbleTable, "bubbleDiv6", "Population", "GDP", "GDP_Capita_Ratio") ,
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
