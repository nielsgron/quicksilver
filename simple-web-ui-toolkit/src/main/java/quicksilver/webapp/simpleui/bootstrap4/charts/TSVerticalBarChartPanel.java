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
import tech.tablesaw.plotly.api.VerticalBarPlot;
import tech.tablesaw.plotly.components.Figure;

public class TSVerticalBarChartPanel extends TSFigurePanel {

    public TSVerticalBarChartPanel(Table table, String divName, String groupColName, String numberColName, int width, int height, boolean enableLegend) {
        super(divName);

        Figure figure = null;

        try {
            figure = VerticalBarPlot.create("", table, groupColName, numberColName);
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        addFigure(figure);

    }

}
