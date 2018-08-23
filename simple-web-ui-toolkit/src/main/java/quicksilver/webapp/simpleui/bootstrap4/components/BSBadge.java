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

public class BSBadge extends BSComponent {

    private String labelText;
    private String labelLink = null;
    private boolean isPill = false;

    public BSBadge(String txt) {
        this(txt, Type.PRIMARY);
    }
    public BSBadge(String txt, Type type) {
        this(txt, type, null, false);
    }
    public BSBadge(String txt, Type type, String link, boolean bPill) {
        labelText = txt;
        labelLink = link;
        isPill = bPill;
        setType(type);

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Badge");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        if ( labelLink == null ) {
            putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "span");
        } else {
            putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "a");
            addTagAttribute("href", labelLink);
        }

        if ( isPill ) {
            addTagAttribute("class", "badge badge-pill badge-" + getType().getTypeName());
        } else {
            addTagAttribute("class", "badge badge-" + getType().getTypeName());
        }

    }

    public void renderBody(HtmlStream stream) {
        stream.writeln(labelText);
    }

}
