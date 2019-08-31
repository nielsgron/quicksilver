package quicksilver.webapp.simpleui.html.components;

import quicksilver.webapp.simpleui.HtmlStream;

public class HTMLThematicBreak  extends HTMLComponent {

    public HTMLThematicBreak() {

    }

    protected void defineAttributes() {

    }

    @Override
    public void render(HtmlStream stream) {
        stream.writeln("<hr>");
    }

}
