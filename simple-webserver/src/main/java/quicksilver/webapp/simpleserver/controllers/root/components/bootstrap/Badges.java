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

public class Badges extends AbstractComponentsBootstrapPage {

    public Badges() {
        getSideBar().setActiveItem("Badges");
    }

    protected BSPanel createContentPanelCenter() {

        BSPanel panel = new BSPanel();

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Badge Examples", 4));
        panel.add(new HTMLThematicBreak());

        HTMLHeading h = new HTMLHeading("Example Heading ", 1);
        h.add(new BSBadge("New", BSComponent.Type.SECONDARY));
        panel.add(h);

        panel.add(new HTMLLineBreak(1));

        h = new HTMLHeading("Example Heading ", 2);
        h.add(new BSBadge("New", BSComponent.Type.SECONDARY));
        panel.add(h);

        panel.add(new HTMLLineBreak(1));

        h = new HTMLHeading("Example Heading ", 3);
        h.add(new BSBadge("New", BSComponent.Type.SECONDARY));
        panel.add(h);

        panel.add(new HTMLLineBreak(1));

        h = new HTMLHeading("Example Heading ", 4);
        h.add(new BSBadge("New", BSComponent.Type.SECONDARY));
        panel.add(h);

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Button & Link Examples", 4));
        panel.add(new HTMLThematicBreak());

        BSButton b = new BSButton("Notifications ");
        b.add(new BSBadge("4", BSComponent.Type.LIGHT));
        panel.add(b);

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Variation Examples", 4));
        panel.add(new HTMLThematicBreak());

        panel.add(new BSBadge("Default"));
        panel.add(new BSBadge("Primary", BSComponent.Type.PRIMARY));
        panel.add(new BSBadge("Secondary", BSComponent.Type.SECONDARY));
        panel.add(new BSBadge("Sucess", BSComponent.Type.SUCCESS));
        panel.add(new BSBadge("Danger", BSComponent.Type.DANGER));
        panel.add(new BSBadge("Warning", BSComponent.Type.WARNING));
        panel.add(new BSBadge("Info", BSComponent.Type.INFO));
        panel.add(new BSBadge("Light", BSComponent.Type.LIGHT));
        panel.add(new BSBadge("Dark", BSComponent.Type.DARK));

        panel.add(new HTMLLineBreak(1));

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Pill Badge Examples", 4));
        panel.add(new HTMLThematicBreak());

        panel.add(new BSBadge("Default", true));
        panel.add(new BSBadge("Primary", BSComponent.Type.PRIMARY, null, true));
        panel.add(new BSBadge("Secondary", BSComponent.Type.SECONDARY, null, true));
        panel.add(new BSBadge("Sucess", BSComponent.Type.SUCCESS, null, true));
        panel.add(new BSBadge("Danger", BSComponent.Type.DANGER, null, true));
        panel.add(new BSBadge("Warning", BSComponent.Type.WARNING, null, true));
        panel.add(new BSBadge("Info", BSComponent.Type.INFO, null, true));
        panel.add(new BSBadge("Light", BSComponent.Type.LIGHT, null, true));
        panel.add(new BSBadge("Dark", BSComponent.Type.DARK, null, true));

        panel.add(new HTMLLineBreak(1));

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Link Examples", 4));
        panel.add(new HTMLThematicBreak());

        panel.add(new BSBadge("Default", true));
        panel.add(new BSBadge("Primary", BSComponent.Type.PRIMARY, "#", true));
        panel.add(new BSBadge("Secondary", BSComponent.Type.SECONDARY, "#", true));
        panel.add(new BSBadge("Sucess", BSComponent.Type.SUCCESS, "#", true));
        panel.add(new BSBadge("Danger", BSComponent.Type.DANGER, "#", true));
        panel.add(new BSBadge("Warning", BSComponent.Type.WARNING, "#", true));
        panel.add(new BSBadge("Info", BSComponent.Type.INFO, "#", true));
        panel.add(new BSBadge("Light", BSComponent.Type.LIGHT, "#", true));
        panel.add(new BSBadge("Dark", BSComponent.Type.DARK, "#", true));

        panel.add(new HTMLLineBreak(1));

        return panel;
    }

}
