package quicksilver.webapp.simpleui.html.components;

import quicksilver.webapp.simpleui.HtmlStream;

public class HTMLHyperlink extends HTMLComponentContainer {

    private String url;

    public HTMLHyperlink(String hyperlinkURL) {
        url = hyperlinkURL;
    }

    public HTMLHyperlink(String text, String hyperlinkURL) {
        url = hyperlinkURL;
        add(new HTMLText(text));
    }

    public HTMLHyperlink(HTMLComponent component, String hyperlinkURL) {
        url = hyperlinkURL;
        add(component);
    }

    @Override
    public void render(HtmlStream stream) {

        stream.write("<a href=\" " + url + "  \">");
        super.renderBody(stream);
        stream.write("</a>");
    }

}
