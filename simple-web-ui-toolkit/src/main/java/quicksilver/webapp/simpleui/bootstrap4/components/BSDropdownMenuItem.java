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

public class BSDropdownMenuItem extends BSComponentContainer {

    private String href;
    private boolean isActive;
    private boolean isDisabled;

    public BSDropdownMenuItem(String name, String href) {
        this(name, href, false, false);
    }

    public BSDropdownMenuItem(String name, String href, boolean isActive, boolean isDisabled) {
        this.href = href;
        this.isActive = isActive;
        this.isDisabled = isDisabled;
        add(new BSText(name));
    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Dropdown-Item");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "a");

        addTagAttribute("class", getClassNames());
        addTagAttribute("href", href);

    }

    protected String getClassNames() {
        StringBuilder cNames = new StringBuilder();

        cNames.append("dropdown-item");

        if ( isActive ) {
            cNames.append(" active");
        }
        if ( isDisabled ) {
            cNames.append(" disabled");
        }

        return cNames.toString();
    }

}
