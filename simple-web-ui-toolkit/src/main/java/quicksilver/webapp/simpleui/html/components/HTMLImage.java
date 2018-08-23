package quicksilver.webapp.simpleui.html.components;

import quicksilver.webapp.simpleui.HtmlStream;

public class HTMLImage extends HTMLComponent {

    protected String url;
    protected String alt;

    public HTMLImage(String url, String alt) {
        this.url = url;
        this.alt = alt;
    }

    @Override
    public void render(HtmlStream stream) {

        stream.write("<img width=\"16\" height=\"16\"");

        stream.write(" src=\"" + url + "\"");
        stream.write(" alt=\"" + alt + "\"");

        stream.write(">");

    }
}
