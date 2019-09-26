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

package quicksilver.webapp.simpleui.html.components;

import quicksilver.webapp.simpleui.HtmlStream;

public class HTMLSVG extends HTMLComponent {

    protected String url;
    protected String alt;

    public HTMLSVG(String url, String alt) {
        this.url = url;
        this.alt = alt;

    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "SVG");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "svg");

        addTagAttribute("width", "12");
        addTagAttribute("height", "12");
        addTagAttribute("fill", "none");
        addTagAttribute("stroke", "currentColor");
        addTagAttribute("stroke-width", "2");
        addTagAttribute("stroke-linecap", "round");
        addTagAttribute("stroke-linejoin", "round");

    }

    @Override
    public void renderBody(HtmlStream stream) {
        stream.write("<use xlink:href=\"" + url + "\">");
    }

}
