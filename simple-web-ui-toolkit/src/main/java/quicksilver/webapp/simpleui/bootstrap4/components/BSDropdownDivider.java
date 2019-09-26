package quicksilver.webapp.simpleui.bootstrap4.components;

public class BSDropdownDivider extends BSComponent {

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Dropdown-Divider");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "div");

        addTagAttribute("class", getClassNames());

    }

    protected String getClassNames() {
        StringBuilder cNames = new StringBuilder();

        cNames.append("dropdown-divider");
        //cNames.append(" alert-").append(getType().getTypeName());

        return cNames.toString();
    }


}
