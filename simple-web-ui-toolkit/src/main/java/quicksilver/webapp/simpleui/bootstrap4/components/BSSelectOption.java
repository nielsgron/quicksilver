package quicksilver.webapp.simpleui.bootstrap4.components;

public class BSSelectOption extends BSComponentContainer {

    public BSSelectOption(String value) {
        add(new BSText(value));
    }

    @Override
    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Option");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_END_WITH_LINEBREAK, Boolean.FALSE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "option");

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
