package quicksilver.webapp.simpleui.bootstrap4.components;

public class BSInputRange extends BSInput {

    public BSInputRange(String placeholder) {
        super("range", placeholder);
    }

    public BSInputRange(String placeholder, String aria_label, String aria_describedby, String id) {
        super("range", placeholder, aria_label, aria_describedby, id);
    }

    @Override
    protected String getClassNames() {
        return "form-control-range";
    }

}
