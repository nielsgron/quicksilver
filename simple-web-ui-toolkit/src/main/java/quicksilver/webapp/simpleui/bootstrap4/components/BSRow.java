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

import quicksilver.webapp.simpleui.html.components.HTMLComponent;

/*
    Example :

    W3Schools : https://www.w3schools.com/bootstrap4/bootstrap_tables.asp
    Bootstrap Docs : https://getbootstrap.com/docs/4.1/content/tables/
 */

public class BSRow extends BSComponentContainer {

    public BSRow(int columns) {
        initColumns(columns);
        defineAttributes();
    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Row");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "div");

        addTagAttribute("class", "row");

    }

    private void initColumns(int columns) {
        for ( int i = 0; i < columns; i++ ) {
            add(new BSColumn());
        }
    }

    // Override add() method so that we only add BSColumn components
    public HTMLComponent add(HTMLComponent component) {
        if ( component instanceof BSColumn ) {
            super.add(component);
        }
        return component;
    }

    public BSColumn getColumn(int index) {
        return (BSColumn)super.get(index);
    }

}
