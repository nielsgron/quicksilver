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

/*
    Example :

    W3Schools :
    Bootstrap Docs :
 */

public class BSListGroupItem extends BSComponentContainer {

    private String itemName;
    private String urlReference;
    private boolean isActive;

    public BSListGroupItem(String name) {
        this(name, null);
    }

    public BSListGroupItem(String name, String url) {
        itemName = name;
        urlReference = url;
        add(new BSText(name));
        defineAttributes();
    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "List Group Item");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);

        if ( urlReference == null ) {
            putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "li");

            addTagAttribute("class", "list-group-item");
        } else {
            putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "a");

            if ( isActive() ) {
                addTagAttribute("href", urlReference );
                addTagAttribute("class", "list-group-item list-group-item-action active");
            } else {
                addTagAttribute("href", urlReference );
                addTagAttribute("class", "list-group-item list-group-item-action");
            }
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

        removeTagAttribute("class");

        if ( isActive() ) {
            addTagAttribute("class", "list-group-item list-group-item-action active");
        } else {
            addTagAttribute("class", "list-group-item list-group-item-action");
        }

    }


}
