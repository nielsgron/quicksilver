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

/**
 * @deprecated Use BSCheckLabel
 */
@Deprecated
public class BSFormLabel extends BSComponentContainer {

    private String labelString;
    private String forID;

    public BSFormLabel(String labelString, String forID) {
        this.labelString = labelString;
        this.forID = forID;
        add(new BSText(labelString));
    }

    @Override
    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Label");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "label");

        addTagAttribute("class", getClassNames());
        addTagAttribute("for", forID);

    }

    @Override
    protected String getClassNames() {
        StringBuilder cNames = new StringBuilder();

        cNames.append("form-check-label");

        return cNames.toString();
    }

}
