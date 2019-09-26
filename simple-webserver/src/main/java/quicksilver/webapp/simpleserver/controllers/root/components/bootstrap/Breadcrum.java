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

public class Breadcrum extends AbstractComponentsBootstrapPage {

    public Breadcrum() {
        getSideBar().setActiveItem("Breadcrum");
    }

    protected BSPanel createContentPanelCenter() {

        BSPanel panel = new BSPanel();

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Breadcrum Examples", 4));
        panel.add(new HTMLThematicBreak());

        BSBreadcrumb crumb = new BSBreadcrumb();
        ((BSBreadcrumbItem)crumb.add(new BSBreadcrumbItem(true))).add(new BSText("Home"));
        panel.add(crumb);

        crumb = new BSBreadcrumb();
        ((BSBreadcrumbItem)crumb.add(new BSBreadcrumbItem(false))).add(new BSHyperlink("Home", "#"));
        ((BSBreadcrumbItem)crumb.add(new BSBreadcrumbItem(true))).add(new BSText("Library"));
        panel.add(crumb);

        crumb = new BSBreadcrumb();
        ((BSBreadcrumbItem)crumb.add(new BSBreadcrumbItem(false))).add(new BSHyperlink("Home", "#"));
        ((BSBreadcrumbItem)crumb.add(new BSBreadcrumbItem(false))).add(new BSHyperlink("Library", "#"));
        ((BSBreadcrumbItem)crumb.add(new BSBreadcrumbItem(true))).add(new BSText("Data"));
        panel.add(crumb);

        return panel;
    }

}
