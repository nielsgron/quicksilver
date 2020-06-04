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

package quicksilver.webapp.simpleui.bootstrap4.components;

import quicksilver.webapp.simpleui.HtmlStream;

/*
    Example :

    W3Schools :
    Bootstrap Docs :
 */

public class BSNavbar extends BSComponentContainer {

    private boolean isFluid = false;
    private BSNavbarBrand navBarBrand;

    public BSNavbar(boolean bFluid, String brand) {
        isFluid = bFluid;
        navBarBrand = new BSNavbarBrand("/", brand);
    }

    public void setActiveItem(String name) {
        for (int i = 0; i < children.size(); i++) {
            if ( (children.get(i) instanceof BSNavItem) ) {
                BSNavItem item = (BSNavItem)children.get(i);
                if (item.getName().equals(name)) {
                    item.setActive(true);
                } else {
                    item.setActive(false);
                }
            }
        }
    }

    protected void defineAttributes() {
        // TODO: Need to implement
    }

    public void render(HtmlStream stream) {

        // Wrap the children components in the container tags
        //stream.writeln("<nav class=\"navbar\" navbar-expand-md navbar-dark bg-dark mb-4>");
        stream.writeln("<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">");
        if ( isFluid ) {
            stream.writeln("<div class=\"container-fluid\">");
        } else {
            stream.writeln("<div class=\"container\">");
        }
        renderHeader(stream);

        // Define for NavItem buttons
        stream.writeln("<button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">");
        stream.writeln("<span class=\"navbar-toggler-icon\"></span>");
        stream.writeln("</button>");

        // Start collapse
        stream.writeln("<div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">");

        renderNav(stream);

        renderNavRight(stream);

        // Close collapse
        stream.writeln("</div>");
        // Close container
        stream.writeln("</div>");
        // Close navbar
        stream.writeln("</nav>");

    }

    private void renderHeader(HtmlStream stream) {
        //stream.writeln("<div class=\"navbar-header\">");
        navBarBrand.render(stream);
        //stream.writeln("<div>");
    }

    private void renderNav(HtmlStream stream) {
        // Start Navbar Nav
        stream.writeln("<ul class=\"navbar-nav mr-auto mt-2 mt-lg-0\">");
        //stream.writeln("<ul class=\"nav navbar-nav\">");

        // Render each of the children components which are NavItems
        for (int i = 0; i < children.size(); i++) {
            if ( children.get(i) instanceof BSNavItem ) {
                children.get(i).render(stream);
            }
        }
        // Close Navbar Nav
        stream.writeln("</ul>");
    }

    private void renderNavRight(HtmlStream stream) {
        stream.writeln("<ul class=\"nav navbar-nav navbar-right\">");

        // Render each of the children components which are NOT NavItems, like a form-inline
        for (int i = 0; i < children.size(); i++) {
            if ( !(children.get(i) instanceof BSNavItem) ) {
                children.get(i).render(stream);
            }
        }
        // Close Navbar Nav
        stream.writeln("</ul>");
    }

}
