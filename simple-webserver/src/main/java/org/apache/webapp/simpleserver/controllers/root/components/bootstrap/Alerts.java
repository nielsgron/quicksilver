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

import org.apache.webapp.simpleui.bootstrap4.components.BSAlert;
import org.apache.webapp.simpleui.bootstrap4.components.BSComponentType;
import org.apache.webapp.simpleui.bootstrap4.components.BSPanel;
import org.apache.webapp.simpleui.bootstrap4.components.BSText;

public class Alerts extends AbstractComponentsBootstrapPage {

    public Alerts() {
        getSideBar().setActiveItem("Alerts");
    }

    protected BSPanel createContentPanelCenter() {

        BSPanel panel = new BSPanel();

        panel.add(new BSText("<br>"));
        panel.add(new BSText("List of Alert Components"));

        BSAlert alert = new BSAlert(BSComponentType.PRIMARY);
        alert.add(new BSText("This is a primary alert—check it out!"));

        panel.add(new BSText("<br>"));
        panel.add(new BSText("<br>"));
        panel.add(alert);

        alert = new BSAlert(BSComponentType.SECONDARY);
        alert.add(new BSText("This is a secondary alert—check it out!"));
        panel.add(alert);

        alert = new BSAlert(BSComponentType.SUCCESS);
        alert.add(new BSText("This is a success alert—check it out!"));
        panel.add(alert);

        alert = new BSAlert(BSComponentType.DANGER);
        alert.add(new BSText("This is a danger alert—check it out!"));
        panel.add(alert);

        alert = new BSAlert(BSComponentType.WARNING);
        alert.add(new BSText("This is a warning alert—check it out!"));
        panel.add(alert);

        alert = new BSAlert(BSComponentType.INFO);
        alert.add(new BSText("This is a info alert—check it out!"));
        panel.add(alert);

        alert = new BSAlert(BSComponentType.LIGHT);
        alert.add(new BSText("This is a light alert—check it out!"));
        panel.add(alert);

        alert = new BSAlert(BSComponentType.DARK);
        alert.add(new BSText("This is a dark alert—check it out!"));
        panel.add(alert);

        return panel;
    }

}
