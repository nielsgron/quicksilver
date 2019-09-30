package quicksilver.webapp.simpleui.bootstrap4.components;

public class BSFormLabel extends BSComponentContainer {

    private String labelString;
    private String forID;

    public BSFormLabel(String labelString, String forID) {
        this.labelString = labelString;
        this.forID = forID;
        add(new BSText(labelString));
    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Label");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "label");

        addTagAttribute("class", getClassNames());
        addTagAttribute("for", forID);

    }

    protected String getClassNames() {
        StringBuilder cNames = new StringBuilder();

        cNames.append("form-check-label");

        return cNames.toString();
    }

}
