/*
 * Copyright 2018 Niels Gron All Rights Reserved.
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

package org.apache.webapp.simpleui;

import org.apache.webapp.simpleui.bootstrap4.components.BSComponentContainer;
import org.apache.webapp.simpleui.bootstrap4.components.BSPanel;
import org.apache.webapp.simpleui.bootstrap4.layouts.BSBorderLayout;

public abstract class HtmlPageBootstrapAppLayout extends HtmlPageBootstrap {

    private BSPanel panelTop;
    private BSPanel panelCenter;
    private BSPanel panelBottom;
    private BSPanel panelLeft;
    private BSPanel panelRight;

    protected BSComponentContainer createContentPane() {
        BSPanel panel = new BSPanel();
        panel.setLayout(new BSBorderLayout());

        panelTop = createContentPanelTop();
        panelCenter = createContentPanelCenter();
        panelBottom = createContentPanelBottom();
        panelLeft = createContentPanelLeft();
        panelRight = createContentPanelRight();

        panel.add(panelTop, BSBorderLayout.NORTH);
        panel.add(panelCenter, BSBorderLayout.CENTER);
        panel.add(panelBottom, BSBorderLayout.SOUTH);
        panel.add(panelLeft, BSBorderLayout.WEST);
        panel.add(panelRight, BSBorderLayout.EAST);

        return panel;
    }

    protected BSPanel createContentPanelTop() {
        return null;
    }

    protected BSPanel createContentPanelCenter() {
        return null;
    }

    protected BSPanel createContentPanelBottom() {
        return null;
    }

    protected BSPanel createContentPanelLeft() {
        return null;
    }

    protected BSPanel createContentPanelRight() {
        return null;
    }

    public BSPanel getContentPanelTop() {
        return panelTop;
    }

    public BSPanel getContentPanelCenter() {
        return panelCenter;
    }

    public BSPanel getContentPanelBottom() {
        return panelBottom;
    }

    public BSPanel getContentPanelLeft() {
        return panelLeft;
    }

    public BSPanel getContentPanelRight() {
        return panelRight;
    }

}
