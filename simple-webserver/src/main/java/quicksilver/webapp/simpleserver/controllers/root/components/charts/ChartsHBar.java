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
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.components.Margin;

public class ChartsHBar extends AbstractComponentsChartsPage {

    public ChartsHBar() {
        super();
        toolbar.setActiveButton("HBar");
    }

    protected BSPanel createContentPanelCenter() {

        QuickBodyPanel body = new QuickBodyPanel();

        String divName = "hbarDiv";

        boolean autoSize = true;

        // Add Chart
        //Table table = Charts.createPieDataSet(true);
        Table table = TSDataSetFactory.createSampleCountryEconomicData().getTSTable();

        ChartBuilder chartBuilder = ChartBuilder.createBuilder(ChartBuilder.CHART_RENDERER.EXPLORER)
                .dataTable(table)
                .chartType(ChartBuilder.CHART_TYPE.HORIZONTAL_BAR)
                .columnsForViewColumns("Country")
                .columnsForViewRows("GDP")
                .axisTitles("GDP", "Country")
                ;

        if ( !autoSize ) {
            chartBuilder.layout( 1000, 200, 5, 35, 80, 5, false);
        } else {
            chartBuilder.layout(5, 35, 80, 5, false);
            chartBuilder.getLayoutBuilder()
                    .autosize(true)
                    .height(250);
        }

        body.addRowOfColumns(
            new BSCard( new TSFigurePanel(chartBuilder.divName(divName + "1").build(), divName + "1"),
                    "Country GDP (Nominal)")
        );

        if ( !autoSize ) {
            chartBuilder.layout(450, 200, false);
        }

        body.addRowOfColumns(
            new BSCard( new TSFigurePanel(chartBuilder.divName(divName + "2").build(), divName + "2"),
                    "Country GDP (Nominal)"),
            new BSCard( new TSFigurePanel(chartBuilder.divName(divName + "3").columnsForViewRows("Population").build(), divName + "3"),
                    "Country Population")
        );

        if ( !autoSize ) {
            chartBuilder.layout(300, 200, false);
        }

        body.addRowOfColumns(
            new BSCard( new TSFigurePanel(chartBuilder.divName(divName + "4").build(), divName + "4"),
                    "Country GDP (Nominal)"),
            new BSCard( new TSFigurePanel(chartBuilder.divName(divName + "5").columnsForViewRows("GDP_Capita").build(), divName + "5"),
                    "Country GDP (Per Capita)"),
            new BSCard( new TSFigurePanel(chartBuilder.divName(divName + "6").columnsForViewRows("Population").build(), divName + "6"),
                    "Country Population")
        );

        ChartBuilder chartBuilderPerContinent = ChartBuilder.createBuilder(ChartBuilder.CHART_RENDERER.EXPLORER)
                .dataTable(table)
                .chartType(ChartBuilder.CHART_TYPE.HORIZONTAL_BAR)
                .layout(500, 200, false)
                .columnsForViewColumns("Continent")
                .columnsForViewRows("GDP")
                .axisTitles("GDP", "Continent")
                .columnForColor("Country");

        ChartBuilder chartBuilderPerContinentStacked = ChartBuilder.createBuilder(ChartBuilder.CHART_RENDERER.EXPLORER)
                .dataTable(table)
                .chartType(ChartBuilder.CHART_TYPE.HORIZONTAL_BAR, Layout.BarMode.STACK)
                .layout(500, 200, false)
                .columnsForViewColumns("Continent")
                .columnsForViewRows("GDP")
                .axisTitles("GDP", "Continent")
                .columnForColor("Country");

        body.addRowOfColumns(
                new BSCard(new TSFigurePanel(chartBuilderPerContinent.divName(divName + "7").build(), divName + "7"),
                        "Country GDP (Nominal)"),
                new BSCard(new TSFigurePanel(chartBuilderPerContinentStacked.divName(divName + "9").build(), divName + "9"),
                        "Country GDP (Nominal)")
        );

        BSPanel panel = new BSPanel();
        panel.add(new HTMLLineBreak(1));
        panel.add(body);
        panel.add(new HTMLLineBreak(2));

        return panel;

    }

}
