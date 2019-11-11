package tech.tablesaw.charts.impl.plotly.plots;

import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.api.OHLCPlot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.traces.ScatterTrace;
import tech.tablesaw.plotly.traces.Trace;

public class PlotlyOHLCPlot extends OHLCPlot {

    private Figure figure;

    public PlotlyOHLCPlot(Layout layout, Table table, String xCol, String openCol, String highCol, String lowCol, String closeCol) {
        ScatterTrace trace = ScatterTrace.builder(table.dateColumn(xCol), table.numberColumn(openCol), table.numberColumn(highCol), table.numberColumn(lowCol), table.numberColumn(closeCol)).type("ohlc").build();
        figure = new Figure(layout, new Trace[]{trace});
    }

    public Figure getFigure() {
        return figure;
    }

}
