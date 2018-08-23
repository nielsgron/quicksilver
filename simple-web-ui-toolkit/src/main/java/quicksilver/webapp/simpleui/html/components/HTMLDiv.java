package quicksilver.webapp.simpleui.html.components;

import quicksilver.webapp.simpleui.HtmlStream;

public class HTMLDiv extends HTMLComponentContainer {

    private String className;
    private String textAlign;
    private String padding;
    private String margin;
    private String color;
    private String bgColor;
    private String borderBottom;

    public HTMLDiv() {

    }

    public HTMLDiv(String className) {
        this.className = className;
    }

    public HTMLDiv(String textAlign, String padding, String margin, String color, String bgColor, String borderBottom) {
        // left, 1.5px, 1.5px, black, white, 1px solid
        this.textAlign = textAlign;
        this.padding = padding;
        this.margin = margin;
        this.color = color;
        this.bgColor = bgColor;
        this.borderBottom = borderBottom;
    }

    @Override
    public void render(HtmlStream stream) {

        stream.write("<div");

        if ( className != null ) {
            stream.write(" class=\"" + className + "\"");
        }
        String style = getStyle();
        if ( style != null ) {
            stream.write(" style=\"" + style + "\"");
        }

        stream.writeln(">");
        super.renderBody(stream);
        stream.writeln("</div>");

    }

    private String getStyle() {
        StringBuilder styleBuffer = new StringBuilder();

        if ( textAlign != null ) {
            styleBuffer.append("text-align:" + textAlign + ";");
        }
        if ( padding != null ) {
            styleBuffer.append("padding:" + padding + ";");
        }
        if ( margin != null ) {
            styleBuffer.append("margin:" + margin + ";");
        }
        if ( color != null ) {
            styleBuffer.append("color:" + color + ";");
        }
        if ( bgColor != null ) {
            styleBuffer.append("background-color:" + bgColor + ";");
        }
        if ( borderBottom != null ) {
            styleBuffer.append("border-bottom:" + borderBottom + ";");
        }

        if ( styleBuffer.length() > 0 ) {
            return  styleBuffer.toString();
        } else {
            return null;
        }
    }

}
