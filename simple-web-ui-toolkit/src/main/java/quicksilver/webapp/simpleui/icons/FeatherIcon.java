package quicksilver.webapp.simpleui.icons;

import quicksilver.webapp.simpleui.html.components.HTMLImage;

public class FeatherIcon extends HTMLImage {

    public static FeatherIcon ACTIVITY = new FeatherIcon("/icons/feather/activity.svg");
    public static FeatherIcon DATABASE = new FeatherIcon("/icons/feather/database.svg");
    public static FeatherIcon DELETE = new FeatherIcon("/icons/feather/delete.svg");
    public static FeatherIcon TRASH = new FeatherIcon("/icons/feather/trash.svg");
    public static FeatherIcon TRASH_2 = new FeatherIcon("/icons/feather/trash-2.svg");

    protected FeatherIcon(String url) {
        super(url, "");
    }

    protected FeatherIcon(String url, String alt) {
        super(url, alt);
    }

    protected FeatherIcon(FeatherIcon icon, String alt) {
        super(icon.url, alt);
    }

}
