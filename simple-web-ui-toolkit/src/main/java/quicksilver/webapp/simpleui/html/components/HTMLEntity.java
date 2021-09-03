package quicksilver.webapp.simpleui.html.components;

import quicksilver.webapp.simpleui.HtmlStream;

public class HTMLEntity extends HTMLComponent {

    public static String NAME_NON_BREAKING_SPACE = "&nbsp;";
    public static String NAME_LESS_THAN = "&lt;"; // <
    public static String NAME_GREATER_THAN = "&gt;"; // >
    public static String NAME_AMPERSAND = "&amp;"; // &
    public static String NAME_DOUBLE_QUOTE_MARK = "&quot;"; // "
    public static String NAME_SINGLE_QUOTE_MARK = "&apos;"; // '
    public static String NAME_CENT = "&cent;"; // ¢
    public static String NAME_POUND = "&pound;"; // £
    public static String NAME_YEN = "&yen;"; // ¥
    public static String NAME_EURO = "&euro;"; // €
    public static String NAME_COPYRIGHT = "&copy;"; // ©
    public static String NAME_REGISTERED_TRADEMARK = "&reg;"; // ®

    public static HTMLEntity ENTITY_NON_BREAKING_SPACE = new HTMLEntity(NAME_NON_BREAKING_SPACE, 1);

    private String entityName;
    private int numberOfEntities;

    public HTMLEntity(String entityName) {
        this.entityName = entityName;
        this.numberOfEntities = 1;
    }

    public HTMLEntity(String entityName, int numberOfEntities) {
        this.entityName = entityName;
        this.numberOfEntities = numberOfEntities;
    }

    protected void defineAttributes() {

    }

    @Override
    public void render(HtmlStream stream) {
        for (int i = 0; i < numberOfEntities; i++ ) {
            stream.writeln(entityName);
        }
    }

}
