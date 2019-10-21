package quicksilver.webapp.simpleui.bootstrap4.charts;

import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.api.PiePlot;
import tech.tablesaw.plotly.components.Figure;

public class TSTreeMapChartPanel extends TSFigurePanel {

    public TSTreeMapChartPanel(Table table, String divName) {
        super(divName);

        Figure figure = null;

        try {
            figure = PiePlot.create("", table, "Name", "Value");
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        addFigure(figure);

    }

}
