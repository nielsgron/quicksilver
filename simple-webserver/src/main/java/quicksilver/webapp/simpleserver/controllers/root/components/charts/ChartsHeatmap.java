package quicksilver.webapp.simpleserver.controllers.root.components.charts;

import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.quick.QuickBodyPanel;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import tech.tablesaw.api.Table;

public class ChartsHeatmap extends AbstractComponentsChartsPage {

    public ChartsHeatmap() {
        super();
        toolbar.setActiveButton("Heatmap");
    }

    protected BSPanel createContentPanelCenter() {

        QuickBodyPanel body = new QuickBodyPanel();

        // Add Chart
        Table table = Charts.createMarketDataSet();

        body.addRowOfColumns(
                Charts.addHeatmapChart(table, "div1", "Wide Chart")
        );

        body.addRowOfColumns(
                Charts.addHeatmapChart(table, "div2", "Half Width Chart"),
                Charts.addHeatmapChart(table, "div3", "Half Width Chart")
        );

        body.addRowOfColumns(
                Charts.addHeatmapChart(table, "div4", "Narrow Chart"),
                Charts.addHeatmapChart(table, "div5", "Narrow Chart"),
                Charts.addHeatmapChart(table, "div6", "Narrow Chart")
        );

        body.doLayout();

        BSPanel panel = new BSPanel();
        panel.add(new HTMLLineBreak(1));
        panel.add(body);
        panel.add(new HTMLLineBreak(2));

        return panel;

    }

}
