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

public class BSButton extends BSComponent {

    private String text;
    private String hyperlink;
    private boolean isOutline;

    public BSButton(String text) {
        this(text, BSComponent.Type.PRIMARY);
    }

    public BSButton(String text, BSComponent.Type type) {
        this(text, type, false, null);
    }

    public BSButton(String text, BSComponent.Type type, boolean isOutline, String hyperLink) {
        setType(type);
        this.text = text;
        this.hyperlink = hyperLink;
        this.isOutline = isOutline;

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Button");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        if ( hyperlink != null ) {
            putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "a");
            addTagAttribute("href", hyperlink);
        } else {
            putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "button");
            addTagAttribute("type", "button" );
        }

        String classList = "btn";
        if ( isOutline ) {
            classList += " btn-outline-" + getType().getTypeName();
        } else {
            classList += " btn-" + getType().getTypeName();
        }
        if ( getSize() == Size.SMALL ) {
            classList += " btn-sm";
        } else if ( getSize() == Size.LARGE ) {
            classList += " btn-lg";
        }

        addTagAttribute("class", classList);

    }

    public void renderBody(HtmlStream stream) {
        stream.writeln(text);
    }

}
