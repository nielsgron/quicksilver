package quicksilver.webapp.simpleserver.controllers.components.toolbar;

import quicksilver.webapp.simpleui.bootstrap4.components.BSButton;
import quicksilver.webapp.simpleui.bootstrap4.components.BSButtonToolbar;
import quicksilver.webapp.simpleui.bootstrap4.quick.QuickButton;

public class TBarTables  extends BSButtonToolbar {

    public TBarTables() {

        BSButton b = null;

//        b = new QuickButton("Basic", "/components/tables", true);
//        b.setSize(Size.SMALL);
//        addAsGroup(b);

//        b = new QuickButton("Color", "/components/tables/color", false);
//        b.setSize(Size.SMALL);
//        addAsGroup(b);
//
//        b = new QuickButton("Sort", "/components/tables/sort", false);
//        b.setSize(Size.SMALL);
//        addAsGroup(b);

        // Export
        b = new QuickButton("Export", "/components/tables/export", false);
        b.setSize(Size.SMALL);
        addAsGroup(b);

    }

}
