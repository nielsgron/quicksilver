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

import quicksilver.webapp.simpleui.html.components.HTMLComponent;

/**
 * @see
 * <a href='https://getbootstrap.com/docs/4.1/components/scrollspy/'>Bootstrap
 * Docs</a>
 */
public class BSScrollspy extends BSComponentContainer {

    private final String targetId;
    private final String height;

    public BSScrollspy(String targetId, String height) {
        this.targetId = targetId;
        this.height = height;
    }

    public BSScrollspy(HTMLComponent nav, String height) {
        this(nav.getId(), height);
    }

    @Override
    protected void defineAttributes() {
        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Scrollspy");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "div");

        addTagAttribute("data-spy", "scroll");
        addTagAttribute("data-target", "#" + targetId);
        addTagAttribute("data-offset", "0");

        addTagAttribute("style", "overflow-y: scroll; position: relative; height:" + height);
    }
}
