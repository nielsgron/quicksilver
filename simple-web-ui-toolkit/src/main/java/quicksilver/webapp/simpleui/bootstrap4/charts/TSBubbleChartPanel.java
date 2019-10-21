package quicksilver.webapp.simpleui.bootstrap4.charts;

import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.api.BubblePlot;
import tech.tablesaw.plotly.api.PiePlot;
import tech.tablesaw.plotly.components.Figure;

public class TSBubbleChartPanel extends TSFigurePanel {

    public TSBubbleChartPanel(Table table, String divName) {
        super(divName);

        Figure figure = null;

        try {
            figure = BubblePlot.create("", table, "Name", "Value", "Size");
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        addFigure(figure);

    }

}
