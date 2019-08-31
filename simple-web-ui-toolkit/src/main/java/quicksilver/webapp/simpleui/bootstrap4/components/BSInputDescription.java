package quicksilver.webapp.simpleui.bootstrap4.components;

import quicksilver.webapp.simpleui.HtmlStream;
import quicksilver.webapp.simpleui.html.components.HTMLComponent;

public class BSInputDescription extends HTMLComponent {

    private String text;
    private String id;

    public BSInputDescription(String text, String id) {
        this.text = text;
        this.id = id;

    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Small");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "small");

        if (id != null) {
            addTagAttribute("id", id);
        }

        addTagAttribute("class", getClassNames());
    }

    protected String getClassNames() {
        return "form-text text-muted";
    }

    @Override
    public void renderBody(HtmlStream stream) {
        stream.write(text);
    }
}
