package quicksilver.webapp.simpleui.bootstrap4.components;

import quicksilver.webapp.simpleui.HtmlStream;

public class BSBreadcrumb extends BSComponentContainer {

    public void render(HtmlStream stream) {

        stream.writeln("<nav aria-label=\"breadcrumb\">");
        stream.writeln("<ol class=\"breadcrumb\">");
        super.render(stream);
        stream.writeln("</ol>");
        stream.writeln("</nav>");

    }

}
