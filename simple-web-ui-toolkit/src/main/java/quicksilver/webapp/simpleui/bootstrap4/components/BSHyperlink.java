/*
 * Copyright 2018-2020 Niels Gron and Contributors All Rights Reserved.
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

/*
    Example :

    W3Schools : https://www.w3schools.com/tags/tag_a.asp
    Bootstrap Docs : NONE
 */
public class BSHyperlink extends BSComponentContainer {

    private final String text;
    private final String url;

    public BSHyperlink(String text, String hyperlinkURL) {
        this.text = text;
        this.url = hyperlinkURL;
        add(new HTMLText(text));
    }

    @Override
    protected void defineAttributes() {
        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Hyperlink");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "a");
        putComponentAttribute(COMPONENT_ATTRIB_END_WITH_LINEBREAK, Boolean.FALSE);

        addTagAttribute("href", url);
        addTagAttribute("class", getClassNames());
    }

    @Override
    protected String getClassNames() {
        if (getType() != null) {
            return "text-" + getType().getTypeName();
        }

        return null;
    }

    public String getText() {
        return text;
    }

    public String getUrl() {
        return url;
    }

}
