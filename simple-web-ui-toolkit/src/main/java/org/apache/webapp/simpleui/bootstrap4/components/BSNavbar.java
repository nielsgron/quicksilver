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

package org.apache.webapp.simpleui.bootstrap4.components;

import org.apache.webapp.simpleui.HtmlStream;

public class BSNavbar extends BSComponentContainer {

    private boolean isFluid = false;
    private String navBarBrand;

    public BSNavbar(boolean bFluid, String brand) {
        isFluid = bFluid;
        navBarBrand = brand;
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

    public void render(HtmlStream stream) {

        // Wrap the children components in the container tags
        //stream.writeln("<nav class=\"navbar\" navbar-expand-md navbar-dark bg-dark mb-4>");
        stream.writeln("<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">");
        if ( !isFluid ) {
            stream.writeln("<div class=\"container\">");
        }
        stream.writeln("<a class=\"navbar-brand\" href=\"/\">" + navBarBrand + "</a>");

        // Define for NavItem buttons
        stream.writeln("<button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">");
        stream.writeln("<span class=\"navbar-toggler-icon\"></span>");
        stream.writeln("</button>");

        // Start collapse
        stream.writeln("<div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">");

        // Start Navbar Nav
        stream.writeln("<ul class=\"navbar-nav mr-auto mt-2 mt-lg-0\">");
        // Render each of the children components which are NavItems
        for (int i = 0; i < children.size(); i++) {
            if ( children.get(i) instanceof BSNavItem ) {
                children.get(i).render(stream);
            }
        }
        // Close Navbar Nav
        stream.writeln("</ul>");

        // Render each of the children components which are NOT NavItems, like a form-inline
        for (int i = 0; i < children.size(); i++) {
            if ( !(children.get(i) instanceof BSNavItem) ) {
                children.get(i).render(stream);
            }
        }

        // Close collapse
        stream.writeln("</div>");

        if ( !isFluid ) {
            stream.writeln("</div>");
        }

        // Close container wrapper
        stream.writeln("</nav>");

    }

}
