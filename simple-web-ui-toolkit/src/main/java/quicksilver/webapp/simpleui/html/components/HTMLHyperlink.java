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

public class HTMLHyperlink extends HTMLComponentContainer {

    private String url;

    public HTMLHyperlink(String url) {
        this.url = url;

    }

    public HTMLHyperlink(String text, String url) {
        this.url = url;
        add(new HTMLText(text));

    }

    public HTMLHyperlink(HTMLComponent component, String url) {
        this.url = url;
        add(component);

    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Hyperlink");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "a");
        putComponentAttribute(COMPONENT_ATTRIB_END_WITH_LINEBREAK, Boolean.FALSE);

        addTagAttribute("href", url);

    }

}
