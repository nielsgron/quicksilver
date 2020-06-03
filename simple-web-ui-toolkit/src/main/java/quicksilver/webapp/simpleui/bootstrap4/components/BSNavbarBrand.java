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

import quicksilver.webapp.simpleui.html.components.HTMLComponent;


/**
 * @see W3Schools :
 * @see <a href='https://getbootstrap.com/docs/4.1/components/navbar/#brand'>Bootstrap Docs</a>
 */
public class BSNavbarBrand extends BSComponentContainer {

    private final String url;

    public BSNavbarBrand(String url, String text) {
        this(url, new BSText(text));
    }

    public BSNavbarBrand(String url, HTMLComponent body) {
        this.url = url;
        add(body);
    }

    @Override
    protected void defineAttributes() {
        putComponentAttribute(COMPONENT_ATTRIB_NAME, "NarBar Brand");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "a");
        putComponentAttribute(COMPONENT_ATTRIB_END_WITH_LINEBREAK, Boolean.FALSE);

        addTagAttribute("href", url);
        addTagAttribute("class", getClassNames());
    }

    @Override
    protected String getClassNames() {
        return "navbar-brand";
    }
}
