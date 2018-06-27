package org.apache.webapp.simpleserver.controllers.root.components.bootstrap;

import org.apache.webapp.simpleui.bootstrap4.components.BSListGroup;
import org.apache.webapp.simpleui.bootstrap4.components.BSPanel;
import org.apache.webapp.simpleui.bootstrap4.components.BSText;

public class ListGroup  extends AbstractComponentsBootstrapPage {

    public ListGroup() {
        getSideBar().setActiveItem("List Group");
    }

    protected BSPanel createContentPanelCenter() {

        BSPanel panel = new BSPanel();

        panel.add(new BSText("<br>"));
        panel.add(new BSText("List of List Group Components"));
        panel.add(new BSText("<br>"));
        panel.add(new BSListGroup());

        return panel;
    }

}
