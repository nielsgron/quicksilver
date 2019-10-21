package quicksilver.webapp.simpleui.bootstrap4.charts;

import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.api.PiePlot;
import tech.tablesaw.plotly.api.TimeSeriesPlot;
import tech.tablesaw.plotly.components.Figure;

public class TSTimeSeriesChartPanel extends TSFigurePanel {

    public TSTimeSeriesChartPanel(Table table, String divName) {
        super(divName);

        Figure figure = null;

        try {
            figure = TimeSeriesPlot.create("", table, "Date", "Close");
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        addFigure(figure);

    }

}
