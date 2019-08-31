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

public class BSNav extends BSComponentContainer {

    public static final int STYLE_BASE = 0;
    public static final int STYLE_TAB = 1;
    public static final int STYLE_PILL = 2;

    private int prop_style = STYLE_BASE;
    private int prop_alignment = HORIZONTAL_ALIGNMENT;

    public BSNav(int style, int alignment) {
        prop_style = style;
        prop_alignment = alignment;

    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Nav");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "ul");

        String classList = "nav";

        if ( prop_alignment == VERTICAL_ALIGNMENT ) {
            classList += " flex-column";
        }

        if ( prop_style == STYLE_BASE ) {

        } else if ( prop_style == STYLE_TAB ) {
            classList += " nav-tabs";
        } else if ( prop_style == STYLE_PILL ) {
            classList += " nav-pills";
        }

        addTagAttribute("class", classList);

    }

    public void setActiveItem(String name) {
        for (int i = 0; i < children.size(); i++) {
            if ( (children.get(i) instanceof BSNavItem) ) {
                BSNavItem item = (BSNavItem)children.get(i);
                if (item.getName().equals(name)) {
                    item.setActive(true);
                } else {
                    item.setActive(false);
                }
            }
        }
    }

}