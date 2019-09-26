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

package quicksilver.webapp.simpleui.icons;

import quicksilver.webapp.simpleui.HtmlStream;
import quicksilver.webapp.simpleui.html.components.HTMLImage;

public class FontAwesomeIcon extends HTMLImage {

    public static FontAwesomeIcon COG = new FontAwesomeIcon("cog");

    protected FontAwesomeIcon(String url) {
        super(url, "");
    }

    protected FontAwesomeIcon(String url, String alt) {
        super(url, alt);
    }

    protected FontAwesomeIcon(FontAwesomeIcon icon, String alt) {
        super(icon.url, alt);
    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "FontAwesomeIcon");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "i");

        addTagAttribute("class", getClassNames());

    }

    protected String getClassNames() {
        StringBuilder cNames = new StringBuilder();

        cNames.append("fas ");
        cNames.append("fa-");
        cNames.append(getURL());

        cNames.append(" fa-2x");

        return cNames.toString();
    }


    public static String getScriptLocal() {
        return "";
    }

    public static String getScriptCDN() {
        return "<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.5.0/css/all.css\" integrity=\"sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU\" crossorigin=\"anonymous\">";
    }

}