package quicksilver.webapp.simpleui.bootstrap4.charts;

// 2D & 3D Scatterplot Chart

import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.api.PiePlot;
import tech.tablesaw.plotly.api.ScatterPlot;
import tech.tablesaw.plotly.components.Figure;

public class TSScatterChartPanel extends TSFigurePanel {

    public TSScatterChartPanel(Table table, String divName) {
        super(divName);

        Figure figure = null;

        try {
            figure = ScatterPlot.create("", table, "Name", "Value");
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        addFigure(figure);

    }

}
