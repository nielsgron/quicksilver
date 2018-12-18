package quicksilver.webapp.simpleui.icons;

import quicksilver.webapp.simpleui.HtmlStream;
import quicksilver.webapp.simpleui.html.components.HTMLImage;

public class FontAwesomeIcon extends HTMLImage {

    public static FontAwesomeIcon COG = new FontAwesomeIcon("cog");

    protected FontAwesomeIcon(String url) {
        super(url, "");
    }

    protected FontAwesomeIcon(String url, String alt) {
        super(url, alt);
    }

    protected FontAwesomeIcon(FontAwesomeIcon icon, String alt) {
        super(icon.url, alt);
    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "FontAwesomeIcon");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "i");

        addTagAttribute("class", getClassNames());

    }

    protected String getClassNames() {
        StringBuilder cNames = new StringBuilder();

        cNames.append("fas ");
        cNames.append("fa-");
        cNames.append(getURL());

        cNames.append(" fa-2x");

        return cNames.toString();
    }


    public static String getScriptLocal() {
        return "";
    }

    public static String getScriptCDN() {
        return "<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.5.0/css/all.css\" integrity=\"sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU\" crossorigin=\"anonymous\">";
    }

}