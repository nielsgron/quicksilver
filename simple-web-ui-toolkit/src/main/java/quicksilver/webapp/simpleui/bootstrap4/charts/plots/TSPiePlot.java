package quicksilver.webapp.simpleui.bootstrap4.charts.plots;

import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.api.PiePlot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.traces.PieTrace;
import tech.tablesaw.plotly.traces.Trace;

public class TSPiePlot extends PiePlot {

    private Figure figure;

    public TSPiePlot(Layout layout, Table table, String groupColName, String numberColName) {
        PieTrace trace = PieTrace.builder(table.column(groupColName), table.numberColumn(numberColName)).showLegend(true).build();
        figure = new Figure(layout, new Trace[]{trace});
    }

    public Figure getFigure() {
        return figure;
    }

}
