package quicksilver.webapp.simpleui.html.components;

import quicksilver.webapp.simpleui.HtmlStream;

public class HTMLHyperlink extends HTMLText {

    private String url;

    public HTMLHyperlink(String text, String hyperlinkURL) {
        super(text);
        url = hyperlinkURL;
    }

    @Override
    public void render(HtmlStream stream) {

        stream.write("<a href=\" " + url + "  \">");
        super.render(stream);
        stream.write("</a>");
    }

}
