/*
 * Copyright 2018 Niels Gron All Rights Reserved.
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

package org.apache.webapp.simpleserver.controllers;

import org.apache.webapp.simpleui.HtmlPageBootstrapAppLayout;
import org.apache.webapp.simpleui.bootstrap4.components.BSForm;
import org.apache.webapp.simpleui.bootstrap4.components.BSInput;
import org.apache.webapp.simpleui.bootstrap4.components.BSNavItem;
import org.apache.webapp.simpleui.bootstrap4.components.BSNavbar;

public class SimpleDemoBasePage extends HtmlPageBootstrapAppLayout {

    protected BSNavbar createNavbar() {

        // Create a Navbar and set it in the viewport
        BSNavbar navBar = new BSNavbar( false,"Simple Demo");
        navBar.add(new BSNavItem("Components", "/components"));
        navBar.add(new BSNavItem("About", "/about"));

        BSForm searchForm = new BSForm(true, "/search", true);
        searchForm.add(new BSInput("text", "Search"));

        navBar.add(searchForm);

        return navBar;
    }

}
