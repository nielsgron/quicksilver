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

package quicksilver.webapp.simpleui.html.components;

public class HTMLDiv extends HTMLComponentContainer {

    private String classNames;
    private String textAlign;
    private String padding;
    private String margin;
    private String color;
    private String bgColor;
    private String borderBottom;

    public HTMLDiv() {

    }

    public HTMLDiv(String classNames) {
        this.classNames = classNames;

    }

    public HTMLDiv(String textAlign, String padding, String margin, String color, String bgColor, String borderBottom) {
        // left, 1.5px, 1.5px, black, white, 1px solid
        this.textAlign = textAlign;
        this.padding = padding;
        this.margin = margin;
        this.color = color;
        this.bgColor = bgColor;
        this.borderBottom = borderBottom;

    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Div");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "div");

        String cNames = getClassNames();
        String style = getStyle();

        if ( cNames != null ) {
            addTagAttribute("class", cNames);
        }

        if ( style != null ) {
            addTagAttribute("style", style);
        }

    }

    protected String getClassNames() {
        return classNames;
    }

    protected String getStyle() {
        StringBuilder styleBuffer = new StringBuilder();

        if ( textAlign != null ) {
            styleBuffer.append("text-align:").append(textAlign).append(";");
        }
        if ( padding != null ) {
            styleBuffer.append("padding:").append(padding).append(";");
        }
        if ( margin != null ) {
            styleBuffer.append("margin:").append(margin).append(";");
        }
        if ( color != null ) {
            styleBuffer.append("color:").append(color).append(";");
        }
        if ( bgColor != null ) {
            styleBuffer.append("background-color:").append(bgColor).append(";");
        }
        if ( borderBottom != null ) {
            styleBuffer.append("border-bottom:").append(borderBottom).append(";");
        }

        if ( styleBuffer.length() > 0 ) {
            return  styleBuffer.toString();
        } else {
            return null;
        }
    }

}
