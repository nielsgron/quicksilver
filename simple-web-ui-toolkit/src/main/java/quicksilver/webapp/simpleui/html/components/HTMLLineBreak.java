package quicksilver.webapp.simpleui.html.components;

import quicksilver.webapp.simpleui.HtmlStream;

public class HTMLLineBreak extends HTMLComponent {

    private int numberOfLineBreaks;

    public HTMLLineBreak() {
        this(1);
    }

    public HTMLLineBreak(int amount) {
        numberOfLineBreaks = amount;
    }

    @Override
    public void render(HtmlStream stream) {
        for (int i = 0; i < numberOfLineBreaks; i++ ) {
            stream.writeln("<br>");
        }
    }

}
