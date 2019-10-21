package quicksilver.webapp.simpleserver.controllers.components.toolbar;

import quicksilver.webapp.simpleui.bootstrap4.components.BSButton;
import quicksilver.webapp.simpleui.bootstrap4.components.BSButtonToolbar;
import quicksilver.webapp.simpleui.bootstrap4.quick.QuickButton;

public class TBarCharts  extends BSButtonToolbar {

    public TBarCharts() {

        BSButton b = new QuickButton("All", "/components/charts", true);
        b.setSize(Size.SMALL);
        addAsGroup(b);

        b = new QuickButton("VBar", "/components/charts/vbar", false);
        b.setSize(Size.SMALL);
        addAsGroup(b);

        b = new QuickButton("HBar", "/components/charts/hbar", false);
        b.setSize(Size.SMALL);
        addAsGroup(b);

        b = new QuickButton("Line", "/components/charts/line", false);
        b.setSize(Size.SMALL);
        addAsGroup(b);

        b = new QuickButton("Area", "/components/charts/area", false);
        b.setSize(Size.SMALL);
        addAsGroup(b);

        b = new QuickButton("Pie", "/components/charts/pie", false);
        b.setSize(Size.SMALL);
        addAsGroup(b);

        b = new QuickButton("Timeseries", "/components/charts/timeseries", false);
        b.setSize(Size.SMALL);
        addAsGroup(b);

        b = new QuickButton("Scatter", "/components/charts/scatter", false);
        b.setSize(Size.SMALL);
        addAsGroup(b);

        b = new QuickButton("Bubble", "/components/charts/bubble", false);
        b.setSize(Size.SMALL);
        addAsGroup(b);

        b = new QuickButton("Histogram", "/components/charts/histogram", false);
        b.setSize(Size.SMALL);
        addAsGroup(b);

        b = new QuickButton("Heatmap", "/components/charts/heatmap", false);
        b.setSize(Size.SMALL);
        addAsGroup(b);

        b = new QuickButton("Candlestick", "/components/charts/candlestick", false);
        b.setSize(Size.SMALL);
        addAsGroup(b);

        b = new QuickButton("OHLC", "/components/charts/ohlc", false);
        b.setSize(Size.SMALL);
        addAsGroup(b);

        b = new QuickButton("Treemap", "/components/charts/treemap", false);
        b.setSize(Size.SMALL);
        addAsGroup(b);

        b = new QuickButton("Sunburst", "/components/charts/sunburst", false);
        b.setSize(Size.SMALL);
        addAsGroup(b);

    }

}
