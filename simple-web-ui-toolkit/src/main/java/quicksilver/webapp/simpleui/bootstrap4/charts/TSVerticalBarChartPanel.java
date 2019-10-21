package quicksilver.webapp.simpleui.bootstrap4.charts;

import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.api.VerticalBarPlot;
import tech.tablesaw.plotly.components.Figure;

public class TSVerticalBarChartPanel extends TSFigurePanel {

    public TSVerticalBarChartPanel(Table table, String divName) {
        super(divName);

        Figure figure = null;

        try {
            figure = VerticalBarPlot.create("", table, "Name", "Value");
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        addFigure(figure);

    }

}
