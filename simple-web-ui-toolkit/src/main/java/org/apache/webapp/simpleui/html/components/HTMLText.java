package org.apache.webapp.simpleui.html.components;

import org.apache.webapp.simpleui.HtmlStream;

public class HTMLText extends HTMLComponent {

    private String text;

    public HTMLText(String txt) {
        text = txt;
    }

    @Override
    public void render(HtmlStream stream) {
        stream.write(text);
    }

}
