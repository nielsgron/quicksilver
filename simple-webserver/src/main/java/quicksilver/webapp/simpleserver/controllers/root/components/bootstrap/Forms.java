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

import quicksilver.webapp.simpleui.bootstrap4.components.BSCheckInput;
import quicksilver.webapp.simpleui.bootstrap4.components.BSCheckLabel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSForm;
import quicksilver.webapp.simpleui.bootstrap4.components.BSFormButton;
import quicksilver.webapp.simpleui.bootstrap4.components.BSInputDescription;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSText;
import quicksilver.webapp.simpleui.bootstrap4.components.BSTextInput;
import quicksilver.webapp.simpleui.html.components.HTMLHeading;
import quicksilver.webapp.simpleui.html.components.HTMLLabel;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import quicksilver.webapp.simpleui.html.components.HTMLThematicBreak;

public class Forms extends AbstractComponentsBootstrapPage {

    public Forms() {
        getSideBar().setActiveItem("Forms");
    }

    protected BSPanel createContentPanelCenter() {

        BSPanel panel = new BSPanel();

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Forms Components", 4));
        panel.add(new HTMLThematicBreak());

        BSForm form = new BSForm(null, true);
        form.addAsGroup(new HTMLLabel("Email address", "emailInput"),
                new BSTextInput("email", "Enter email", null, "emailHelp", "emailInput"),
                new BSInputDescription("We'll never share your email with anyone else.", "emailHelp"));
        form.addAsGroup(new HTMLLabel("Password", "passwordInput"),
                new BSTextInput("password", "Password", null, null, "passwordInput")
                );
        form.addAsCheckGroup(new BSCheckInput("checkbox", null, null, null, "exampleCheck1"),
                new BSCheckLabel("Check", "exampleCheck1")
                );
            form.add(new BSFormButton("Submit"));

        panel.add(form);

        return panel;
    }

}
