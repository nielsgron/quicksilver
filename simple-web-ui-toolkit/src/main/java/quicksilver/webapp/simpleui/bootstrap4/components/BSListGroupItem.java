package quicksilver.webapp.simpleui.bootstrap4.components;

import quicksilver.webapp.simpleui.HtmlStream;

public class BSListGroupItem extends BSComponentContainer {

    private String itemName;
    private String urlReference;
    private boolean isActive;

    public BSListGroupItem(String name) {
        this(name, null);
    }

    public BSListGroupItem(String name, String url) {
        itemName = name;
        urlReference = url;
        add(new BSText(name));

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "List Group Item");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);

        if ( urlReference == null ) {
            putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "li");

            addTagAttribute("class", "list-group-item");
        } else {
            putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "a");

            if ( isActive() ) {
                addTagAttribute("href", urlReference );
                addTagAttribute("class", "list-group-item list-group-item-action active");
            } else {
                addTagAttribute("href", urlReference );
                addTagAttribute("class", "list-group-item list-group-item-action");
            }
        }

    }

    public String getName() {
        return itemName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;

        removeTagAttribute("class");

        if ( isActive() ) {
            addTagAttribute("class", "list-group-item list-group-item-action active");
        } else {
            addTagAttribute("class", "list-group-item list-group-item-action");
        }

    }


}
