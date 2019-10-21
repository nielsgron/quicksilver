package quicksilver.webapp.simpleui.bootstrap4.charts;

import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.api.CandlestickPlot;
import tech.tablesaw.plotly.api.PiePlot;
import tech.tablesaw.plotly.components.Figure;

public class TSCandlestickChartPanel extends TSFigurePanel {

    public TSCandlestickChartPanel(Table table, String divName) {
        super(divName);

        Figure figure = null;

        try {
            figure = CandlestickPlot.create("", table, "Date", "Open", "High", "Low", "Close");
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        addFigure(figure);

    }

}
