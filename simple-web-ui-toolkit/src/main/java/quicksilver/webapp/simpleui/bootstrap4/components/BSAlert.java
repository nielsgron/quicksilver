package quicksilver.webapp.simpleui.bootstrap4.components;

public class BSAlert extends BSComponentContainer {

    public BSAlert() {
        this(BSComponent.Type.PRIMARY);
    }

    public BSAlert(BSComponent.Type cType) {
        setType(cType);

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Alert");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "div");

        addTagAttribute("class", "alert alert-" + getType().getTypeName());
        addTagAttribute("role", "alert");

    }

}
