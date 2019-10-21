package quicksilver.webapp.simpleui.bootstrap4.quick;

import quicksilver.webapp.simpleui.HtmlStream;
import quicksilver.webapp.simpleui.bootstrap4.components.BSNavItem;

public class QuickSidebarMenuItem extends BSNavItem {

    public QuickSidebarMenuItem(String name, String url) {
        super(name, url);
    }

    @Override
    public void renderBody(HtmlStream stream) {

        stream.writeln("  <a class=\"nav-link");

        if ( isActive() ) {
            stream.writeln(" active\"");
        } else {
            stream.writeln("\"");
        }

        stream.writeln(" href=\"" + getURL() + "\"");

        StringBuilder style = new StringBuilder("padding: 2px 7px 2px 7px;");
        if ( isActive() ) {
            //style.append("background-color: #00a3cc;");
            style.append("background-color: #08c;");
        }
        style.append("border-radius: 0px;");

        if ( style != null ) {
            stream.writeln(" style=\"" + style + "\"");
        }

        stream.writeln(" >" + getName() + "</a>");

    }

    protected String getStyle() {
        StringBuilder s = new StringBuilder();

        s.append("padding: 1px 1px 1px 1px;");

        return s.toString();
    }

}
