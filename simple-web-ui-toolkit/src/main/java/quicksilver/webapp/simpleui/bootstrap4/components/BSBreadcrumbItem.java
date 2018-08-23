package quicksilver.webapp.simpleui.bootstrap4.components;

public class BSBreadcrumbItem extends BSComponentContainer {

    private boolean isActive = false;

    public BSBreadcrumbItem(boolean active) {
        isActive = active;

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Breadcrumb Item");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "li");

        if ( isActive ) {
            addTagAttribute("class", "breadcrumb-item active");
            addTagAttribute("aria-current", "page");
        } else {
            addTagAttribute("class", "breadcrumb-item");
        }

    }

}
