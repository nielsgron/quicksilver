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

package quicksilver.webapp.simpleserver.controllers.components.navs;

import quicksilver.webapp.simpleui.bootstrap4.components.BSForm;
import quicksilver.webapp.simpleui.bootstrap4.components.BSInput;
import quicksilver.webapp.simpleui.bootstrap4.components.BSNavItem;
import quicksilver.webapp.simpleui.bootstrap4.components.BSNavbar;

public class NavSite extends BSNavbar {

    public NavSite() {
        super( false, "WebApp Demo" );

        this.add(new BSNavItem("Components", "/components/bootstrap"));
        this.add(new BSNavItem("About", "/about/project"));

        BSForm searchForm = new BSForm(true, "/search", true);
        searchForm.add(new BSInput("text", "Search..."));

        this.add(searchForm);

    }

}
