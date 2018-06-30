package org.apache.webapp.simpleui.bootstrap4.components;

import org.apache.webapp.simpleui.HtmlStream;

public class BSAlert extends BSComponentContainer {

    public BSAlert() {
        this(BSComponent.Type.PRIMARY);
    }

    public BSAlert(BSComponent.Type cType) {
        setType(cType);
    }

    public void render(HtmlStream stream) {

        stream.writeln("<div class=\"alert alert-" + getType().getTypeName() + "\" role=\"alert\">");
        super.render(stream);
        stream.writeln("</div>");

    }

}
