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
import quicksilver.webapp.simpleui.html.components.HTMLComponent;

public class BSForm extends BSComponentContainer {

    private boolean prop_isInline = false;
    private boolean prop_isGET = true;
    private String prop_action;

    public BSForm(String action, boolean isGET) {
        this(false, action, isGET);
    }

    public BSForm(boolean bInline, String action, boolean isGET) {
        prop_isInline = bInline;
        prop_action = action;
        prop_isGET = isGET;
    }

    public void render(HtmlStream stream) {

        if ( !prop_isInline) {
            stream.writeln("<form");
        } else {
            stream.writeln("<form class=\"form-inline my-2 my-md-0\"");
        }

        stream.write(" action=\"" + prop_action + "\"");
        if ( prop_isGET ) {
            stream.write(" method=\"get\"");
        } else {
            stream.write(" method=\"post\"");
        }

        stream.writeln(">");

        super.render(stream);
        stream.writeln("</form>");

    }

    public void addAsGroup(HTMLComponent... c) {

        BSFormGroup group = new BSFormGroup();
        for ( int i = 0; i < c.length; i++ ) {
            group.add(c[i]);
        }

        add(group);
    }

}
