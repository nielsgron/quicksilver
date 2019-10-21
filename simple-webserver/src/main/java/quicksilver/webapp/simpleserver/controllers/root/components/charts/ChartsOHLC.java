package quicksilver.webapp.simpleserver.controllers.root.components.charts;

import quicksilver.commons.data.TSDataSetFactory;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.quick.QuickBodyPanel;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import tech.tablesaw.api.Table;

public class ChartsOHLC extends AbstractComponentsChartsPage {

    public ChartsOHLC() {
        super();
        toolbar.setActiveButton("OHLC");
    }

    protected BSPanel createContentPanelCenter() {

        QuickBodyPanel body = new QuickBodyPanel();

        // Add Chart
        Table table = TSDataSetFactory.createSampleStockPrices().getTSTable();

        body.addRowOfColumns(
                Charts.addOHLCChart(table, "div1", "Wide Chart")
        );

        body.addRowOfColumns(
                Charts.addOHLCChart(table, "div2", "Half Width Chart"),
                Charts.addOHLCChart(table, "div3", "Half Width Chart")
        );

        body.addRowOfColumns(
                Charts.addOHLCChart(table, "div4", "Narrow Chart"),
                Charts.addOHLCChart(table, "div5", "Narrow Chart"),
                Charts.addOHLCChart(table, "div6", "Narrow Chart")
        );

        body.doLayout();

        BSPanel panel = new BSPanel();
        panel.add(new HTMLLineBreak(1));
        panel.add(body);
        panel.add(new HTMLLineBreak(2));

        return panel;

    }

}
