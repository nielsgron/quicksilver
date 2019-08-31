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

package quicksilver.webapp.simpleserver.controllers.root.components.bootstrap;

import quicksilver.webapp.simpleserver.controllers.components.sidebar.SideBarComponentOverview;
import quicksilver.webapp.simpleserver.controllers.root.components.AbstractComponentsPage;
import quicksilver.webapp.simpleui.bootstrap4.components.BSNavPill;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSText;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;

public abstract class AbstractComponentsBootstrapPage extends AbstractComponentsPage {

    private BSNavPill sideBar;

    public AbstractComponentsBootstrapPage() {
        getComponentNavTab().setActiveItem("Bootstrap");
    }

    protected BSPanel createContentPanelLeft() {

        if ( sideBar == null ) {
            sideBar = new SideBarComponentOverview();
        }

        BSPanel panel = new BSPanel();
        panel.add(new HTMLLineBreak(1));
        BSText gettingStartedHeader = new BSText("Getting Started");
        panel.add(gettingStartedHeader);
        panel.add(new HTMLLineBreak(2));

        panel.add(sideBar);

        panel.add(new HTMLLineBreak(1));

        return panel;
    }

    public BSNavPill getSideBar() {
        return sideBar;
    }

}
