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

package quicksilver.webapp.simpleui.bootstrap4.charts;

import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.api.TreemapPlot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.components.Margin;

public class TSTreeMapChartPanel extends TSFigurePanel {

    public TSTreeMapChartPanel(Table table, String divName, int width, int height, boolean enableLegend, String... columns) {
        super(divName);

        Figure figure = null;

        try {
            //default margins are huge for small dimensions. leave 10% each side but no more than 50px
            int margin = Math.min(50, Math.min(width, height) / 10);

            figure = TreemapPlot.create(
                    Layout.builder("")
                            .width(width)
                            .height(height)
                            .margin(Margin.builder()
                                    .top(margin)
                                    .bottom(margin)
                                    .left(margin)
                                    .right(margin)
                                    .build())
                            .showLegend(enableLegend)
                            .build(),
                    table, columns);
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        addFigure(figure);

    }

}
