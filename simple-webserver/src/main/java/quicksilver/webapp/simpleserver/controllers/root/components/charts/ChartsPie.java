package quicksilver.webapp.simpleserver.controllers.root.components.charts;

import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.quick.QuickBodyPanel;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import tech.tablesaw.api.Table;

public class ChartsPie extends AbstractComponentsChartsPage {

    public ChartsPie() {
        super();
        toolbar.setActiveButton("Pie");
    }

    protected BSPanel createContentPanelCenter() {

        QuickBodyPanel body = new QuickBodyPanel();

        // Add Chart
        Table table = Charts.createPieDataSet(true);

        body.addRowOfColumns(
                Charts.addPieChart(table, "div1", "Wide Chart")
        );

        body.addRowOfColumns(
                Charts.addPieChart(table, "div2", "Half Width Chart"),
                Charts.addPieChart(table, "div3", "Half Width Chart")
        );

        body.addRowOfColumns(
                Charts.addPieChart(table, "div4", "Narrow Chart"),
                Charts.addPieChart(table, "div5", "Narrow Chart"),
                Charts.addPieChart(table, "div6", "Narrow Chart")
        );

        body.doLayout();

        BSPanel panel = new BSPanel();
        panel.add(new HTMLLineBreak(1));
        panel.add(body);
        panel.add(new HTMLLineBreak(2));

        return panel;

    }

}
