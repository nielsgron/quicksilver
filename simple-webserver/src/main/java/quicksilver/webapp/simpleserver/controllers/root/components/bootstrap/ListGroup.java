/*
 * Copyright 2018-2020 Niels Gron and Contributors All Rights Reserved.
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

import quicksilver.webapp.simpleui.bootstrap4.components.BSListGroup;
import quicksilver.webapp.simpleui.bootstrap4.components.BSListGroupItem;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.html.components.HTMLHeading;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import quicksilver.webapp.simpleui.html.components.HTMLThematicBreak;

public class ListGroup  extends AbstractComponentsBootstrapPage {

    public ListGroup() {
        getSideBar().setActiveItem("List Group");
    }

    @Override
    protected BSPanel createContentPanelCenter() {

        BSPanel panel = new BSPanel();

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("List Group Examples", 4));
        panel.add(new HTMLThematicBreak());

        // List Group Examples

        BSListGroup listGroup = new BSListGroup();
        listGroup.add(new BSListGroupItem("Item 1")
                .active(true));
        listGroup.add(new BSListGroupItem("Item 2")
                .disabled(true));
        listGroup.add(new BSListGroupItem("Item 3"));

        panel.add(listGroup);
        panel.add(new HTMLLineBreak(2));

        listGroup = new BSListGroup();
        BSListGroupItem lgi1 = new BSListGroupItem("Link 1", "/1");
        lgi1.setActive(true);
        listGroup.add(lgi1);

        listGroup.add(new BSListGroupItem("Link 2", "/2"));
        listGroup.add(new BSListGroupItem("Link 3", "/3"));

        panel.add(listGroup);

        return panel;
    }

}
