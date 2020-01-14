package tech.tablesaw.charts.impl.plotly.plots;

import tech.tablesaw.api.Table;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.columns.Column;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.traces.HeatmapTrace;
import tech.tablesaw.plotly.traces.Trace;
import tech.tablesaw.util.DoubleArrays;

import java.util.List;

public class PlotlyHeatMapPlot extends PlotlyAbstractPlot {

    public PlotlyHeatMapPlot(ChartBuilder chartBuilder) {
        setChartBuilder(chartBuilder);
        String categoryCol1 = columnsForViewColumns[0];
        String categoryCol2 = columnsForViewColumns[1];

        // TODO : columnForLabels -
        // TODO : columnForDetails -
        // TODO : columnForColor -
        // TODO : columnForSize -

        Table counts = table.xTabCounts(categoryCol1, categoryCol2);
        counts = counts.dropRows(counts.rowCount() - 1);
        List<Column<?>> columns = counts.columns();
        columns.remove(counts.columnCount() - 1);
        Column<?> yColumn = columns.remove(0);
        double[][] z = DoubleArrays.to2dArray(counts.numericColumns());

        Object[] x = counts.columnNames().toArray();
        Object[] y = yColumn.asObjectArray();
        HeatmapTrace trace = HeatmapTrace.builder(x, y, z).build();

        setFigure( new Figure(layout, config, new Trace[]{trace}) );
    }

}
