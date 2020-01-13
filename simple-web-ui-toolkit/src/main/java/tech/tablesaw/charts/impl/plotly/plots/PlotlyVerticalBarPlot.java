package tech.tablesaw.charts.impl.plotly.plots;

import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.plotly.traces.BarTrace;

public class PlotlyVerticalBarPlot extends PlotlyBarPlot {

    public PlotlyVerticalBarPlot(ChartBuilder chartBuilder) {
        super(chartBuilder);
    }

    @Override
    public BarTrace.Orientation getOrientation() {
        return BarTrace.Orientation.VERTICAL;
    }

}
