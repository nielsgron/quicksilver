package tech.tablesaw.charts.impl.plotly.plots;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.tablesaw.api.NumericColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Marker;
import tech.tablesaw.plotly.traces.HistogramTrace;
import tech.tablesaw.plotly.traces.Trace;

public class PlotlyHistogramPlot extends PlotlyAbstractPlot {

    private final static Logger LOG = LogManager.getLogger();

    public PlotlyHistogramPlot(ChartBuilder chartBuilder) {
        setChartBuilder(chartBuilder);
        String numericColumnName = columnsForViewRows[0];

        if (columnsForViewRows.length > 1) {
            LOG.warn("Histogram plot will only take into account the 1st view row ({} received)", columnsForViewRows.length);
        }

        // TODO : columnForLabels -
        // TODO : columnForDetails -
        // TODO : columnForSize -

        if (columnForColor != null) {
            List<String> colorDimension = table.stringColumn(columnForColor).unique().asList();

            Trace[] traces = colorDimension.stream()
                    .map(color -> {
                        Table colorTable = table.where(table.stringColumn(columnForColor).isEqualTo(color));
                        NumericColumn<?> column = colorTable.numberColumn(numericColumnName);
                        HistogramTrace trace = HistogramTrace.builder(column.asDoubleArray()).build();
                        return trace;
                    })
                    .toArray(Trace[]::new);

            setFigure(new Figure(layout, config, traces));
        } else {
            NumericColumn<?> column = table.numberColumn(numericColumnName);
            HistogramTrace.HistogramBuilder builder = HistogramTrace.builder(column.asDoubleArray());
            if (columnForColor == null) {
                if (traceColors != null && traceColors.length > 0) {
                    builder.marker(Marker.builder()
                            .color(traceColors[0])
                            .build());
                }
            }
            HistogramTrace trace = builder.build();
            
            setFigure(new Figure(layout, config, new Trace[]{trace}));
        }
    }

}
