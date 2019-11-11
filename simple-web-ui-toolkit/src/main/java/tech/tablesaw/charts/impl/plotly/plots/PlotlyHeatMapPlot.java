package tech.tablesaw.charts.impl.plotly.plots;

import tech.tablesaw.api.Table;
import tech.tablesaw.columns.Column;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.traces.HeatmapTrace;
import tech.tablesaw.plotly.traces.Trace;
import tech.tablesaw.util.DoubleArrays;

import java.util.List;

public class PlotlyHeatMapPlot { // extends Heatmap {

    private Figure figure;

    public PlotlyHeatMapPlot(Layout layout, Table table, String categoryCol1, String categoryCol2) {
        Table counts = table.xTabCounts(categoryCol1, categoryCol2);
        counts = counts.dropRows(new int[]{counts.rowCount() - 1});
        List<Column<?>> columns = counts.columns();
        columns.remove(counts.columnCount() - 1);
        Column<?> yColumn = (Column)columns.remove(0);
        double[][] z = DoubleArrays.to2dArray(counts.numericColumns());
        Object[] x = counts.columnNames().toArray();
        Object[] y = yColumn.asObjectArray();
        HeatmapTrace trace = HeatmapTrace.builder(x, y, z).build();
        figure = new Figure(layout, new Trace[]{trace});
    }

    public Figure getFigure() {
        return figure;
    }

}
