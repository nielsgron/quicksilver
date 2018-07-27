package quicksilver.webapp.simpleui.html.components;

import quicksilver.webapp.simpleui.HtmlStream;

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
