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

import quicksilver.webapp.simpleui.bootstrap4.components.BSBadge;
import quicksilver.webapp.simpleui.bootstrap4.components.BSComponent;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSText;

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
        panel.add(new BSBadge("Primary", BSComponent.Type.PRIMARY));
        panel.add(new BSBadge("Secondary", BSComponent.Type.SECONDARY));
        panel.add(new BSBadge("Sucess", BSComponent.Type.SUCCESS));
        panel.add(new BSBadge("Danger", BSComponent.Type.DANGER));
        panel.add(new BSBadge("Warning", BSComponent.Type.WARNING));
        panel.add(new BSBadge("Info", BSComponent.Type.INFO));
        panel.add(new BSBadge("Light", BSComponent.Type.LIGHT));
        panel.add(new BSBadge("Dark", BSComponent.Type.DARK));

        panel.add(new BSText("<br>"));

        return panel;
    }

}
