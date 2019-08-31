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

import quicksilver.webapp.simpleui.bootstrap4.components.*;
import quicksilver.webapp.simpleui.html.components.HTMLHeading;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import quicksilver.webapp.simpleui.html.components.HTMLThematicBreak;

public class ButtonGroup extends AbstractComponentsBootstrapPage {

    public ButtonGroup() {
        getSideBar().setActiveItem("Button group");
    }

    protected BSPanel createContentPanelCenter() {

        BSPanel panel = new BSPanel();

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Button Group Examples", 4));
        panel.add(new HTMLThematicBreak());

        BSButtonGroup g = new BSButtonGroup();
        g.add(new BSButton("Button 1"));
        g.add(new BSButton("Button 2"));
        g.add(new BSButton("Button 3"));

        panel.add(g);

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Button Toolbar Examples", 4));
        panel.add(new HTMLThematicBreak());

        // Button Toolbar
        BSButtonGroup g1 = new BSButtonGroup();
        g1.add(new BSButton("1"));
        g1.add(new BSButton("2"));
        g1.add(new BSButton("3"));

        BSButtonGroup g2 = new BSButtonGroup();
        g2.add(new BSButton("4"));
        g2.add(new BSButton("5"));
        g2.add(new BSButton("6"));

        BSButtonGroup g3 = new BSButtonGroup();
        g3.add(new BSButton("7"));
        g3.add(new BSButton("8"));
        g3.add(new BSButton("9"));

        BSButtonToolbar toolbar = new BSButtonToolbar();
        toolbar.add(g1);
        toolbar.add(g2);
        toolbar.add(g3);

        panel.add(toolbar);

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Button Group Size Examples", 4));
        panel.add(new HTMLThematicBreak());

        // Button Group Size

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Button Group Nesting Examples", 4));
        panel.add(new HTMLThematicBreak());

        // Button Group Nesting

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Vertical Button Group Examples", 4));
        panel.add(new HTMLThematicBreak());

        // Vertical Button Groups

        return panel;
    }

}
