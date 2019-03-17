package quicksilver.webapp.simpleui.bootstrap4.components;

public class BSFormButton extends BSButton {

    public BSFormButton(String text) {
        super(text);
    }

    @Override
    protected void defineAttributes() {
        super.defineAttributes();

        removeTagAttribute("type");
        addTagAttribute("type", "submit");
    }

}
