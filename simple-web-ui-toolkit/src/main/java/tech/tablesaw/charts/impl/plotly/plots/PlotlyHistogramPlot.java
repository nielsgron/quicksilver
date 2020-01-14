package tech.tablesaw.charts.impl.plotly.plots;

import tech.tablesaw.api.NumericColumn;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.traces.HistogramTrace;
import tech.tablesaw.plotly.traces.Trace;

public class PlotlyHistogramPlot extends PlotlyAbstractPlot {

    public PlotlyHistogramPlot(ChartBuilder chartBuilder) {
        setChartBuilder(chartBuilder);
        String numericColumnName = columnsForViewRows[0];

        // TODO : columnForLabels -
        // TODO : columnForDetails -
        // TODO : columnForColor -
        // TODO : columnForSize -

        NumericColumn<?> column = table.numberColumn(numericColumnName);
        HistogramTrace trace = HistogramTrace.builder(column.asDoubleArray()).build();
        setFigure( new Figure(layout, config, new Trace[]{trace}) );
    }

}
