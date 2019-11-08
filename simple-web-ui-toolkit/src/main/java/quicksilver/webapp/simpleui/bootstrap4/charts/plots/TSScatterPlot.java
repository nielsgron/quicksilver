package quicksilver.webapp.simpleui.bootstrap4.charts.plots;

import tech.tablesaw.api.CategoricalColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.api.ScatterPlot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.components.Marker;
import tech.tablesaw.plotly.traces.ScatterTrace;
import tech.tablesaw.plotly.traces.Trace;
import tech.tablesaw.table.TableSliceGroup;

import java.util.List;

public class TSScatterPlot extends ScatterPlot {

    private Figure figure;

    public TSScatterPlot(Layout layout, Table table, String xCol, String yCol, String groupCol) {
        TableSliceGroup tables = table.splitOn(new CategoricalColumn[]{table.categoricalColumn(groupCol)});
        ScatterTrace[] traces = new ScatterTrace[tables.size()];
        Marker marker = Marker.builder().opacity(0.75D).build();

        for(int i = 0; i < tables.size(); ++i) {
            List<Table> tableList = tables.asTableList();
            traces[i] = ScatterTrace.builder(((Table)tableList.get(i)).numberColumn(xCol), ((Table)tableList.get(i)).numberColumn(yCol)).showLegend(true).marker(marker).name(((Table)tableList.get(i)).name()).build();
        }

        figure = new Figure(layout, traces);
    }

    public TSScatterPlot(Layout layout, Table table, String xCol, String yCol) {
        ScatterTrace trace = ScatterTrace.builder(table.numberColumn(xCol), table.numberColumn(yCol)).build();
        figure = new Figure(layout, new Trace[]{trace});
    }

    public TSScatterPlot(Layout layout, String xTitle, double[] xCol, String yTitle, double[] yCol) {
        ScatterTrace trace = ScatterTrace.builder(xCol, yCol).build();
        figure = new Figure(layout, new Trace[]{trace});
    }

    public Figure getFigure() {
        return figure;
    }

}
