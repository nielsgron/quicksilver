package quicksilver.webapp.simpleui.bootstrap4.components;

public class BSCheckInput extends BSInput {

    public BSCheckInput(String type, String placeholder, String aria_label, String aria_describedby, String id) {
        super(type, placeholder, aria_label, aria_describedby, id);
    }

    @Override
    protected String getClassNames() {
        return "form-check-input";
    }

}
