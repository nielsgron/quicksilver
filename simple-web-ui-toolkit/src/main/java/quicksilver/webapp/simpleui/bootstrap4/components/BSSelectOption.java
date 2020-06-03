package quicksilver.webapp.simpleui.bootstrap4.components;

public class BSSelectOption extends BSComponentContainer {

    private final boolean selected;
    private final String value;

    public BSSelectOption(String value) {
        this(value, false);
    }

    public BSSelectOption(String value, boolean selected) {
        this.selected = selected;
        this.value = value;
        add(new BSText(value));
    }

    public boolean isSelected() {
        return selected;
    }

    public String getValue() {
        return value;
    }

    @Override
    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Option");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_END_WITH_LINEBREAK, Boolean.FALSE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "option");

        if (selected) {
            addTagAttribute("selected", null);
        }
        //addTagAttribute("class", getClassNames());

    }

//    protected String getClassNames() {
//        StringBuilder cNames = new StringBuilder();
//
//        cNames.append("form-control");
//
//        return cNames.toString();
//    }

}
