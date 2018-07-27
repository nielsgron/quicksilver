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

public class BSInput extends BSComponent {

    private String input_type;
    private String input_placeholder;
    private String input_aria_label;
    private String input_aria_describedby;

    public BSInput(String type) {
        input_type = type;
    }

    public BSInput(String type, String placeholder) {
        input_type = type;
        input_placeholder = placeholder;
    }

    public BSInput(String type, String placeholder, String aria_label, String aria_describedby) {
        input_type = type;
        input_placeholder = placeholder;
        input_aria_label = aria_label;
        input_aria_describedby = aria_describedby;
    }

    public void render(HtmlStream stream) {

        stream.write("<input");

        stream.write(" class=\"form-control\"");
        stream.write(" type=\"" + input_type + "\"");

        if ( input_placeholder != null && input_placeholder.length() > 0 ) {
            stream.write(" placeholder=\"" + input_placeholder + "\"");
        }
        if ( input_aria_label != null && input_aria_label.length() > 0 ) {
            stream.write(" aria-label=\"" + input_aria_label + "\"");
        }
        if ( input_aria_describedby != null && input_aria_describedby.length() > 0 ) {
            stream.write(" aria-describedby=\"" + input_aria_describedby + "\"");
        }

        stream.writeln(">");

    }

}
