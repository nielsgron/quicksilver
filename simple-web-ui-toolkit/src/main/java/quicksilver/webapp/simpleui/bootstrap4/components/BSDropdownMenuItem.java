package quicksilver.webapp.simpleui.bootstrap4.components;

public class BSDropdownMenuItem extends BSComponentContainer {

    private String href;
    private boolean isActive;
    private boolean isDisabled;

    public BSDropdownMenuItem(String name, String href) {
        this(name, href, false, false);
    }

    public BSDropdownMenuItem(String name, String href, boolean isActive, boolean isDisabled) {
        this.href = href;
        this.isActive = isActive;
        this.isDisabled = isDisabled;
        add(new BSText(name));
    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Dropdown-Item");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "a");

        addTagAttribute("class", getClassNames());
        addTagAttribute("href", href);

    }

    protected String getClassNames() {
        StringBuilder cNames = new StringBuilder();

        cNames.append("dropdown-item");

        if ( isActive ) {
            cNames.append(" active");
        }
        if ( isDisabled ) {
            cNames.append(" disabled");
        }

        return cNames.toString();
    }

}
