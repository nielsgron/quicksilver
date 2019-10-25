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

import quicksilver.webapp.simpleui.html.components.HTMLText;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.components.Margin;
import tech.tablesaw.plotly.traces.BarTrace;
import tech.tablesaw.plotly.traces.Trace;

public class TSHorizontalBarChartPanel extends TSFigurePanel {

    public TSHorizontalBarChartPanel(Table table, String divName) {
        super(divName);
    }

    public TSHorizontalBarChartPanel(Table table, String divName, int height, int width, boolean enableLegend) {
        this(table, "Name", "Value", divName, height, width, enableLegend);
    }

    public TSHorizontalBarChartPanel(Table table, String groupColName, String numberColName, String divName, int height, int width, boolean enableLegend) {
        super(divName);

        setHeight(height);
        setWidth(width);
        enableLegend(enableLegend);

        Figure figure = null;

        try {
            figure = createFigure( table, groupColName, numberColName, "");
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        HTMLText divS = new HTMLText(figure.divString(divName));
        HTMLText divJS = new HTMLText(figure.asJavascript(divName));

        this.add(divS);
        this.add(divJS);

    }

    public Figure createFigure(Table table, String groupColName, String numberColName, String title) {

        Layout layout = Layout.builder()
                .title(title)
                .height(height)
                .width(width)
                .showLegend(enabledLegend)
                .margin(Margin.builder().left(40).right(5).bottom(30).top(5).build())
                .build();
        BarTrace trace = BarTrace.builder(table.categoricalColumn(groupColName), table.numberColumn(numberColName)).orientation(BarTrace.Orientation.HORIZONTAL).build();
        return new Figure(layout, new Trace[]{trace});

    }

}
