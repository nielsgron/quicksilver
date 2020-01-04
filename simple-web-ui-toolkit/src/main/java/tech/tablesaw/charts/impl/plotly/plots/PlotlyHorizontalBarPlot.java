package tech.tablesaw.charts.impl.plotly.plots;

import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.traces.BarTrace;
import tech.tablesaw.plotly.traces.Trace;

public class PlotlyHorizontalBarPlot extends PlotlyAbstractPlot {

    public PlotlyHorizontalBarPlot(ChartBuilder chartBuilder) {
        setChartBuilder(chartBuilder);
        String groupColName = columnsForViewColumns[0];
        String[] numberColNames = columnsForViewRows;

        // TODO : columnForLabels -
        // TODO : columnForDetails -
        // TODO : columnForColor -
        // TODO : columnForSize -

        // TODO : Support Clustered & Area display type. Research BarTrace.builder.mode(ScatterTrace.Mode.LINE) & BarTrace.builder.fill(ScatterTrace.Fill.TO_NEXT_Y) as in PlotlyAreaPlot

        BarTrace.Orientation orientation = BarTrace.Orientation.HORIZONTAL;
        Trace[] traces = new Trace[numberColNames.length];
        for (int i = 0; i < numberColNames.length; i++) {
            String name = numberColNames[i];
            BarTrace trace =
                    BarTrace.builder(table.categoricalColumn(groupColName), table.numberColumn(name))
                            .orientation(orientation)
                            .showLegend(true)
                            .name(name)
                            .build();
            traces[i] = trace;
        }

        setFigure( new Figure(layout, traces) );
    }

}
