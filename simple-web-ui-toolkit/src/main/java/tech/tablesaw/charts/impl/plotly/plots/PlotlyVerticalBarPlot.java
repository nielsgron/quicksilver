package tech.tablesaw.charts.impl.plotly.plots;

import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.api.VerticalBarPlot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.traces.BarTrace;
import tech.tablesaw.plotly.traces.Trace;

public class PlotlyVerticalBarPlot extends VerticalBarPlot {

    private Figure figure;

    public PlotlyVerticalBarPlot(Layout layout, Table table, String groupColName, String numberColName) {
        BarTrace trace = BarTrace.builder(table.categoricalColumn(groupColName), table.numberColumn(numberColName)).orientation(BarTrace.Orientation.VERTICAL).build();
        figure = new Figure(layout, new Trace[]{trace});
    }

    public PlotlyVerticalBarPlot(Layout layout, Table table, String groupColName, Layout.BarMode barMode, String... numberColNames) {
        Trace[] traces = new Trace[numberColNames.length];

        for(int i = 0; i < numberColNames.length; ++i) {
            String name = numberColNames[i];
            BarTrace trace = BarTrace.builder(table.categoricalColumn(groupColName), table.numberColumn(name)).orientation(BarTrace.Orientation.VERTICAL).showLegend(true).name(name).build();
            traces[i] = trace;
        }

        figure = new Figure(layout, traces);
    }

    public Figure getFigure() {
        return figure;
    }

}
