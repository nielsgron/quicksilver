package quicksilver.webapp.simpleui.bootstrap4.quick;

import quicksilver.webapp.simpleui.html.components.HTMLDiv;

public class QuickSidebar extends HTMLDiv {

    protected String getStyle() {
        StringBuilder s = new StringBuilder();

        s.append("background-color: #f5f5f5;");

        return s.toString();
    }

}
