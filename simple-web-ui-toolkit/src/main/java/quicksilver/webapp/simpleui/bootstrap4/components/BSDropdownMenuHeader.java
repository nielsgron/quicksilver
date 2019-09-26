package quicksilver.webapp.simpleui.bootstrap4.components;

public class BSDropdownMenuHeader extends BSComponentContainer {

    private String headerName;
    private int headerSize;

    public BSDropdownMenuHeader(String headerName) {
        this(headerName, 6);
    }

    public BSDropdownMenuHeader(String headerName, int headerSize) {
        this.headerName = headerName;
        this.headerSize = headerSize;

        add(new BSText(headerName));
    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Dropdown-Menu-Header");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "h" + headerSize);

        addTagAttribute("class", getClassNames());

    }

    protected String getClassNames() {
        StringBuilder cNames = new StringBuilder();

        cNames.append("dropdown-header");

        return cNames.toString();
    }

}
