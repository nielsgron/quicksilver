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

public class BSButton extends BSComponent {

    private String prop_text;
    private boolean isOutline;

    public BSButton(String text) {
        this(text, BSComponent.Type.PRIMARY);
    }

    public BSButton(String text, BSComponent.Type type) {
        this(text, type, false);
    }

    public BSButton(String text, BSComponent.Type type, boolean outline) {
        setType(type);
        prop_text = text;
        isOutline = outline;
    }

    public void render(HtmlStream stream) {

        stream.writeln("<button type=\"button\" ");

        if ( isOutline ) {
            stream.write("class=\"btn btn-outline-" + getType().getTypeName() );
        } else {
            stream.write("class=\"btn btn-" + getType().getTypeName() );
        }
        if ( getSize() == Size.SMALL ) {
            stream.write(" btn-sm");
        } else if ( getSize() == Size.LARGE ) {
            stream.write(" btn-lg");
        }

        stream.writeln("\">");
        stream.writeln(prop_text);
        stream.writeln("</button>");

    }

}
