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

import quicksilver.webapp.simpleui.HtmlStream;

public class BSInputSelect extends BSComponent {

    private String id;
    private boolean isMultiple;
    private String[] selectList;

    public BSInputSelect(String id, boolean isMultiple, String[] selectList) {
        this.id = id;
        this.isMultiple = isMultiple;
        this.selectList = selectList;
    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Select");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "select");

        if ( isMultiple ) {
            addTagAttribute("multiple", null);
        }
        addTagAttribute("class", getClassNames());
        addTagAttribute("id", id);

    }

    protected String getClassNames() {
        StringBuilder cNames = new StringBuilder();

        cNames.append("form-control");

        return cNames.toString();
    }

    public void renderBody(HtmlStream stream) {

        for ( int i = 0; i < selectList.length; i++ ) {
            stream.write("<option>");
            stream.write(selectList[i]);
            stream.writeln("</option>");
        }

    }

}
