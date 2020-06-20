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

import org.checkerframework.checker.nullness.qual.NonNull;

/*
    Example :

    W3Schools :
    Bootstrap Docs :
 */

public class BSNav extends BSComponentContainer {

    public static enum Style {
        BASE, TAB, PILL
    }

    private Style prop_style = Style.BASE;
    private Alignment prop_alignment = Alignment.HORIZONTAL;

    public BSNav(@NonNull Style style, Alignment alignment) {
        prop_style = style;
        prop_alignment = alignment;

    }

    @Override
    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Nav");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "ul");

        addTagAttribute("class", getClassNames());
    }

    @Override
    protected String getClassNames() {
        String classList = "nav";

        if ( prop_alignment == Alignment.VERTICAL ) {
            classList += " flex-column";
        }

        switch (prop_style){
            case BASE:
                break;
            case TAB:
                classList += " nav-tabs";
                break;
            case PILL:
                classList += " nav-pills";
                break;
        }

        return classList;
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