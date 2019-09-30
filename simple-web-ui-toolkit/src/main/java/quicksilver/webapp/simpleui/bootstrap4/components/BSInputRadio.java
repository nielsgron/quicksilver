package quicksilver.webapp.simpleui.bootstrap4.components;

public class BSInputRadio extends BSInput {

    public BSInputRadio(String placeholder, String aria_label, String aria_describedby, String id) {
        super("radio", placeholder, aria_label, aria_describedby, id);
    }

    @Override
    protected String getClassNames() {
        return "form-check-input";
    }

}
