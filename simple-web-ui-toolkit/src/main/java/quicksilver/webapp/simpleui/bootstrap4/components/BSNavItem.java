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
 * @see
 * <a href='https://getbootstrap.com/docs/4.1/components/navs/'>Bootstrap
 * Docs</a>
 */
public class BSNavItem extends BSComponentContainer {

    private String itemName;
    private String urlReference;
    private final BSNavLink link;

    public BSNavItem(String name, String url) {
        itemName = name;
        urlReference = url;
        add(link = new BSNavLink(name, url) {
            //XXX: For some reason we use the same style, twice. I suspect this could be deleted.
            @Override
            protected String getStyle() {
                return BSNavItem.this.getStyle();
            }
        });
    }

    @Override
    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Nav Item");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "li");

        addTagAttribute("class", getClassNames());

        String style = getStyle();
        if (style != null) {
            addTagAttribute("style", style);
        }

    }

    @Override
    protected String getClassNames() {
        return "nav-item";
    }

    public String getName() {
        return itemName;
    }

    public String getURL() {
        return urlReference;
    }

    public void setURL(String url) {
        urlReference = url;
    }

    public boolean isActive() {
        return link.isActive();
    }

    public void setActive(boolean active) {
        link.setActive(active);
    }

    public BSNavItem active(boolean active) {
        setActive(active);
        return this;
    }

    public BSNavItem disabled(boolean b) {
        setDisabled(b);
        return this;
    }

    public void setDisabled(boolean disabled) {
        link.setDisabled(disabled);
    }

    public boolean isDisabled() {
        return link.isDisabled();
    }

}
