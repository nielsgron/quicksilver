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

package quicksilver.webapp.simpleui.bootstrap4.components;

/*
    Example :

    W3Schools : https://www.w3schools.com/bootstrap4/bootstrap_tables.asp
    Bootstrap Docs : https://getbootstrap.com/docs/4.1/content/tables/
 */

public class BSColumn extends BSComponentContainer {

    private int prop_columnWeight = -1;

    public BSColumn() {

    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Column");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "div");

        addTagAttribute("class", "column");

    }

    public void setColumnWeight(int columnWeight) {
        prop_columnWeight = columnWeight;

        if ( prop_columnWeight <= 0 || prop_columnWeight > 12 ) {
            removeTagAttribute("class");
            addTagAttribute("class", "column");
        } else {
            removeTagAttribute("class");
            addTagAttribute("class", "col-md-" + prop_columnWeight);
        }

    }

}
