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
import tech.tablesaw.charts.Chart;
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

    public TSFigurePanel(Chart figure, String divName) {
        this.divName = divName;
        addFigure(figure);
    }

    public void addFigure(Chart figure) {

        if ( figure == null ) {
            this.add(new HTMLText("To be implemented"));
        } else {

            for(int i = 0; i< figure.figureCount(); i++) {
            try {
                String perFigureDivName = figure.figureCount() > 1 ? (divName+"_"+i) : divName;

                HTMLText divS = new HTMLText(figure.divString(perFigureDivName, i));
                String js = figure.asJavascript(perFigureDivName, i);

                HTMLText divJS = new HTMLText(js);
                //Plot.show( tsPlot );

                this.add(divS);
                this.add(divJS);

            } catch (Exception e) {
                e.printStackTrace();
            }
            }

        }

    }

}
