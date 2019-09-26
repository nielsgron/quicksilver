/*
 * Copyright 2018-2019 Niels Gron and Contributors All Rights Reserved.
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

public class Dropdowns extends AbstractComponentsBootstrapPage {

    public Dropdowns() {
        getSideBar().setActiveItem("Dropdowns");
    }

    protected BSPanel createContentPanelCenter() {

        BSPanel panel = new BSPanel();

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Dropdown Examples - Single Button", 4));
        panel.add(new HTMLThematicBreak());

        // Single Button
        BSDropdown dropdown = new BSDropdown(true);

        BSButton b = new BSButton("Dropdown Button", BSComponent.Type.PRIMARY, false, null, false, true, false);
        dropdown.add(b);

        BSDropdownMenu menu = new BSDropdownMenu();
        menu.add(new BSDropdownMenuItem("Action", "#"));
        menu.add(new BSDropdownMenuItem("Another action", "#"));
        menu.add(new BSDropdownMenuItem("Something else here", "#"));
        menu.add(new BSDropdownDivider());
        menu.add(new BSDropdownMenuItem("Separated Link", "#"));
        dropdown.add(menu);
        panel.add(dropdown);

        dropdown = new BSDropdown(true);

        b = new BSButton("Dropdown Link", BSComponent.Type.SECONDARY, false, "http://www.duckduckgo.com", false, true, false);
        dropdown.add(b);

        menu = new BSDropdownMenu();
        menu.add(new BSDropdownMenuItem("Link Action", "#"));
        menu.add(new BSDropdownMenuItem("Link Another action", "#"));
        menu.add(new BSDropdownMenuItem("Link Something else here", "#"));
        menu.add(new BSDropdownDivider());
        menu.add(new BSDropdownMenuItem("Link Separated Link", "#"));
        dropdown.add(menu);
        panel.add(dropdown);

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Split Button", 4));
        panel.add(new HTMLThematicBreak());

        // Split Button

        dropdown = new BSDropdown(true);

        b = new BSButton("Dropdown Split", BSComponent.Type.PRIMARY, false, null, false, false, false, false);
        dropdown.add(b);

        b = new BSButton("", BSComponent.Type.PRIMARY, false, null, false, true, true, false);
        dropdown.add(b);

        menu = new BSDropdownMenu();
        menu.add(new BSDropdownMenuItem("Link Action", "#"));
        menu.add(new BSDropdownMenuItem("Link Another action", "#"));
        menu.add(new BSDropdownMenuItem("Link Something else here", "#"));
        menu.add(new BSDropdownDivider());
        menu.add(new BSDropdownMenuItem("Separated Link", "#"));
        dropdown.add(menu);

        panel.add(dropdown);

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Sizing", 4));
        panel.add(new HTMLThematicBreak());

        // Sizing

        dropdown = new BSDropdown(true);

        b = new BSButton("Large Dropdown Button", BSComponent.Type.PRIMARY, false, null, false, true, false);
        b.setSize(BSComponent.Size.LARGE);
        dropdown.add(b);

        menu = new BSDropdownMenu();
        menu.add(new BSDropdownMenuItem("Action", "#"));
        menu.add(new BSDropdownMenuItem("Another action", "#"));
        menu.add(new BSDropdownMenuItem("Something else here", "#"));
        menu.add(new BSDropdownDivider());
        menu.add(new BSDropdownMenuItem("Separated Link", "#"));
        dropdown.add(menu);
        panel.add(dropdown);

        dropdown = new BSDropdown(true);

        b = new BSButton("Small Dropdown Link", BSComponent.Type.SECONDARY, false, "http://www.duckduckgo.com", false, true, false);
        b.setSize(BSComponent.Size.SMALL);
        dropdown.add(b);

        menu = new BSDropdownMenu();
        menu.add(new BSDropdownMenuItem("Link Action", "#"));
        menu.add(new BSDropdownMenuItem("Link Another action", "#"));
        menu.add(new BSDropdownMenuItem("Link Something else here", "#"));
        menu.add(new BSDropdownDivider());
        menu.add(new BSDropdownMenuItem("Link Separated Link", "#"));
        dropdown.add(menu);
        panel.add(dropdown);

        panel.add(new HTMLLineBreak(2 ));

        dropdown = new BSDropdown(true);

        b = new BSButton("Large Dropdown Split", BSComponent.Type.PRIMARY, false, null, false, false, false, false);
        b.setSize(BSComponent.Size.LARGE);
        dropdown.add(b);

        b = new BSButton("", BSComponent.Type.PRIMARY, false, null, false, true, true, false);
        b.setSize(BSComponent.Size.LARGE);
        dropdown.add(b);

        menu = new BSDropdownMenu();
        menu.add(new BSDropdownMenuItem("Link Action", "#"));
        menu.add(new BSDropdownMenuItem("Link Another action", "#"));
        menu.add(new BSDropdownMenuItem("Link Something else here", "#"));
        menu.add(new BSDropdownDivider());
        menu.add(new BSDropdownMenuItem("Separated Link", "#"));
        dropdown.add(menu);

        panel.add(dropdown);


        dropdown = new BSDropdown(true);

        b = new BSButton("Small Dropdown Split", BSComponent.Type.PRIMARY, false, null, false, false, false, false);
        b.setSize(BSComponent.Size.SMALL);
        dropdown.add(b);

        b = new BSButton("", BSComponent.Type.PRIMARY, false, null, false, true, true, false);
        b.setSize(BSComponent.Size.SMALL);
        dropdown.add(b);

        menu = new BSDropdownMenu();
        menu.add(new BSDropdownMenuItem("Link Action", "#"));
        menu.add(new BSDropdownMenuItem("Link Another action", "#"));
        menu.add(new BSDropdownMenuItem("Link Something else here", "#"));
        menu.add(new BSDropdownDivider());
        menu.add(new BSDropdownMenuItem("Separated Link", "#"));
        dropdown.add(menu);

        panel.add(dropdown);


        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Directions - Dropup", 4));
        panel.add(new HTMLThematicBreak());

        // Directions - Dropup
        dropdown = new BSDropdown(true);
        dropdown.setDirection(BSDropdown.Direction.UP);

        b = new BSButton("Dropup", BSComponent.Type.PRIMARY, false, null, false, true, false);
        dropdown.add(b);

        menu = new BSDropdownMenu();
        menu.add(new BSDropdownMenuItem("Action", "#"));
        menu.add(new BSDropdownMenuItem("Another action", "#"));
        menu.add(new BSDropdownMenuItem("Something else here", "#"));
        menu.add(new BSDropdownDivider());
        menu.add(new BSDropdownMenuItem("Separated Link", "#"));
        dropdown.add(menu);
        panel.add(dropdown);

        dropdown = new BSDropdown(true);
        dropdown.setDirection(BSDropdown.Direction.UP);

        b = new BSButton("Split dropup", BSComponent.Type.PRIMARY, false, null, false, false, false, false);
        dropdown.add(b);

        b = new BSButton("", BSComponent.Type.PRIMARY, false, null, false, true, true, false);
        dropdown.add(b);

        menu = new BSDropdownMenu();
        menu.add(new BSDropdownMenuItem("Link Action", "#"));
        menu.add(new BSDropdownMenuItem("Link Another action", "#"));
        menu.add(new BSDropdownMenuItem("Link Something else here", "#"));
        menu.add(new BSDropdownDivider());
        menu.add(new BSDropdownMenuItem("Separated Link", "#"));
        dropdown.add(menu);

        panel.add(dropdown);

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Directions - Dropright", 4));
        panel.add(new HTMLThematicBreak());

        // Directions - Dropright
        dropdown = new BSDropdown(true);
        dropdown.setDirection(BSDropdown.Direction.RIGHT);

        b = new BSButton("Dropright", BSComponent.Type.PRIMARY, false, null, false, true, false);
        dropdown.add(b);

        menu = new BSDropdownMenu();
        menu.add(new BSDropdownMenuItem("Action", "#"));
        menu.add(new BSDropdownMenuItem("Another action", "#"));
        menu.add(new BSDropdownMenuItem("Something else here", "#"));
        menu.add(new BSDropdownDivider());
        menu.add(new BSDropdownMenuItem("Separated Link", "#"));
        dropdown.add(menu);
        panel.add(dropdown);

        dropdown = new BSDropdown(true);
        dropdown.setDirection(BSDropdown.Direction.RIGHT);

        b = new BSButton("Split dropright", BSComponent.Type.PRIMARY, false, null, false, false, false, false);
        dropdown.add(b);

        b = new BSButton("", BSComponent.Type.PRIMARY, false, null, false, true, true, false);
        dropdown.add(b);

        menu = new BSDropdownMenu();
        menu.add(new BSDropdownMenuItem("Link Action", "#"));
        menu.add(new BSDropdownMenuItem("Link Another action", "#"));
        menu.add(new BSDropdownMenuItem("Link Something else here", "#"));
        menu.add(new BSDropdownDivider());
        menu.add(new BSDropdownMenuItem("Separated Link", "#"));
        dropdown.add(menu);

        panel.add(dropdown);


        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Directions - Dropleft", 4));
        panel.add(new HTMLThematicBreak());

        // Directions - Dropleft
        dropdown = new BSDropdown(true);
        dropdown.setDirection(BSDropdown.Direction.LEFT);

        b = new BSButton("Dropleft", BSComponent.Type.PRIMARY, false, null, false, true, false);
        dropdown.add(b);

        menu = new BSDropdownMenu();
        menu.add(new BSDropdownMenuItem("Action", "#"));
        menu.add(new BSDropdownMenuItem("Another action", "#"));
        menu.add(new BSDropdownMenuItem("Something else here", "#"));
        menu.add(new BSDropdownDivider());
        menu.add(new BSDropdownMenuItem("Separated Link", "#"));
        dropdown.add(menu);
        panel.add(dropdown);

        dropdown = new BSDropdown(true);
        dropdown.setDirection(BSDropdown.Direction.LEFT);

        b = new BSButton("Split dropleft", BSComponent.Type.PRIMARY, false, null, false, false, false, false);
        dropdown.add(b);

        b = new BSButton("", BSComponent.Type.PRIMARY, false, null, false, true, true, false);
        dropdown.add(b);

        menu = new BSDropdownMenu();
        menu.add(new BSDropdownMenuItem("Link Action", "#"));
        menu.add(new BSDropdownMenuItem("Link Another action", "#"));
        menu.add(new BSDropdownMenuItem("Link Something else here", "#"));
        menu.add(new BSDropdownDivider());
        menu.add(new BSDropdownMenuItem("Separated Link", "#"));
        dropdown.add(menu);

        panel.add(dropdown);


        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Active", 4));
        panel.add(new HTMLThematicBreak());

        // Active
        dropdown = new BSDropdown(true);

        b = new BSButton("Dropdown - Active Menu Item", BSComponent.Type.PRIMARY, false, null, false, true, false);
        dropdown.add(b);

        menu = new BSDropdownMenu();
        menu.add(new BSDropdownMenuItem("Action", "#"));
        menu.add(new BSDropdownMenuItem("Another action", "#"));
        menu.add(new BSDropdownMenuItem("Something else here", "#", true, false));
        menu.add(new BSDropdownDivider());
        menu.add(new BSDropdownMenuItem("Separated Link", "#"));
        dropdown.add(menu);
        panel.add(dropdown);


        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Disabled", 4));
        panel.add(new HTMLThematicBreak());

        // Disabled
        dropdown = new BSDropdown(true);

        b = new BSButton("Dropdown - Disabled Menu Item", BSComponent.Type.PRIMARY, false, null, false, true, false);
        dropdown.add(b);

        menu = new BSDropdownMenu();
        menu.add(new BSDropdownMenuItem("Action", "#"));
        menu.add(new BSDropdownMenuItem("Another action", "#"));
        menu.add(new BSDropdownMenuItem("Something else here", "#", false, true));
        menu.add(new BSDropdownDivider());
        menu.add(new BSDropdownMenuItem("Separated Link", "#"));
        dropdown.add(menu);
        panel.add(dropdown);

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Menu Alignment", 4));
        panel.add(new HTMLThematicBreak());

        // Menu Alignment
        dropdown = new BSDropdown(true);

        b = new BSButton("Dropdown - Right Aligned Menu", BSComponent.Type.PRIMARY, false, null, false, true, false);
        dropdown.add(b);

        menu = new BSDropdownMenu(true);
        menu.add(new BSDropdownMenuItem("Action", "#"));
        menu.add(new BSDropdownMenuItem("Another action", "#"));
        menu.add(new BSDropdownMenuItem("Something else here", "#"));
        menu.add(new BSDropdownDivider());
        menu.add(new BSDropdownMenuItem("Separated Link", "#"));
        dropdown.add(menu);
        panel.add(dropdown);

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Menu content - Headers", 4));
        panel.add(new HTMLThematicBreak());

        // Menu content - Headers
        dropdown = new BSDropdown(true);

        b = new BSButton("Dropdown - Header in Menu", BSComponent.Type.PRIMARY, false, null, false, true, false);
        dropdown.add(b);

        menu = new BSDropdownMenu(true);
        menu.add(new BSDropdownMenuHeader("My Header", 6));
        menu.add(new BSDropdownMenuItem("Action", "#"));
        menu.add(new BSDropdownMenuItem("Another action", "#"));
        menu.add(new BSDropdownMenuItem("Something else here", "#"));
        menu.add(new BSDropdownDivider());
        menu.add(new BSDropdownMenuItem("Separated Link", "#"));
        dropdown.add(menu);
        panel.add(dropdown);

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Menu content - Dividers", 4));
        panel.add(new HTMLThematicBreak());

        // Menu content - Dividers
        dropdown = new BSDropdown(true);

        b = new BSButton("Dropdown - Menu with Separator", BSComponent.Type.PRIMARY, false, null, false, true, false);
        dropdown.add(b);

        menu = new BSDropdownMenu(true);
        menu.add(new BSDropdownMenuItem("Action", "#"));
        menu.add(new BSDropdownMenuItem("Another action", "#"));
        menu.add(new BSDropdownMenuItem("Something else here", "#"));
        menu.add(new BSDropdownDivider());
        menu.add(new BSDropdownMenuItem("Separated Link", "#"));
        dropdown.add(menu);
        panel.add(dropdown);

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Menu content - Text", 4));
        panel.add(new HTMLThematicBreak());

        // Menu content - Text

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Menu content - Forms", 4));
        panel.add(new HTMLThematicBreak());

        // Menu content - Forms

        return panel;
    }

}
