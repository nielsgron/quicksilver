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

import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.html.components.HTMLText;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.components.Margin;

public class TSFigurePanel extends BSPanel {

    /*

        Simple
         - Bar & Stacked Bar [ Vertical | Horizontal ]
         - Line & Multi Line
         - Area & Stacked Area
         - Pie

        Advanced
         - Sunburst
         - Scatter Plot
         - Bubble

        Statistical
         - Histogram
         - Treemap
         - Heatmap

        Financial
         - Timeseries
         - Candlestick
         - Open High Low Close (OHLC)

    */

    protected String divName;
    protected int width;
    protected int height;
    protected boolean enabledLegend = false;

    public TSFigurePanel(String divName) {
        this(divName, 500, 200);
    }

    public TSFigurePanel(String divName, int width, int height) {
        this.divName = divName;
        this.width = width;
        this.height = height;
    }

    public TSFigurePanel(Figure figure, String divName) {
        this(figure, divName, 500, 200);
    }

    public TSFigurePanel(Figure figure, String divName, int width, int height) {
        this.divName = divName;
        this.width = width;
        this.height = height;
        addFigure(figure);
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void enableLegend(boolean enabled) {
        this.enabledLegend = enabled;
    }

    public void addFigure(Figure figure) {

        if ( figure == null ) {
            this.add(new HTMLText("To be implemented"));
        } else {

            try {

                HTMLText divS = new HTMLText(figure.divString(divName));
                String js = figure.asJavascript(divName);

                int rHeight = 600;
                int rWidth = 800;

                if (js.contains("height: 700")) {
                    rHeight = 700;
                }

                if (js.contains("width: 900")) {
                    rWidth = 900;
                }

                js = js.replace("height: " + rHeight + "", "height: " + height + ", margin: {\n" +
                        "    autoexpand: false,\n" +
                        "    l: 40,\n" +
                        "    r: 5,\n" +
                        "    b: 30,\n" +
                        "    t: 5\n" +
                        "  }");
                js = js.replace("width: " + rWidth + "", "width: " + width + "");
                js = js.replace("data, layout);", "data, layout, {displayModeBar: false});");
                HTMLText divJS = new HTMLText(js);
                //Plot.show( tsPlot );

                this.add(divS);
                this.add(divJS);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public static Layout.LayoutBuilder createLayoutBuilder(int width, int height, boolean enabledLegend) {
        return createLayoutBuilder(width, height, 0, 0, 0, 0, enabledLegend);
    }

    public static Layout.LayoutBuilder createLayoutBuilder(int width, int height, int marginTop, int marginBottom, int marginLeft, int marginRight, boolean enabledLegend) {

        return Layout.builder("")
                .width(width)
                .height(height)
                .margin(Margin.builder()
                        .top(marginTop)
                        .bottom(marginBottom)
                        .left(marginLeft)
                        .right(marginRight)
                        .build())
                .showLegend(enabledLegend);

    }

}
