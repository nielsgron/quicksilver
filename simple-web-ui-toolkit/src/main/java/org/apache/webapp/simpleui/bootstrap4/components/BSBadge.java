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

package org.apache.webapp.simpleui.bootstrap4.components;

import org.apache.webapp.simpleui.HtmlStream;

public class BSBadge extends BSComponent {

    private String labelText;
    private String labelLink = null;
    private boolean isPill = false;

    public BSBadge(String txt) {
        this(txt, Type.PRIMARY);
    }
    public BSBadge(String txt, Type type) {
        this(txt, type, null, false);
    }
    public BSBadge(String txt, Type type, String link, boolean bPill) {
        labelText = txt;
        labelLink = link;
        isPill = bPill;
        setType(type);
    }

    public void render(HtmlStream stream) {

        if ( labelLink == null ) {
            stream.write("<span ");
        } else {
            stream.write("<a href=\"");
            stream.write(labelLink);
            stream.write("\" ");
        }

        stream.write("class=\"badge ");
        if ( isPill ) {
            stream.write("badge-pill ");
        }
        stream.write("badge-" + getType().getTypeName());
        stream.writeln("\">");
        stream.writeln(labelText);

        if ( labelLink == null ) {
            stream.writeln("</span>");
        } else {
            stream.writeln("</a>");
        }

    }

}
