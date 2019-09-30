package quicksilver.webapp.simpleui.bootstrap4.components;

public class BSInputFile extends BSInput {

    public BSInputFile(String placeholder) {
        super("file", placeholder);
    }

    public BSInputFile(String placeholder, String aria_label, String aria_describedby, String id) {
        super("file", placeholder, aria_label, aria_describedby, id);
    }

    @Override
    protected String getClassNames() {
        return "custom-file-input";
    }

}
