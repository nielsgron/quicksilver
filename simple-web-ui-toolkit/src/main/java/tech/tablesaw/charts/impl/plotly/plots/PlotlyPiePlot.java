package tech.tablesaw.charts.impl.plotly.plots;

import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Marker;
import tech.tablesaw.plotly.traces.PieTrace;
import tech.tablesaw.plotly.traces.Trace;

public class PlotlyPiePlot extends PlotlyAbstractPlot {

    public PlotlyPiePlot(ChartBuilder chartBuilder) {
        setChartBuilder(chartBuilder);
        String groupColName = columnsForViewColumns[0];
        String numberColName = columnsForViewRows[0];

        // TODO : columnForLabels -
        // TODO : columnForDetails -
        // TODO : columnForColor -
        // TODO : columnForSize -

        PieTrace.PieBuilder builder =
                PieTrace.builder(table.column(groupColName), table.numberColumn(numberColName))
                        .showLegend(true);

        if (columnForColor == null) {
            if (traceColors != null && traceColors.length > 0) {
                builder.marker(Marker.builder()
                        .colors(traceColors)
                        .build());
            }
        }
        PieTrace trace = builder
                        .build();

        setFigure( new Figure(layout, config, new Trace[]{trace}) );
    }

}
