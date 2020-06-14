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

import java.util.UUID;

/**
 * @see
 * <a href='https://www.w3schools.com/bootstrap4/bootstrap_collapse.asp'>W3Schools</a>
 * @see
 * <a href='https://getbootstrap.com/docs/4.1/components/collapse/'>Bootstrap
 * Docs</a>
 */
public class BSCollapse extends BSComponentContainer {

    private String id;

    public BSCollapse(BSComponent body) {
        this.id = "collapse_" + UUID.randomUUID().toString().replaceAll("-", "_");
        add(body);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BSCollapse id(String id) {
        setId(id);
        return this;
    }

    @Override
    protected void defineAttributes() {
        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Collapse");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "div");

        addTagAttribute("id", getId());
        addTagAttribute("class", getClassNames());
    }

    @Override
    protected String getClassNames() {
        return "collapse";
    }

}
