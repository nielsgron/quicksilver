package org.apache.webapp.simpleui.bootstrap4.components;

import org.apache.webapp.simpleui.HtmlStream;

public class BSListGroup extends BSComponentContainer {

    @Override
    public void render(HtmlStream stream) {

        stream.writeln("<ul class=\"list-group\">");
        super.render(stream);
        stream.writeln("</ul>");

    }

    public void setActiveItem(String name) {
        for (int i = 0; i < children.size(); i++) {
            if ( (children.get(i) instanceof BSListGroupItem) ) {
                BSListGroupItem item = (BSListGroupItem)children.get(i);
                if (item.getName().equals(name)) {
                    item.setActive(true);
                } else {
                    item.setActive(false);
                }
            }
        }
    }

}
