package org.apache.webapp.simpleui.html.components;

import org.apache.webapp.simpleui.HtmlStream;

public class HTMLHeading extends HTMLText {

    private int level;

    public HTMLHeading(String text, int l) {
        super(text);
        level = l;
    }

    @Override
    public void render(HtmlStream stream) {
        stream.write("<h" + level + ">");
        super.render(stream);
        stream.write("</h" + level + ">");
    }

}
