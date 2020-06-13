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

import quicksilver.webapp.simpleui.bootstrap4.components.BSButton;
import quicksilver.webapp.simpleui.bootstrap4.components.BSCard;
import quicksilver.webapp.simpleui.bootstrap4.components.BSCollapse;
import quicksilver.webapp.simpleui.bootstrap4.components.BSComponent;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSText;
import quicksilver.webapp.simpleui.html.components.HTMLHeading;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import quicksilver.webapp.simpleui.html.components.HTMLThematicBreak;

public class Collapse extends AbstractComponentsBootstrapPage {

    public Collapse() {
        getSideBar().setActiveItem("Collapse");
    }

    protected BSPanel createContentPanelCenter() {

        BSPanel panel = new BSPanel();

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Example Collapse Component", 4));
        panel.add(new HTMLThematicBreak());

        BSCollapse collapse = new BSCollapse(new BSCard().body("A card", null, "Collapsed cards may become visible again with a buton, as seen above"));

        panel.add(new BSButton("Link with href", BSComponent.Type.PRIMARY, false, collapse));
        panel.add(new HTMLLineBreak(1));

        panel.add(collapse);

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Multiple Targets", 4));
        panel.add(new HTMLThematicBreak());

        // Multiple Targets

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Accordian Example", 4));
        panel.add(new HTMLThematicBreak());

        // Accordian Example



        return panel;
    }

}
