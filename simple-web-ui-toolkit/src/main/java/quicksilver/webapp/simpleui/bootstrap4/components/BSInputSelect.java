package quicksilver.webapp.simpleui.bootstrap4.components;

import quicksilver.webapp.simpleui.HtmlStream;

public class BSInputSelect extends BSComponent {

    private String id;
    private boolean isMultiple;
    private String[] selectList;

    public BSInputSelect(String id, boolean isMultiple, String[] selectList) {
        this.id = id;
        this.isMultiple = isMultiple;
        this.selectList = selectList;
    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Select");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "select");

        if ( isMultiple ) {
            addTagAttribute("multiple", null);
        }
        addTagAttribute("class", getClassNames());
        addTagAttribute("id", id);

    }

    protected String getClassNames() {
        StringBuilder cNames = new StringBuilder();

        cNames.append("form-control");

        return cNames.toString();
    }

    public void renderBody(HtmlStream stream) {

        for ( int i = 0; i < selectList.length; i++ ) {
            stream.write("<option>");
            stream.write(selectList[i]);
            stream.writeln("</option>");
        }

    }

}
