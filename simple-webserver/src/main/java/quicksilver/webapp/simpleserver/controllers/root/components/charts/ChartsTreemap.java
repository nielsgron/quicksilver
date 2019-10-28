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
import quicksilver.webapp.simpleui.bootstrap4.charts.TSTreeMapChartPanel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSCard;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.quick.QuickBodyPanel;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import tech.tablesaw.api.Table;

public class ChartsTreemap extends AbstractComponentsChartsPage {

    public ChartsTreemap() {
        super();
        toolbar.setActiveButton("Treemap");
    }

    protected BSPanel createContentPanelCenter() {

        QuickBodyPanel body = new QuickBodyPanel();

        // Add Chart
        Table treemapTable = TSDataSetFactory.createSampleStockMarketEquities().getTSTable();

        body.addRowOfColumns(
                new BSCard(new TSTreeMapChartPanel(treemapTable, "treemapDiv1", "Sector", "Company", 900, 200, false) ,
                        "Treemap Chart")
        );

        Table bsdTable = TSDataSetFactory.createSampleFamilyTreeData().getTSTable();

        body.addRowOfColumns(
                new BSCard(new TSTreeMapChartPanel(bsdTable, "treemapDiv2", "Name", "Parent", 450, 200, false) ,
                        "Treemap Chart"),
                new BSCard(new TSTreeMapChartPanel(bsdTable, "treemapDiv3", "Name", "Parent", 450, 200, false) ,
                        "Treemap Chart")
        );

        body.addRowOfColumns(
                new BSCard(new TSTreeMapChartPanel(treemapTable, "treemapDiv4", "Sector", "Company", 300, 200, false) ,
                        "Treemap Chart"),
                new BSCard(new TSTreeMapChartPanel(treemapTable, "treemapDiv5", "Sector", "Company", 300, 200, false) ,
                        "Treemap Chart"),
                new BSCard(new TSTreeMapChartPanel(treemapTable, "treemapDiv6", "Sector", "Company", 300, 200, false) ,
                        "Treemap Chart")
        );

        body.doLayout();

        BSPanel panel = new BSPanel();
        panel.add(new HTMLLineBreak(1));
        panel.add(body);
        panel.add(new HTMLLineBreak(2));

        return panel;

    }

}
