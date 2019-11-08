package quicksilver.webapp.simpleui.bootstrap4.charts.plots;

import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.api.HorizontalBarPlot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.traces.BarTrace;
import tech.tablesaw.plotly.traces.Trace;

public class TSHorizontalBarPlot extends HorizontalBarPlot {

    private Figure figure;

    public TSHorizontalBarPlot(Layout layout, Table table, String groupColName, String numberColName) {
        BarTrace trace = BarTrace.builder(table.categoricalColumn(groupColName), table.numberColumn(numberColName)).orientation(BarTrace.Orientation.HORIZONTAL).build();
        figure = new Figure(layout, new Trace[]{trace});
    }

    public TSHorizontalBarPlot(Layout layout, Table table, String groupColName, Layout.BarMode barMode, String... numberColNames) {
        Trace[] traces = new Trace[numberColNames.length];

        for(int i = 0; i < numberColNames.length; ++i) {
            String name = numberColNames[i];
            BarTrace trace = BarTrace.builder(table.categoricalColumn(groupColName), table.numberColumn(name)).orientation(BarTrace.Orientation.HORIZONTAL).showLegend(true).name(name).build();
            traces[i] = trace;
        }

        figure = new Figure(layout, traces);
    }

    public Figure getFigure() {
        return figure;
    }

}
