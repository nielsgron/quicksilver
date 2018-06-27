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

package org.apache.webapp.simpleserver.controllers.root.components.bootstrap;

import org.apache.webapp.simpleui.bootstrap4.components.BSBadge;
import org.apache.webapp.simpleui.bootstrap4.components.BSPanel;
import org.apache.webapp.simpleui.bootstrap4.components.BSText;

public class Badges extends AbstractComponentsBootstrapPage {

    public Badges() {
        getSideBar().setActiveItem("Badges");
    }

    protected BSPanel createContentPanelCenter() {

        BSPanel panel = new BSPanel();

        panel.add(new BSText("<br>"));
        panel.add(new BSText("List of Badges Components"));

        panel.add(new BSText("<br>"));
        panel.add(new BSText("<br>"));

        panel.add(new BSBadge("Default"));
        panel.add(new BSBadge("Primary", BSBadge.PRIMARY));
        panel.add(new BSBadge("Secondary", BSBadge.SECONDARY));
        panel.add(new BSBadge("Sucess", BSBadge.SUCCESS));
        panel.add(new BSBadge("Danger", BSBadge.DANGER));
        panel.add(new BSBadge("Warning", BSBadge.WARNING));
        panel.add(new BSBadge("Info", BSBadge.INFO));
        panel.add(new BSBadge("Light", BSBadge.LIGHT));
        panel.add(new BSBadge("Dark", BSBadge.DARK));

        panel.add(new BSText("<br>"));

        return panel;
    }

}
