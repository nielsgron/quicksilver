package quicksilver.webapp.simpleui.bootstrap4.quick;

import quicksilver.webapp.simpleui.bootstrap4.components.BSComponentContainer;
import quicksilver.webapp.simpleui.bootstrap4.components.BSText;

public class QuickSidebarMenuHeader extends BSComponentContainer {

    private String headerName;
    private int headerSize;

    public QuickSidebarMenuHeader(String headerName) {
        this(headerName, 6);
    }

    public QuickSidebarMenuHeader(String headerName, int headerSize) {
        this.headerName = headerName;
        this.headerSize = headerSize;

        add(new BSText(headerName));
    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Sidebar-Menu-Header");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "h" + headerSize);

        //addTagAttribute("class", getClassNames());

        String style = getStyle();
        if ( style != null ) {
            addTagAttribute("style", style);
        }

    }

    protected String getClassNames() {
        StringBuilder cNames = new StringBuilder();

        cNames.append("dropdown-header");

        return cNames.toString();
    }

    protected String getStyle() {
        StringBuilder s = new StringBuilder();

        //s.append("color: #666666;");
        s.append("color: #333;");
        s.append("font-size: 87.5%;");
        s.append("font-weight: bold;");
        s.append("background-color: #f2f2f2;");
        s.append("border-radius: 7px;");
        s.append("padding: 2px 7px 2px 7px");


        return s.toString();
    }

}
