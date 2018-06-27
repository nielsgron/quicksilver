package org.apache.webapp.simpleui.bootstrap4.components;

import org.apache.webapp.simpleui.HtmlStream;

public class BSAlert extends BSComponentContainer {

    private String componentTypeString;

    public BSAlert() {
        this(BSComponentType.PRIMARY);
    }

    public BSAlert(BSComponentType cType) {
        componentTypeString = "alert-" + cType.getTypeName();
    }

    public void render(HtmlStream stream) {

        stream.writeln("<div class=\"alert " + componentTypeString + "\" role=\"alert\">");
        super.render(stream);
        stream.writeln("</div>");

    }

}
