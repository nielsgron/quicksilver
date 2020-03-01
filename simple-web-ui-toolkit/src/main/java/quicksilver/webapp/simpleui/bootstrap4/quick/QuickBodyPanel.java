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

package quicksilver.webapp.simpleui.bootstrap4.quick;

import quicksilver.webapp.simpleui.bootstrap4.components.*;
import quicksilver.webapp.simpleui.bootstrap4.layouts.BSBorderLayout;
import quicksilver.webapp.simpleui.bootstrap4.layouts.BSGridLayout;
import quicksilver.webapp.simpleui.html.components.HTMLComponent;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;

import java.util.ArrayList;

public class QuickBodyPanel extends BSPanel {

    private QuickSidebar sideBar;
    private ArrayList<BSComponent> components = new ArrayList<BSComponent>();

    public QuickBodyPanel() {


    }

    public void addSideBarMenu(String[] sidebarNames, String[] sidebarUrls, String activeItemName ) {
        addSideBarMenu(null, sidebarNames, sidebarUrls, activeItemName );
    }

    public void addSideBarMenu(String headerName, String[] sidebarNames, String[] sidebarUrls, String activeItemName ) {

        int activeItemIndex = 0;
        QuickSidebarMenu sideBarMenu = new QuickSidebarMenu();

        if ( headerName != null ) {
            sideBarMenu.add(new QuickSidebarMenuHeader(headerName));
        }

        for ( int i = 0; i < sidebarNames.length; i++ ) {
            sideBarMenu.add(new QuickSidebarMenuItem(sidebarNames[i], sidebarUrls[i]));
            if ( activeItemName.equals(sidebarNames[i])) {
                activeItemIndex = i;
            }
        }
        sideBarMenu.setActiveItem(sidebarNames[activeItemIndex]);

        getSideBar().add(sideBarMenu);
        getSideBar().add(new HTMLLineBreak(1));
    }

    private QuickSidebar getSideBar() {
        if ( sideBar == null ) {
            sideBar = new QuickSidebar();
        }
        return sideBar;
    }

    public void addRowsOfComponents(BSComponent... components) {

        for ( int j = 0; j < components.length; j++ ) {
            this.components.add(components[j]);
        }

    }

    public void addRowOfColumns(HTMLComponent... components) {

        BSPanel panel = new BSPanel();
        panel.setLayout(new BSGridLayout(1, components.length));

        for ( int j = 0; j < components.length; j++ ) {
            panel.add(components[j]);
        }
        this.components.add(panel);

    }

    public void doLayout() {

        if ( sideBar != null ) {

            // Center Panel
            BSPanel centerPanel = new BSPanel();

            for ( int j = 0; j < this.components.size(); j++ ) {
                if ( j > 0 ) {
                    centerPanel.add(new HTMLLineBreak());
                }
                centerPanel.add(this.components.get(j));
            }

            this.add( BSBorderLayout.createBorderPanel(sideBar, null, null, null, centerPanel) );

        } else {

            for ( int j = 0; j < this.components.size(); j++ ) {
                if ( j > 0 ) {
                    this.add(new HTMLLineBreak());
                }
                this.add(this.components.get(j));
            }

        }

    }

}
