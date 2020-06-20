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
package quicksilver.webapp.simpleserver.controllers.root.components.bootstrap;

import quicksilver.webapp.simpleui.bootstrap4.components.BSComponent;
import quicksilver.webapp.simpleui.bootstrap4.components.BSNav;
import quicksilver.webapp.simpleui.bootstrap4.components.BSNavItem;
import quicksilver.webapp.simpleui.bootstrap4.components.BSNavPill;
import quicksilver.webapp.simpleui.bootstrap4.components.BSNavTab;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.html.components.HTMLHeading;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import quicksilver.webapp.simpleui.html.components.HTMLThematicBreak;

public class Navs extends AbstractComponentsBootstrapPage {

    public Navs() {
        getSideBar().setActiveItem("Navs");
    }

    @Override
    protected BSPanel createContentPanelCenter() {

        BSPanel panel = new BSPanel();

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Navs Examples", 4));
        panel.add(new HTMLThematicBreak());

        // Navs Examples
        {
            panel.add(new HTMLHeading("Base nav", 5));
            panel.add(new HTMLThematicBreak());

            BSNav nav = new BSNav();
            nav.add(new BSNavItem("Active", "#").active(true));
            nav.add(new BSNavItem("Link", "#"));
            nav.add(new BSNavItem("Link", "#"));
            nav.add(new BSNavItem("Disabled", "#").disabled(true));

            panel.add(nav);
            panel.add(new HTMLLineBreak());
        }

        {
            panel.add(new HTMLHeading("Vertical", 5));
            panel.add(new HTMLThematicBreak());

            BSNav nav = new BSNav(BSComponent.Alignment.VERTICAL);
            nav.add(new BSNavItem("Active", "#").active(true));
            nav.add(new BSNavItem("Link", "#"));
            nav.add(new BSNavItem("Link", "#"));
            nav.add(new BSNavItem("Disabled", "#").disabled(true));

            panel.add(nav);
            panel.add(new HTMLLineBreak());
        }

        {
            panel.add(new HTMLHeading("Tabs", 5));
            panel.add(new HTMLThematicBreak());

            BSNavTab nav = new BSNavTab();
            nav.add(new BSNavItem("Active", "#").active(true));
            nav.add(new BSNavItem("Link", "#"));
            nav.add(new BSNavItem("Link", "#"));
            nav.add(new BSNavItem("Disabled", "#").disabled(true));

            panel.add(nav);
            panel.add(new HTMLLineBreak());
        }

        {
            panel.add(new HTMLHeading("Pills", 5));
            panel.add(new HTMLThematicBreak());

            BSNavPill nav = new BSNavPill();
            nav.add(new BSNavItem("Active", "#").active(true));
            nav.add(new BSNavItem("Link", "#"));
            nav.add(new BSNavItem("Link", "#"));
            nav.add(new BSNavItem("Disabled", "#").disabled(true));

            panel.add(nav);
            panel.add(new HTMLLineBreak());
        }

        {
            panel.add(new HTMLHeading("Fill", 5));
            panel.add(new HTMLThematicBreak());

            BSNavPill nav = new BSNavPill();
            //TODO: there's also nav-justify but I don't see the difference?
            nav.setFill(true);
            nav.add(new BSNavItem("Active", "#").active(true));
            nav.add(new BSNavItem("Link", "#"));
            nav.add(new BSNavItem("Link", "#"));
            nav.add(new BSNavItem("Disabled", "#").disabled(true));

            panel.add(nav);
            panel.add(new HTMLLineBreak());
        }
        return panel;
    }

}
