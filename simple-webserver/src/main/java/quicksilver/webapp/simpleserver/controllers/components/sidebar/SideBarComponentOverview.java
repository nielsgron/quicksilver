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

package quicksilver.webapp.simpleserver.controllers.components.sidebar;

import quicksilver.webapp.simpleui.bootstrap4.components.BSComponent;
import quicksilver.webapp.simpleui.bootstrap4.components.BSNavItem;
import quicksilver.webapp.simpleui.bootstrap4.components.BSNavPill;

public class SideBarComponentOverview extends BSNavPill {

    public SideBarComponentOverview() {
        super(BSComponent.VERTICAL_ALIGNMENT);

        this.add(new BSNavItem("Alerts", "/components/bootstrap/alerts"));
        this.add(new BSNavItem("Badges", "/components/bootstrap/badges"));
        this.add(new BSNavItem("Breadcrum", "/components/bootstrap/breadcrum"));
        this.add(new BSNavItem("Buttons", "/components/bootstrap/buttons"));
        this.add(new BSNavItem("Button group", "/components/bootstrap/buttongroup"));
        this.add(new BSNavItem("Card", "/components/bootstrap/card"));
        this.add(new BSNavItem("Carousel", "/components/bootstrap/carousel"));
        this.add(new BSNavItem("Collapse", "/components/bootstrap/collapse"));
        this.add(new BSNavItem("Dropdowns", "/components/bootstrap/dropdowns"));
        this.add(new BSNavItem("Forms", "/components/bootstrap/forms"));
        this.add(new BSNavItem("Input group", "/components/bootstrap/inputgroup"));
        this.add(new BSNavItem("Jumbotron", "/components/bootstrap/jumbotron"));
        this.add(new BSNavItem("List Group", "/components/bootstrap/listgroup"));
        this.add(new BSNavItem("Modal", "/components/bootstrap/modal"));
        this.add(new BSNavItem("Navs", "/components/bootstrap/navs"));
        this.add(new BSNavItem("Navbar", "/components/bootstrap/navbar"));
        this.add(new BSNavItem("Pagination", "/components/bootstrap/pagination"));
        this.add(new BSNavItem("Popovers", "/components/bootstrap/popovers"));
        this.add(new BSNavItem("Progress", "/components/bootstrap/progress"));
        this.add(new BSNavItem("Scrollspy", "/components/bootstrap/scrollspy"));
        this.add(new BSNavItem("Tooltips", "/components/bootstrap/tooltips"));

        this.setActiveItem("Alerts");

    }

}
