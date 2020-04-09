package tech.tablesaw.charts.impl.plotly.plots;

import tech.tablesaw.api.Table;
import tech.tablesaw.charts.ChartBuilder;

public class PlotlyTimeSeriesPlot extends PlotlyLinePlot {

    public PlotlyTimeSeriesPlot(ChartBuilder chartBuilder, String groupCol) {
        super(chartBuilder, groupCol);
    }

    protected Table prepareTableForTrace(Table t, String xCol, String yCol) {
        return t.sortOn(xCol);
    }

    public PlotlyTimeSeriesPlot(ChartBuilder chartBuilder) {
        this(chartBuilder, null);
    }

}
