package quicksilver.webapp.simpleui.bootstrap4.charts;

import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.api.Heatmap;
import tech.tablesaw.plotly.api.PiePlot;
import tech.tablesaw.plotly.components.Figure;

public class TSHeatMapChartPanel extends TSFigurePanel {

    public TSHeatMapChartPanel(Table table, String divName) {
        super(divName);

        Figure figure = null;

        try {
            figure = Heatmap.create("", table, "Sector", "Company");
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        addFigure(figure);

    }

}
