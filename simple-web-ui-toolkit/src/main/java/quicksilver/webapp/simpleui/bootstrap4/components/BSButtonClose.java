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

package quicksilver.webapp.simpleui.bootstrap4.components;

import quicksilver.webapp.simpleui.html.components.HTMLText;

public class BSButtonClose extends BSComponentContainer {

    private String text;

    public BSButtonClose() {
        this.text = "";
        add(new BSText("<span aria-hidden=\"true\">&times;</span>"));
    }

    public BSButtonClose(String text) {
        this.text = text;
        add(new HTMLText(text));
        add(new BSText("<span aria-hidden=\"true\">&times;</span>"));
    }

    public String getText() {
        return text;
    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Button");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "button");
        addTagAttribute("type", "button" );
        addTagAttribute("class", getClassNames());

        addTagAttribute("data-dismiss", "alert" );
        addTagAttribute("aria-label", "Close" );

    }

    protected String getClassNames() {
        StringBuilder cNames = new StringBuilder();

        cNames.append("close");

        return cNames.toString();
    }

    protected void renderBody() {

    }

}
