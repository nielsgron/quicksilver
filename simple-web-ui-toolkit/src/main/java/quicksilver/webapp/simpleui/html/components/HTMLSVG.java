package quicksilver.webapp.simpleui.html.components;

import quicksilver.webapp.simpleui.HtmlStream;

public class HTMLSVG extends HTMLComponent {

    protected String url;
    protected String alt;

    public HTMLSVG(String url, String alt) {
        this.url = url;
        this.alt = alt;
    }

    @Override
    public void render(HtmlStream stream) {

        stream.write("<svg");

        stream.write(" width=\"12\"");
        stream.write(" height=\"12\"");
        stream.write(" fill=\"none\"");
        stream.write(" stroke=\"currentColor\"");
        stream.write(" stroke-width=\"2\"");
        stream.write(" stroke-linecap=\"round\"");
        stream.write(" stroke-linejoin=\"round\"");

        stream.write(">");

        stream.write("<use xlink:href=\"" + url + "\">");

        stream.write("</svg>");

    }

}
