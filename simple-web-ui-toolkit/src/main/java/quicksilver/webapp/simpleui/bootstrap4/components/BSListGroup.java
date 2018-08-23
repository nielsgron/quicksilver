package quicksilver.webapp.simpleui.bootstrap4.components;

import quicksilver.webapp.simpleui.HtmlStream;

public class BSListGroup extends BSComponentContainer {

    public BSListGroup() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "List Group");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "ul");

        addTagAttribute("class", "list-group");

    }

    public void setActiveItem(String name) {
        for (int i = 0; i < children.size(); i++) {
            if ( (children.get(i) instanceof BSListGroupItem) ) {
                BSListGroupItem item = (BSListGroupItem)children.get(i);
                if (item.getName().equals(name)) {
                    item.setActive(true);
                } else {
                    item.setActive(false);
                }
            }
        }
    }

}
