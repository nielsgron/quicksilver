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
    Example :

    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item active" aria-current="page">Home</li>
      </ol>
    </nav>

    W3Schools : NONE
    Bootstrap Docs : https://getbootstrap.com/docs/4.1/components/breadcrumb/
 */

public class BSBreadcrumb extends BSComponentContainer {

    public BSBreadcrumb() {

    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Breadcrumb");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "nav");

        addTagAttribute("aria-label", "breadcrumb");

    }

    public void renderBody(HtmlStream stream) {

        stream.writeln("<ol class=\"breadcrumb\">");
        super.renderBody(stream);
        stream.writeln("</ol>");

    }

}
