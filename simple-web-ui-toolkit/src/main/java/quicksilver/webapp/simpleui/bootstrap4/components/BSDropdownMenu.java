package quicksilver.webapp.simpleui.bootstrap4.components;

public class BSDropdownMenu extends BSComponentContainer {

    private boolean isRightAligned = false;

    public BSDropdownMenu() {

    }

    public BSDropdownMenu(boolean isRightAligned) {
        this.isRightAligned = isRightAligned;
    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Dropdown-Menu");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "div");

        addTagAttribute("class", getClassNames());

    }

    protected String getClassNames() {
        StringBuilder cNames = new StringBuilder();

        cNames.append("dropdown-menu");

        if ( isRightAligned ) {
            cNames.append(" dropdown-menu-right");
        }

        return cNames.toString();
    }

}
