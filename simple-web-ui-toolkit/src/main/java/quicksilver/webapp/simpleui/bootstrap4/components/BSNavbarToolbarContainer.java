package quicksilver.webapp.simpleui.bootstrap4.components;

import quicksilver.webapp.simpleui.html.components.HTMLDiv;

public class BSNavbarToolbarContainer extends BSPanel {

    public BSNavbarToolbarContainer(BSButtonToolbar toolbar) {

        HTMLDiv div = new HTMLDiv(null, null, "6px", null, null, null);
        div.add(toolbar);
        this.add(div);
        this.add(new HTMLDiv(null, null, null, null, null, "1px solid #dee2e6"));

    }

}
