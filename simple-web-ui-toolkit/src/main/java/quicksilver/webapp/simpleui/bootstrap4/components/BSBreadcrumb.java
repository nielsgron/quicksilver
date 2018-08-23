package quicksilver.webapp.simpleui.bootstrap4.components;

import quicksilver.webapp.simpleui.HtmlStream;

public class BSBreadcrumb extends BSComponentContainer {

    public BSBreadcrumb() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Breadcrumb");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "nav");

        addTagAttribute("aria-label", "breadcrumb");

    }

    public void renderBody(HtmlStream stream) {

        stream.writeln("<ol class=\"breadcrumb\">");
        super.renderBody(stream);
        stream.writeln("</ol>");

    }

}
