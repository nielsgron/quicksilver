package quicksilver.webapp.simpleui.bootstrap4.components;

public class BSInputTextArea extends BSComponent {

    private String id;
    private int rows;

    public BSInputTextArea(String id, int rows) {
        this.id = id;
        this.rows = rows;
    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "TextArea");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "textarea");

        addTagAttribute("class", getClassNames());
        addTagAttribute("rows", Integer.toString(rows));
        addTagAttribute("id", id);

    }

    protected String getClassNames() {
        StringBuilder cNames = new StringBuilder();

        cNames.append("form-control");

        return cNames.toString();
    }

}
