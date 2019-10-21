package quicksilver.webapp.simpleui.bootstrap4.charts;

import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.api.AreaPlot;
import tech.tablesaw.plotly.api.PiePlot;
import tech.tablesaw.plotly.components.Figure;

public class TSAreaChartPanel extends TSFigurePanel {

    public TSAreaChartPanel(Table table, String divName) {
        super(divName);

        Figure figure = null;

        try {
            figure = AreaPlot.create("", table, "Name", "Value");
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        addFigure(figure);

    }

}
