package org.apache.webapp.simpleui.bootstrap4.components;

import org.apache.webapp.simpleui.HtmlStream;

public class BSBreadcrumbItem extends BSComponentContainer {

    private boolean isActive = false;

    public BSBreadcrumbItem(boolean active) {
        isActive = active;
    }

    public void render(HtmlStream stream) {

        if ( isActive ) {
            stream.writeln("<li class=\"breadcrumb-item active\" aria-current\"page\">");
        } else {
            stream.writeln("<li class=\"breadcrumb-item\">");
        }
        super.render(stream);
        stream.writeln("</li>");

    }


}
