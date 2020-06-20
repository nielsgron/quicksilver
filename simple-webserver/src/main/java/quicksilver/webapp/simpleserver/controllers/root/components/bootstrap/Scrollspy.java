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

import quicksilver.webapp.simpleui.bootstrap4.components.BSListGroup;
import quicksilver.webapp.simpleui.bootstrap4.components.BSListGroupItem;
import quicksilver.webapp.simpleui.bootstrap4.components.BSNavItem;
import quicksilver.webapp.simpleui.bootstrap4.components.BSNavbar;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSScrollspy;
import quicksilver.webapp.simpleui.bootstrap4.components.BSText;
import quicksilver.webapp.simpleui.bootstrap4.layouts.BSGridLayout;
import quicksilver.webapp.simpleui.html.components.HTMLComponent;
import quicksilver.webapp.simpleui.html.components.HTMLHeading;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import quicksilver.webapp.simpleui.html.components.HTMLThematicBreak;

public class Scrollspy extends AbstractComponentsBootstrapPage {

    public Scrollspy() {
        getSideBar().setActiveItem("Scrollspy");
    }

    @Override
    protected BSPanel createContentPanelCenter() {

        BSPanel panel = new BSPanel();

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Scrollspy Examples", 4));
        panel.add(new HTMLThematicBreak());

        BSNavbar nav = new BSNavbar(true, "MyBrand");
        nav.id("navbar-example");
        nav.add(new BSNavItem("Pricing", "#pricing-nav"));
        nav.add(new BSNavItem("Projects", "#projects-nav"));
        nav.add(new BSNavItem("About", "#about-nav"));

        // Scrollspy Examples
        BSScrollspy spy = createSpy(nav, "-nav");

        panel.add(nav);

        panel.add(spy);

        panel.add(new HTMLHeading("List group", 5));
        panel.add(new HTMLThematicBreak());

        BSListGroup group = new BSListGroup();
        group.add(new BSListGroupItem("Pricing", "#pricing-list"));
        group.add(new BSListGroupItem("Projects", "#projects-list"));
        group.add(new BSListGroupItem("About", "#about-list"));
        group.id("examplegroup");

        BSPanel p = new BSPanel();
        p.setLayout(new BSGridLayout(1, 2));

        p.add(group);
        p.add(createSpy(group, "-list"));

        panel.add(p);

        return panel;
    }

    BSScrollspy createSpy(HTMLComponent target, String idSuffix) {
        BSScrollspy spy = new BSScrollspy(target, "250px");

        spy.add(new HTMLHeading("Pricing", 1).id("pricing" + idSuffix));
        spy.add(new BSText("Out pricing is the best." + generateText(12)));
        spy.add(new HTMLLineBreak(2));

        spy.add(new HTMLHeading("Projects", 1).id("projects" + idSuffix));
        spy.add(new BSText("Previous projects are listed bellow..." + generateText(12)));
        spy.add(new HTMLLineBreak(2));

        spy.add(new HTMLHeading("About", 1).id("about" + idSuffix));
        spy.add(new BSText("We are a small company built on a dream to..." + generateText(12)));
        spy.add(new HTMLLineBreak(2));

        return spy;
    }

}
