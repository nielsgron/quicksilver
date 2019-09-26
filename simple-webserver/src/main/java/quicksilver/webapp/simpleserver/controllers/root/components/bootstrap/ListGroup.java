package quicksilver.webapp.simpleserver.controllers.root.components.bootstrap;

import quicksilver.webapp.simpleui.bootstrap4.components.BSListGroup;
import quicksilver.webapp.simpleui.bootstrap4.components.BSListGroupItem;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSText;
import quicksilver.webapp.simpleui.html.components.HTMLHeading;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import quicksilver.webapp.simpleui.html.components.HTMLThematicBreak;

public class ListGroup  extends AbstractComponentsBootstrapPage {

    public ListGroup() {
        getSideBar().setActiveItem("List Group");
    }

    protected BSPanel createContentPanelCenter() {

        BSPanel panel = new BSPanel();

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("List Group Examples", 4));
        panel.add(new HTMLThematicBreak());

        // List Group Examples

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
