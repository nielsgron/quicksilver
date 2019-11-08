package quicksilver.webapp.simpleui.bootstrap4.charts.plots;

import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.api.CandlestickPlot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.traces.ScatterTrace;
import tech.tablesaw.plotly.traces.Trace;

public class TSCandlestickPlot extends CandlestickPlot {

    private Figure figure;

    public TSCandlestickPlot(Layout layout, Table table, String xCol, String openCol, String highCol, String lowCol, String closeCol) {
        ScatterTrace trace = ScatterTrace.builder(table.dateColumn(xCol), table.numberColumn(openCol), table.numberColumn(highCol), table.numberColumn(lowCol), table.numberColumn(closeCol)).type("candlestick").build();
        figure = new Figure(layout, new Trace[]{trace});
    }

    public Figure getFigure() {
        return figure;
    }

}
