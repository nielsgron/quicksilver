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

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import quicksilver.webapp.simpleui.html.components.HTMLComponent;

/**
 * @see
 * <a href='https://getbootstrap.com/docs/4.1/components/list-group/'>Bootstrap
 * Docs</a>
 */
public class BSListGroupItem extends BSComponentContainer {

    private String itemName;
    private String urlReference;
    private boolean isActive;
    private boolean disabled;

    public BSListGroupItem(@NonNull String name) {
        this(name, null);
    }

    public BSListGroupItem(@NonNull String name, @Nullable String url) {
        this(name, url, new BSText(name));
    }

    public BSListGroupItem(@NonNull String name, @Nullable String url, HTMLComponent ...children) {
        this.itemName = name;
        this.urlReference = url;
        for(HTMLComponent c : children) {
            add(c);
        }
    }

    @Override
    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "List Group Item");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);

        addTagAttribute("class", getClassNames());

        if (urlReference == null) {
            putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "li");
        } else {
            putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "a");
            addTagAttribute("href", urlReference);
        }
    }

    @Override
    protected String getClassNames() {
        StringBuilder sb = new StringBuilder();

        sb.append("list-group-item");
        if (urlReference != null) {
            sb.append(" list-group-item-action");
        }
        if (isActive()) {
            sb.append(" active");
        }
        if (disabled) {
            //XXX: Bootstrap limitation: won't work for <a> links
            sb.append(" disabled");
        }
        if (getType() != null && getType() != DEFAULT_TYPE) {
            sb.append(" list-group-item-").append(getType().getTypeName());
        }

        return sb.toString();
    }

    public String getName() {
        return itemName;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public BSListGroupItem disabled(boolean disabled) {
        setDisabled(disabled);
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public BSListGroupItem active(boolean active) {
        setActive(active);
        return this;
    }

}
