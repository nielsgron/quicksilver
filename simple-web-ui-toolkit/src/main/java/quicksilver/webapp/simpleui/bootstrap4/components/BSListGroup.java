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

public class BSListGroup extends BSComponentContainer {

    public BSListGroup() {
        defineAttributes();
    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "List Group");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "ul");

        addTagAttribute("class", "list-group");

    }

    public void setActiveItem(String name) {
        for (int i = 0; i < children.size(); i++) {
            if ( (children.get(i) instanceof BSListGroupItem) ) {
                BSListGroupItem item = (BSListGroupItem)children.get(i);
                if (item.getName().equals(name)) {
                    item.setActive(true);
                } else {
                    item.setActive(false);
                }
            }
        }
    }

}
