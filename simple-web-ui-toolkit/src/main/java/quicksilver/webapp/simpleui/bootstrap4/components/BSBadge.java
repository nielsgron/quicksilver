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

/*
    Example : <span class="badge badge-primary">Primary</span>
            : <span class="badge badge-pill badge-primary">Primary</span>
            : <a href="#" class="badge badge-primary">Primary</a>

    W3Schools : https://www.w3schools.com/bootstrap4/bootstrap_badges.asp
    Bootstrap Docs : https://getbootstrap.com/docs/4.1/components/badge/
 */

public class BSBadge extends BSComponent {

    private String labelText;
    private String labelLink = null;
    private boolean isPill = false;

    public BSBadge(String txt) {
        this(txt, Type.PRIMARY);
    }
    public BSBadge(String txt, boolean bPill) {
        this(txt, Type.PRIMARY, null, bPill);
    }
    public BSBadge(String txt, Type type) {
        this(txt, type, null, false);
    }
    public BSBadge(String txt, Type type, String link, boolean bPill) {
        labelText = txt;
        labelLink = link;
        isPill = bPill;
        setType(type);

    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Badge");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        if ( labelLink == null ) {
            putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "span");
        } else {
            putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "a");
            addTagAttribute("href", labelLink);
        }

        addTagAttribute("class", getClassNames());

    }

    protected String getClassNames() {
        StringBuilder cNames = new StringBuilder();

        cNames.append("badge");

        if ( isPill ) {
            cNames.append(" badge-pill");
        }

        cNames.append(" badge-").append(getType().getTypeName());

        return cNames.toString();
    }


    public void renderBody(HtmlStream stream) {
        stream.writeln(labelText);
    }

}
