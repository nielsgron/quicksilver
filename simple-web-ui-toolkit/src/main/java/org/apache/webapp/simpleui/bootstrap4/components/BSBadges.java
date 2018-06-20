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

public class BSBadges extends BSComponent {

    public static final String PRIMARY = "badge-primary";       // blue
    public static final String SECONDARY = "badge-secondary";   // gray
    public static final String SUCCESS = "badge-success";       // green
    public static final String DANGER = "badge-danger";         // red
    public static final String WARNING = "badge-warning";       // yellow
    public static final String INFO = "badge-info";             // light blue
    public static final String LIGHT = "badge-light";           // light gray
    public static final String DARK = "badge-dark";             // dark gray

    private String labelType = SECONDARY;
    private String labelText;

    private String labelLink = null;
    private boolean isPill = false;

    public BSBadges(String txt) {
        labelText = txt;
    }
    public BSBadges(String txt, String type) {
        labelText = txt;
        labelType = type;
    }
    public BSBadges(String txt, String type, String link, boolean bPill) {
        labelText = txt;
        labelType = type;
        labelLink = link;
        isPill = bPill;
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
        stream.write(labelType);
        stream.writeln("\">");
        stream.writeln(labelText);

        if ( labelLink == null ) {
            stream.writeln("</span>");
        } else {
            stream.writeln("</a>");
        }

    }

}
