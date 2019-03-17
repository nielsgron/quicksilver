package quicksilver.webapp.simpleui.bootstrap4.components;

import quicksilver.webapp.simpleui.html.components.HTMLLabel;

public class BSCheckLabel extends HTMLLabel {

    public BSCheckLabel(String text, String forAttribute) {
        super(text, forAttribute);
    }

    @Override
    protected void defineAttributes() {
        addTagAttribute("class", "form-check-label");

        super.defineAttributes();
    }

}
