/*
 * Copyright 2018 Niels Gron and Contributors All Rights Reserved.
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

package quicksilver.webapp.simpleui.bootstrap4.components;

import quicksilver.webapp.simpleui.HtmlStream;

public class BSColumn extends BSComponentContainer {

    private int prop_columnWeight = -1;

    @Override
    public void render(HtmlStream stream) {

        stream.write("<div class=\"");

        if ( prop_columnWeight <= 0 || prop_columnWeight > 12 ) {
            stream.write("column");
        } else {
            stream.write("col-md-" + prop_columnWeight );
        }

        stream.writeln("\">");
        super.render(stream);
        stream.writeln("</div>");

    }

    public void setColumnWeight(int columnWeight) {
        prop_columnWeight = columnWeight;
    }

}
