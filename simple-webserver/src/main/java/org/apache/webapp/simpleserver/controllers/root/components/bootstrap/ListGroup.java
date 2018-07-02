package org.apache.webapp.simpleserver.controllers.root.components.bootstrap;

import org.apache.webapp.simpleui.bootstrap4.components.BSListGroup;
import org.apache.webapp.simpleui.bootstrap4.components.BSListGroupItem;
import org.apache.webapp.simpleui.bootstrap4.components.BSPanel;
import org.apache.webapp.simpleui.bootstrap4.components.BSText;
import org.apache.webapp.simpleui.html.components.HTMLLineBreak;

public class ListGroup  extends AbstractComponentsBootstrapPage {

    public ListGroup() {
        getSideBar().setActiveItem("List Group");
    }

    protected BSPanel createContentPanelCenter() {

        BSPanel panel = new BSPanel();

        panel.add(new HTMLLineBreak(1));
        panel.add(new BSText("List of List Group Components"));
        panel.add(new HTMLLineBreak(2));

        BSListGroup listGroup = new BSListGroup();
        listGroup.add(new BSListGroupItem("Item 1"));
        listGroup.add(new BSListGroupItem("Item 2"));
        listGroup.add(new BSListGroupItem("Item 3"));

        panel.add(listGroup);
        panel.add(new HTMLLineBreak(2));

        listGroup = new BSListGroup();
        BSListGroupItem lgi1 = new BSListGroupItem("Link 1", "/1");
        lgi1.setActive(true);
        listGroup.add(lgi1);

        listGroup.add(new BSListGroupItem("Link 2", "/2"));
        listGroup.add(new BSListGroupItem("Link 3", "/3"));

        panel.add(listGroup);

        return panel;
    }

}
