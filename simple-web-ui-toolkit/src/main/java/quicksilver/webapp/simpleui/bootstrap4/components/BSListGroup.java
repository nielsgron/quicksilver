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
 * <a href='https://getbootstrap.com/docs/4.1/components/list-group/'>Bootstrap
 * Docs</a>
 */
public class BSListGroup extends BSComponentContainer {

    private boolean flush;

    public BSListGroup() {

    }

    @Override
    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "List Group");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "ul");

        addTagAttribute("class", getClassNames());

    }

    @Override
    protected String getClassNames() {
        if (flush) {
            return "list-group list-group-flush";
        }
        return "list-group";
    }

    /**
     *
     * @param flush if true, remove some borders to better fit in a parent
     * container
     */
    public void setFlush(boolean flush) {
        this.flush = flush;
    }

    public boolean isFlush() {
        return flush;
    }

    public BSListGroup flush(boolean flush) {
        setFlush(flush);
        return this;
    }

    public void setActiveItem(String name) {
        for (int i = 0; i < children.size(); i++) {
            if ( (children.get(i) instanceof BSListGroupItem) ) {
                BSListGroupItem item = (BSListGroupItem)children.get(i);
                if (item.getName().equals(name)) {
                    item.setActive(true);
                } else {
                    item.setActive(false);
                }
            }
        }
    }

}
