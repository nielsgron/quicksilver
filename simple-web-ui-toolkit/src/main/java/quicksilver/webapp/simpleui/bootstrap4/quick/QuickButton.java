package quicksilver.webapp.simpleui.bootstrap4.quick;

import quicksilver.webapp.simpleui.bootstrap4.components.BSButton;

public class QuickButton extends BSButton {

    public QuickButton(String text) {

        super(text, Type.SECONDARY, false, null, false, false, false);
        setSize(Size.SMALL);

    }

    public QuickButton(String text, String hyperLink) {

        super(text, Type.SECONDARY, false, hyperLink, false, false, false);
        setSize(Size.SMALL);

    }

    public QuickButton(String text, String hyperLink, boolean isActive) {

        super(text, Type.SECONDARY, false, hyperLink, false, false, isActive);
        setSize(Size.SMALL);

    }

    protected String getStyle() {
        StringBuilder s = new StringBuilder();

        s.append("color: black;");

        if ( isActive() ) {
            s.append("background-color: #d9d9d9;");
        } else {
            s.append("background-color: #f2f2f2;");
        }

        //s.append("border-color: #d9d9d9;");
        s.append("border-color: #cccccc;");

        return s.toString();
    }

}
