package quicksilver.webapp.simpleui.bootstrap4.components;

import quicksilver.webapp.simpleui.html.components.HTMLText;

public class BSButtonClose extends BSComponentContainer {

    private String text;

    public BSButtonClose() {
        this.text = "";
        add(new BSText("<span aria-hidden=\"true\">&times;</span>"));
    }

    public BSButtonClose(String text) {
        this.text = text;
        add(new HTMLText(text));
        add(new BSText("<span aria-hidden=\"true\">&times;</span>"));
    }

    public String getText() {
        return text;
    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Button");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "button");
        addTagAttribute("type", "button" );
        addTagAttribute("class", getClassNames());

        addTagAttribute("data-dismiss", "alert" );
        addTagAttribute("aria-label", "Close" );

    }

    protected String getClassNames() {
        StringBuilder cNames = new StringBuilder();

        cNames.append("close");

        return cNames.toString();
    }

    protected void renderBody() {

    }

}
