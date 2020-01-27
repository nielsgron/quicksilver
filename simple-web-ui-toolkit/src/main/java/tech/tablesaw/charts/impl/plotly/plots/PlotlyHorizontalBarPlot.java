package tech.tablesaw.charts.impl.plotly.plots;

import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.plotly.traces.BarTrace;

public class PlotlyHorizontalBarPlot extends PlotlyBarPlot {

    public PlotlyHorizontalBarPlot(ChartBuilder chartBuilder) {
        super(chartBuilder);
    }

    @Override
    public BarTrace.Orientation getOrientation() {
        return BarTrace.Orientation.HORIZONTAL;
    }

}