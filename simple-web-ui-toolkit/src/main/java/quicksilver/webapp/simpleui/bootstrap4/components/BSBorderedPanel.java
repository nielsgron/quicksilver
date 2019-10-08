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

import quicksilver.webapp.simpleui.html.components.HTMLDiv;

public class BSBorderedPanel extends HTMLDiv {

    private String padding = "1.5rem";

    private String borderStyle = "solid";
    private String borderWidth = ".15rem";
    private String borderColor = "#f2f2f2";
    private String borderRadius;

    private String backgroundColor;

    public BSBorderedPanel() {

    }

    public BSBorderedPanel(String borderWidth) {
        setWidth(borderWidth);
    }

    public BSBorderedPanel(String borderWidth, String borderColor, String borderStyle, String backgroundColor) {
        setWidth(borderWidth);
        setColor(borderColor);
        setStyle(borderStyle);
        setBackgroundColor(backgroundColor);
    }

    protected String getStyle() {
        StringBuilder styleBuffer = new StringBuilder();

        if ( padding != null ) {
            styleBuffer.append("padding:").append(padding).append(";");
        }
        if ( borderStyle != null ) {
            styleBuffer.append("border-style:").append(borderStyle).append(";");
        }
        if ( borderWidth != null ) {
            styleBuffer.append("border-width:").append(borderWidth).append(";");
        }
        if ( borderColor != null ) {
            styleBuffer.append("border-color:").append(borderColor).append(";");
        }
        if ( borderRadius != null ) {
            styleBuffer.append("border-radius:").append(borderRadius).append(";");
        }
        if ( backgroundColor != null ) {
            styleBuffer.append("background-color:").append(backgroundColor).append(";");
        }

        return styleBuffer.toString();
    }

    public void setStyle(String value) {
        borderStyle = value;
    }

    public void setWidth(String value) {
        borderWidth = value;
    }

    public void setColor(String value) {
        borderColor = value;
    }

    public void setRadius(String value) {
        borderRadius = value;
    }

    public void setBackgroundColor(String value) {
        backgroundColor = value;
    }

}
