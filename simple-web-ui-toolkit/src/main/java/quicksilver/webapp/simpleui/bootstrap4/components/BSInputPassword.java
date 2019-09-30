package quicksilver.webapp.simpleui.bootstrap4.components;

public class BSInputPassword extends BSInput {

    public BSInputPassword(String placeholder) {
        super("password", placeholder);
    }

    public BSInputPassword(String placeholder, String aria_label, String aria_describedby, String id) {
        super("password", placeholder, aria_label, aria_describedby, id);
    }

    @Override
    protected String getClassNames() {
        return "form-control";
    }

}
