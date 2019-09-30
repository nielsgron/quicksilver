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

    private static String padding = "padding: 1px 1px 1px 5px;";

    public SideBarComponentOverview() {
        super(BSComponent.VERTICAL_ALIGNMENT);

        this.add(new BSNavItem("Alerts", "/components/bootstrap/alerts") {
            protected String getStyle() { return padding; }
        });
        this.add(new BSNavItem("Badges", "/components/bootstrap/badges"){
            protected String getStyle() { return padding; }
        });
        this.add(new BSNavItem("Breadcrum", "/components/bootstrap/breadcrum"){
            protected String getStyle() { return padding; }
        });
        this.add(new BSNavItem("Buttons", "/components/bootstrap/buttons"){
            protected String getStyle() { return padding; }
        });
        this.add(new BSNavItem("Button group", "/components/bootstrap/buttongroup"){
            protected String getStyle() { return padding; }
        });
        this.add(new BSNavItem("Card", "/components/bootstrap/card"){
            protected String getStyle() { return padding; }
        });
        this.add(new BSNavItem("Carousel", "/components/bootstrap/carousel"){
            protected String getStyle() { return padding; }
        });
        this.add(new BSNavItem("Collapse", "/components/bootstrap/collapse"){
            protected String getStyle() { return padding; }
        });
        this.add(new BSNavItem("Dropdowns", "/components/bootstrap/dropdowns"){
            protected String getStyle() { return padding; }
        });
        this.add(new BSNavItem("Forms", "/components/bootstrap/forms") {
            protected String getStyle() { return padding; }
        });
        this.add(new BSNavItem("Input group", "/components/bootstrap/inputgroup"){
            protected String getStyle() { return padding; }
        });
        this.add(new BSNavItem("Jumbotron", "/components/bootstrap/jumbotron"){
            protected String getStyle() { return padding; }
        });
        this.add(new BSNavItem("List Group", "/components/bootstrap/listgroup"){
            protected String getStyle() { return padding; }
        });
        this.add(new BSNavItem("Modal", "/components/bootstrap/modal"){
            protected String getStyle() { return padding; }
        });
        this.add(new BSNavItem("Navs", "/components/bootstrap/navs"){
            protected String getStyle() { return padding; }
        });
        this.add(new BSNavItem("Navbar", "/components/bootstrap/navbar"){
            protected String getStyle() { return padding; }
        });
        this.add(new BSNavItem("Pagination", "/components/bootstrap/pagination"){
            protected String getStyle() { return padding; }
        });
        this.add(new BSNavItem("Popovers", "/components/bootstrap/popovers"){
            protected String getStyle() { return padding; }
        });
        this.add(new BSNavItem("Progress", "/components/bootstrap/progress"){
            protected String getStyle() { return padding; }
        });
        this.add(new BSNavItem("Scrollspy", "/components/bootstrap/scrollspy"){
            protected String getStyle() { return padding; }
        });
        this.add(new BSNavItem("Tooltips", "/components/bootstrap/tooltips"){
            protected String getStyle() { return padding; }
        });

        this.setActiveItem("Alerts");

    }

}
