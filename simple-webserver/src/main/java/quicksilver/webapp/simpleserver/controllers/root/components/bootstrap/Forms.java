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

        BSForm form = new BSForm(null, false);

        // Input Checkbox
        form.addAsCheckGroup(new BSInputCheckbox(null, null, null, "exampleCheck1"),
                new BSCheckLabel("Check-1", "exampleCheck1")
        );

        // Input Radio
        form.addAsCheckGroup(new BSInputRadio(null, null, null, "exampleRadio1"),
                new BSCheckLabel("Radio-1", "exampleRadio1")
        );
        form.addAsCheckGroup(new BSInputRadio(null, null, null, "exampleRadio2"),
                new BSCheckLabel("Radio-2", "exampleRadio2")
        );

        // Input Text
        form.addAsGroup(new HTMLLabel("Username", "usernameInput"),
                new BSInputText("Username", null, "usernameHelp", "usernameInput"),
                new BSInputHelpText("Username.", "usernameHelp"));

        // Input Email
        form.addAsGroup(new HTMLLabel("Email address", "emailInput"),
                new BSInputEmail("Enter email", null, "emailHelp", "emailInput"),
                new BSInputHelpText("We'll never share your email with anyone else.", "emailHelp"));

        // Input Password
        form.addAsGroup(new HTMLLabel("Password", "passwordInput"),
                new BSInputPassword("Password", null, "passwordHelp", "passwordInput"),
                new BSInputHelpText("Password is encrypted.", "passwordHelp"));

        // Input Select
        form.addAsGroup(new HTMLLabel("Select Options", "selectInput"),
                new BSInputSelect("SelectInput", false, new String[] { "1", "2", "3", "4"}),
                new BSInputHelpText("You choose.", "selectHelp"));

        // Input Range
        form.addAsGroup(new HTMLLabel("Range", "rangeInput"),
                new BSInputRange("Choose Range", null, "rangeHelp", "rangeInput"),
                new BSInputHelpText("You must choose a range.", "rangeHelp"));

        // Input TextArea
        form.addAsGroup(new HTMLLabel("Text Area", "textAreaInput"),
                new BSInputTextArea("textAreaInput", 4),
                new BSInputHelpText("Write your story.", "textAreaHelp"));

        // Input File
        form.addAsGroup(new HTMLLabel("Choose File", "fileInput"),
                new BSInputFile("Choose File", null, "fileHelp", "fileInput"),
                new BSInputHelpText("Browse for the file and choose it.", "fileHelp"));


        form.add(new BSFormButton("Submit"));

        BSBorderedPanel bPanel = new BSBorderedPanel(".15rem", "#f2f2f2", "solid", null);
        bPanel.add(form);
        panel.add(bPanel);

        panel.add(new HTMLLineBreak(1));

        return panel;
    }

}
