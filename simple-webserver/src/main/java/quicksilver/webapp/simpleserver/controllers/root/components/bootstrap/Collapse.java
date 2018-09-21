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

import quicksilver.webapp.simpleui.bootstrap4.components.BSButton;
import quicksilver.webapp.simpleui.bootstrap4.components.BSCard;
import quicksilver.webapp.simpleui.bootstrap4.components.BSCollapse;
import quicksilver.webapp.simpleui.bootstrap4.components.BSComponent;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSText;

public class Collapse extends AbstractComponentsBootstrapPage {

    public Collapse() {
        getSideBar().setActiveItem("Collapse");
    }

    protected BSPanel createContentPanelCenter() {

        BSPanel panel = new BSPanel();

        panel.add(new BSText("<br>"));
        panel.add(new BSText("List of Collapse Components"));
        panel.add(new BSText("<br>"));
        BSCollapse collapse = new BSCollapse(new BSCard("A card", null, "Collapsed cards may become visible again with a buton, as seen above"));
        panel.add(new BSButton("Link with href", BSComponent.Type.PRIMARY, false, collapse));
        panel.add(new BSText("<br>"));
        panel.add(collapse);

        return panel;
    }

}
