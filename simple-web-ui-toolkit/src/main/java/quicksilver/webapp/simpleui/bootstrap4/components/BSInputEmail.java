package quicksilver.webapp.simpleui.bootstrap4.components;

public class BSInputEmail extends BSInput {

    public BSInputEmail(String placeholder) {
        super("email", placeholder);
    }

    public BSInputEmail(String placeholder, String aria_label, String aria_describedby, String id) {
        super("email", placeholder, aria_label, aria_describedby, id);
    }

    @Override
    protected String getClassNames() {
        return "form-control";
    }

}
