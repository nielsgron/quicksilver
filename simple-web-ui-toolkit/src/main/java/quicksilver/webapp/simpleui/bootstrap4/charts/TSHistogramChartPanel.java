package quicksilver.webapp.simpleui.bootstrap4.charts;

import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.api.Histogram;
import tech.tablesaw.plotly.api.PiePlot;
import tech.tablesaw.plotly.components.Figure;

public class TSHistogramChartPanel extends TSFigurePanel {

    public TSHistogramChartPanel(Table table, String divName) {
        super(divName);

        Figure figure = null;

        try {
            figure = Histogram.create("", table, "Value");
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        addFigure(figure);

    }

}
