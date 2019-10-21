package quicksilver.webapp.simpleui.bootstrap4.quick;

import quicksilver.webapp.simpleui.bootstrap4.components.BSComponent;
import quicksilver.webapp.simpleui.bootstrap4.components.BSNavPill;

public class QuickSidebarMenu extends BSNavPill {

    public QuickSidebarMenu() {
        super(BSComponent.VERTICAL_ALIGNMENT);
    }

    protected String getStyle() {
        StringBuilder s = new StringBuilder();

        s.append("background-color: #f5f5f5;");

        return s.toString();
    }

}
