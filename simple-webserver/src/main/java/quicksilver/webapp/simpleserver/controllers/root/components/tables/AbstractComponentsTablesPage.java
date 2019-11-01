package quicksilver.webapp.simpleserver.controllers.root.components.tables;

import quicksilver.webapp.simpleserver.controllers.components.toolbar.TBarCharts;
import quicksilver.webapp.simpleserver.controllers.components.toolbar.TBarTables;
import quicksilver.webapp.simpleserver.controllers.root.components.AbstractComponentsPage;
import quicksilver.webapp.simpleui.bootstrap4.components.BSNavbarToolbarContainer;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;

public class AbstractComponentsTablesPage extends AbstractComponentsPage {

    protected TBarTables toolbar;

    public AbstractComponentsTablesPage() {
        getComponentNavTab().setActiveItem("Tables");
    }

    protected BSPanel createContentPanelTop() {
        toolbar = new TBarTables();
        BSPanel panel = super.createContentPanelTop();
        panel.add(new BSNavbarToolbarContainer(toolbar));
        return panel;
    }

}
