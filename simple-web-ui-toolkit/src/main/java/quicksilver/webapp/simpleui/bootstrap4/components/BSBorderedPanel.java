package quicksilver.webapp.simpleui.bootstrap4.components;

import quicksilver.webapp.simpleui.html.components.HTMLDiv;

public class BSBorderedPanel extends HTMLDiv {

    private String padding = "1.5rem";

    private String borderStyle = "solid";
    private String borderWidth = ".15rem";
    private String borderColor = "#f2f2f2";
    private String borderRadius;

    private String backgroundColor;

    public BSBorderedPanel() {

    }

    public BSBorderedPanel(String borderWidth) {
        setWidth(borderWidth);
    }

    public BSBorderedPanel(String borderWidth, String borderColor, String borderStyle, String backgroundColor) {
        setWidth(borderWidth);
        setColor(borderColor);
        setStyle(borderStyle);
        setBackgroundColor(backgroundColor);
    }

    protected String getStyle() {
        StringBuilder styleBuffer = new StringBuilder();

        if ( padding != null ) {
            styleBuffer.append("padding:").append(padding).append(";");
        }
        if ( borderStyle != null ) {
            styleBuffer.append("border-style:").append(borderStyle).append(";");
        }
        if ( borderWidth != null ) {
            styleBuffer.append("border-width:").append(borderWidth).append(";");
        }
        if ( borderColor != null ) {
            styleBuffer.append("border-color:").append(borderColor).append(";");
        }
        if ( borderRadius != null ) {
            styleBuffer.append("border-radius:").append(borderRadius).append(";");
        }
        if ( backgroundColor != null ) {
            styleBuffer.append("background-color:").append(backgroundColor).append(";");
        }

        return styleBuffer.toString();
    }

    public void setStyle(String value) {
        borderStyle = value;
    }

    public void setWidth(String value) {
        borderWidth = value;
    }

    public void setColor(String value) {
        borderColor = value;
    }

    public void setRadius(String value) {
        borderRadius = value;
    }

    public void setBackgroundColor(String value) {
        backgroundColor = value;
    }

}
