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

/*
    Example :

    W3Schools :
    Bootstrap Docs :
 */

public class BSNavItem extends BSComponent {

    private String itemName;
    private String urlReference;
    private boolean isActive;

    public BSNavItem(String name, String url) {
        itemName = name;
        urlReference = url;

    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Nav Item");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "li");

        addTagAttribute("class", "nav-item");

    }

    @Override
    public void renderBody(HtmlStream stream) {
        if ( isActive ) {
            stream.writeln("  <a class=\"nav-link active\" href=\"" + urlReference + "\">" + itemName + "</a>");
        } else {
            stream.writeln("  <a class=\"nav-link\" href=\"" + urlReference + "\">" + itemName + "</a>");
        }
    }

    public String getName() {
        return itemName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getURL() {
        return urlReference;
    }

    public void setURL(String url) {
        urlReference = url;
    }

}
