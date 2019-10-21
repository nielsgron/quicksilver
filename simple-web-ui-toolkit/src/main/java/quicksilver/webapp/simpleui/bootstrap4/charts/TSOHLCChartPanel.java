package quicksilver.webapp.simpleui.bootstrap4.charts;

import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.api.OHLCPlot;
import tech.tablesaw.plotly.api.PiePlot;
import tech.tablesaw.plotly.components.Figure;

public class TSOHLCChartPanel extends TSFigurePanel {

    public TSOHLCChartPanel(Table table, String divName) {
        super(divName);

        Figure figure = null;

        try {
            figure = OHLCPlot.create("", table, "Date", "Open", "High", "Low", "Close");
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        addFigure(figure);

    }

}
