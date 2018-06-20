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

package org.apache.webapp.simpleui.bootstrap4.components;

public class BSViewport extends BSComponentContainer {

    private BSNavbar navBar;
    private BSComponentContainer contentPane;
    private String iconURL;

    public BSViewport() {
        contentPane = new BSContainer(true, 1,1);
    }

    public String getIcon() {
        return iconURL;
    }
    public void setIcon(String icon) {
        iconURL = icon;
    }

    public BSNavbar getNavbar() {
        return navBar;
    }
    public void setNavbar(BSNavbar bar) {
        navBar = bar;
    }

    public BSComponentContainer getContentPane() {
        return contentPane;
    }
    public void setContentPane(BSComponentContainer container) {
        contentPane = container;
    }

}
