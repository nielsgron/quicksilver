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
import quicksilver.webapp.simpleui.html.components.HTMLHyperlink;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import quicksilver.webapp.simpleui.html.components.HTMLThematicBreak;

public class Alerts extends AbstractComponentsBootstrapPage {

    public Alerts() {
        getSideBar().setActiveItem("Alerts");
    }

    protected BSPanel createContentPanelCenter() {

        BSPanel panel = new BSPanel();

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Alert Examples", 4));
        panel.add(new HTMLThematicBreak());

        BSAlert alert = new BSAlert(BSComponent.Type.PRIMARY);
        alert.add(new BSText("This is a primary alert—check it out!"));

        panel.add(alert);

        alert = new BSAlert(BSComponent.Type.SECONDARY);
        alert.add(new BSText("This is a secondary alert—check it out!"));
        panel.add(alert);

        alert = new BSAlert(BSComponent.Type.SUCCESS);
        alert.add(new BSText("This is a success alert—check it out!"));
        panel.add(alert);

        alert = new BSAlert(BSComponent.Type.DANGER);
        alert.add(new BSText("This is a danger alert—check it out!"));
        panel.add(alert);

        alert = new BSAlert(BSComponent.Type.WARNING);
        alert.add(new BSText("This is a warning alert—check it out!"));
        panel.add(alert);

        alert = new BSAlert(BSComponent.Type.INFO);
        alert.add(new BSText("This is a info alert—check it out!"));
        panel.add(alert);

        alert = new BSAlert(BSComponent.Type.LIGHT);
        alert.add(new BSText("This is a light alert—check it out!"));
        panel.add(alert);

        alert = new BSAlert(BSComponent.Type.DARK);
        alert.add(new BSText("This is a dark alert—check it out!"));
        panel.add(alert);

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Content & Link Example", 4));
        panel.add(new HTMLThematicBreak());

        alert = new BSAlert(BSComponent.Type.PRIMARY);
        alert.add(new HTMLHeading("Well Done!", 4));
        alert.add(new BSText("A simple info alert with "));
        alert.add(new HTMLHyperlink("an example link", "#"));
        alert.add(new BSText(". Give it a click if you like."));

        alert.add(new HTMLThematicBreak());

        alert.add(new BSText("Whenever you need to, be sure to use margin utilities to keep things nice and tidy."));

        panel.add(alert);

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Dismissable Example", 4));
        panel.add(new HTMLThematicBreak());

        alert = new BSAlert(BSComponent.Type.WARNING, true);
        alert.add(new BSText("Holy guacamole! You should check in on some of those fields below."));
        alert.add(new BSButtonClose());

        panel.add(alert);

        panel.add(new HTMLLineBreak(1));

        return panel;
    }

}
