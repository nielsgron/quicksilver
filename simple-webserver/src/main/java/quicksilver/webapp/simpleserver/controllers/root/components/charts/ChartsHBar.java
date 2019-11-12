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
import quicksilver.webapp.simpleui.bootstrap4.components.BSCard;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.quick.QuickBodyPanel;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import tech.tablesaw.api.Table;
import tech.tablesaw.charts.ChartBuilder;

public class ChartsHBar extends AbstractComponentsChartsPage {

    public ChartsHBar() {
        super();
        toolbar.setActiveButton("HBar");
    }

    protected BSPanel createContentPanelCenter() {

        QuickBodyPanel body = new QuickBodyPanel();

        String divName = "hbarDiv";

        // Add Chart
        //Table table = Charts.createPieDataSet(true);
        Table table = TSDataSetFactory.createSampleCountryEconomicData().getTSTable();

        ChartBuilder chartBuilder = ChartBuilder.createBuilder()
                .dataTable(table)
                .chartType(ChartBuilder.CHART_TYPE.HORIZONTAL_BAR)
                .layout(500, 200, false)
                .rowColumns("Country")
                .dataColumns("GDP")
                .axisTitles("GDP", "Country")
                ;

        chartBuilder.layout(1000, 200, 5, 35, 80, 5, false);

        body.addRowOfColumns(
            new BSCard( new TSFigurePanel(chartBuilder.divName(divName + "1").build(), divName + "1"),
                    "Country GDP (Nominal)")
        );

        chartBuilder.layout(450, 200, false);

        body.addRowOfColumns(
            new BSCard( new TSFigurePanel(chartBuilder.divName(divName + "2").build(), divName + "2"),
                    "Country GDP (Nominal)"),
            new BSCard( new TSFigurePanel(chartBuilder.divName(divName + "3").dataColumns("Population").build(), divName + "3"),
                    "Country Population")
        );

        chartBuilder.layout(300, 200, false);

        body.addRowOfColumns(
            new BSCard( new TSFigurePanel(chartBuilder.divName(divName + "4").build(), divName + "4"),
                    "Country GDP (Nominal)"),
            new BSCard( new TSFigurePanel(chartBuilder.divName(divName + "5").dataColumns("GDP_Capita").build(), divName + "5"),
                    "Country GDP (Per Capita)"),
            new BSCard( new TSFigurePanel(chartBuilder.divName(divName + "6").dataColumns("Population").build(), divName + "6"),
                    "Country Population")
        );

        body.doLayout();

        BSPanel panel = new BSPanel();
        panel.add(new HTMLLineBreak(1));
        panel.add(body);
        panel.add(new HTMLLineBreak(2));

        return panel;

    }

}
