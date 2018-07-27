package org.apache.webapp.simpleui.html.components;

import org.apache.webapp.simpleui.HtmlStream;

public class HTMLLabel extends HTMLText {

    private String forAttribute;

    public HTMLLabel(String text) {
        super(text);
    }

    public HTMLLabel(String text, String forAttribute) {
        super(text);
        this.forAttribute = forAttribute;
    }

    @Override
    public void render(HtmlStream stream) {
        stream.write("<label");
        if ( forAttribute != null ) {
            stream.write(" for=\"" + forAttribute + "\"");
        }
        stream.write(">");
        super.render(stream);
        stream.write("</label>");

    }

}
