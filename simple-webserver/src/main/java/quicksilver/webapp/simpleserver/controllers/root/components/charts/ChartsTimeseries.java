package quicksilver.webapp.simpleserver.controllers.root.components.charts;

import quicksilver.commons.data.TSDataSetFactory;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.quick.QuickBodyPanel;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import tech.tablesaw.api.Table;

public class ChartsTimeseries extends AbstractComponentsChartsPage {

    public ChartsTimeseries() {
        super();
        toolbar.setActiveButton("Timeseries");
    }

    protected BSPanel createContentPanelCenter() {

        QuickBodyPanel body = new QuickBodyPanel();

        // Add Chart
        Table table = TSDataSetFactory.createSampleStockPrices().getTSTable();

        body.addRowOfColumns(
                Charts.addTimeseriesChart(table, "div1", "Wide Chart")
        );

        body.addRowOfColumns(
                Charts.addTimeseriesChart(table, "div2", "Half Width Chart"),
                Charts.addTimeseriesChart(table, "div3", "Half Width Chart")
        );

        body.addRowOfColumns(
                Charts.addTimeseriesChart(table, "div4", "Narrow Chart"),
                Charts.addTimeseriesChart(table, "div5", "Narrow Chart"),
                Charts.addTimeseriesChart(table, "div6", "Narrow Chart")
        );

        body.doLayout();

        BSPanel panel = new BSPanel();
        panel.add(new HTMLLineBreak(1));
        panel.add(body);
        panel.add(new HTMLLineBreak(2));

        return panel;

    }

}
