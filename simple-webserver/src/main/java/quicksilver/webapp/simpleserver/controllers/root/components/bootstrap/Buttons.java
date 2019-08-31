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
import quicksilver.webapp.simpleui.bootstrap4.components.BSComponent;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSText;
import quicksilver.webapp.simpleui.html.components.HTMLHeading;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import quicksilver.webapp.simpleui.html.components.HTMLThematicBreak;

public class Buttons extends AbstractComponentsBootstrapPage {

    public Buttons() {
        getSideBar().setActiveItem("Buttons");
    }

    protected BSPanel createContentPanelCenter() {

        BSPanel panel = new BSPanel();

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Button Examples", 4));
        panel.add(new HTMLThematicBreak());

        panel.add(new BSButton("Primary", BSComponent.Type.PRIMARY));
        panel.add(new BSButton("Secondary", BSComponent.Type.SECONDARY));
        panel.add(new BSButton("Success", BSComponent.Type.SUCCESS));
        panel.add(new BSButton("Danger", BSComponent.Type.DANGER));
        panel.add(new BSButton("Warning", BSComponent.Type.WARNING));
        panel.add(new BSButton("Info", BSComponent.Type.INFO));
        panel.add(new BSButton("Light", BSComponent.Type.LIGHT));
        panel.add(new BSButton("Dark", BSComponent.Type.DARK));
        panel.add(new BSButton("Link", BSComponent.Type.LINK));

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Outline Button Examples", 4));
        panel.add(new HTMLThematicBreak());

        panel.add(new BSButton("Primary", BSComponent.Type.PRIMARY, true));
        panel.add(new BSButton("Secondary", BSComponent.Type.SECONDARY, true));
        panel.add(new BSButton("Success", BSComponent.Type.SUCCESS, true));
        panel.add(new BSButton("Danger", BSComponent.Type.DANGER, true));
        panel.add(new BSButton("Warning", BSComponent.Type.WARNING, true));
        panel.add(new BSButton("Info", BSComponent.Type.INFO, true));
        panel.add(new BSButton("Light", BSComponent.Type.LIGHT, true));
        panel.add(new BSButton("Dark", BSComponent.Type.DARK, true));
        panel.add(new BSButton("Link", BSComponent.Type.LINK, true));

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Button Size Examples", 4));
        panel.add(new HTMLThematicBreak());

        // Button Sizes

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Block Button Examples", 4));
        panel.add(new HTMLThematicBreak());

        // Block Buttons

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Button Active & Disabled State Examples", 4));
        panel.add(new HTMLThematicBreak());

        // Button Active & Disabled States

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Button Toggle State Examples", 4));
        panel.add(new HTMLThematicBreak());

        // Button Toggle States

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Check Box and Radio Button Examples", 4));
        panel.add(new HTMLThematicBreak());

        // Checkbox and Radio Button


        return panel;
    }

}
