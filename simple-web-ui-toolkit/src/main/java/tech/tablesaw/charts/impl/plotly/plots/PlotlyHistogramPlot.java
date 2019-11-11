package tech.tablesaw.charts.impl.plotly.plots;

import tech.tablesaw.api.NumericColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.api.Histogram;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.traces.HistogramTrace;
import tech.tablesaw.plotly.traces.Trace;

public class PlotlyHistogramPlot extends Histogram {

    private Figure figure;

    public PlotlyHistogramPlot(Layout layout, NumericColumn<?> data) {
        HistogramTrace trace = HistogramTrace.builder(data.asDoubleArray()).build();
        figure = new Figure(layout, new Trace[]{trace});
    }

    public PlotlyHistogramPlot(Layout layout, Table table, String numericColumnName) {
        NumericColumn<?> column = table.numberColumn(numericColumnName);
        HistogramTrace trace = HistogramTrace.builder(column.asDoubleArray()).build();
        figure = new Figure(layout, new Trace[]{trace});
    }

    public PlotlyHistogramPlot(Layout layout, double[] data) {
        HistogramTrace trace = HistogramTrace.builder(data).build();
        figure = new Figure(layout, new Trace[]{trace});
    }

    public Figure getFigure() {
        return figure;
    }

}
