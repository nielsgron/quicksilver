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
import quicksilver.webapp.simpleui.bootstrap4.layouts.BSLayoutManager;
import quicksilver.webapp.simpleui.html.components.HTMLComponent;

import java.util.ArrayList;

public abstract class BSComponentContainer extends BSComponent {

    private BSLayoutManager layoutManager = null; // BSFlowLayout.INSTANCE;
    protected ArrayList<HTMLComponent> children = new ArrayList<HTMLComponent>();

    public HTMLComponent add(HTMLComponent component) {
        if ( component == null ) {
            return null;
        }
        children.add(component);

        if ( layoutManager != null ) {
            layoutManager.add(component);
        }
        return component;
    }

    public HTMLComponent add(HTMLComponent component, Object constraint) {
        if ( component == null ) {
            return null;
        }
        children.add(component);

        if ( layoutManager != null ) {
            layoutManager.add(component, constraint);
        }
        return component;
    }

    public void remove(HTMLComponent component) {
        if ( component == null ) {
            return;
        }
        children.remove(component);
    }

    public HTMLComponent get(int index) {
        if ( children.size() == 0 ) {
            return null;
        }
        if ( index >= children.size() ) {
            return null;
        }
        return children.get(index);
    }

    public int getChildrenCount() {
        return children.size();
    }

    public void setLayout(BSLayoutManager layout) {
        layoutManager = layout;
    }
    public BSLayoutManager getLayout() {
        return layoutManager;
    }

    protected void defineAttributes() {

    }

    public void renderBody(HtmlStream stream) {

        if ( layoutManager != null ) {
            layoutManager.render(stream);
        } else {
            // Render each of the children components
            for (int i = 0; i < children.size(); i++) {
                children.get(i).render(stream);
            }
        }

    }

}
