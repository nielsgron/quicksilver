package org.apache.webapp.simpleui.bootstrap4.components;

import org.apache.webapp.simpleui.HtmlStream;

public class BSListGroupItem extends BSComponentContainer {

    private String itemName;
    private String urlReference;
    private boolean isActive;

    public BSListGroupItem(String name) {
        itemName = name;
        add(new BSText(name));
    }

    public BSListGroupItem(String name, String url) {
        itemName = name;
        urlReference = url;
        add(new BSText(name));
    }

    @Override
    public void render(HtmlStream stream) {

        if ( urlReference == null ) {
            stream.writeln("<li class=\"list-group-item\">");
            super.render(stream);
            stream.writeln("</li>");
        } else {
            if ( isActive ) {
                stream.writeln("<a href=\"" + urlReference + "\" class=\"list-group-item list-group-item-action active\">");
            } else {
                stream.writeln("<a href=\"" + urlReference + "\" class=\"list-group-item list-group-item-action\">");
            }
            super.render(stream);
            stream.writeln("</a>");
        }
    }

    public String getName() {
        return itemName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


}
