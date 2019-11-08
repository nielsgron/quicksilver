package quicksilver.webapp.simpleui.bootstrap4.charts.plots;

import tech.tablesaw.api.CategoricalColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.api.LinePlot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.traces.ScatterTrace;
import tech.tablesaw.plotly.traces.Trace;
import tech.tablesaw.table.TableSliceGroup;

import java.util.List;

public class TSLinePlot extends LinePlot {

    private Figure figure;

    public TSLinePlot(Layout layout, Table table, String xCol, String yCol, String groupCol) {
        TableSliceGroup tables = table.splitOn(new CategoricalColumn[]{table.categoricalColumn(groupCol)});
        ScatterTrace[] traces = new ScatterTrace[tables.size()];

        for(int i = 0; i < tables.size(); ++i) {
            List<Table> tableList = tables.asTableList();
            traces[i] = ScatterTrace.builder(((Table)tableList.get(i)).numberColumn(xCol), ((Table)tableList.get(i)).numberColumn(yCol)).showLegend(true).name(((Table)tableList.get(i)).name()).mode(ScatterTrace.Mode.LINE).build();
        }

        figure = new Figure(layout, traces);
    }

    public TSLinePlot(Layout layout, Table table, String xCol, String yCol) {
        ScatterTrace trace = ScatterTrace.builder(table.numberColumn(xCol), table.numberColumn(yCol)).mode(ScatterTrace.Mode.LINE).build();
        figure = new Figure(layout, new Trace[]{trace});
    }

    public TSLinePlot(Layout layout, String xTitle, double[] xCol, String yTitle, double[] yCol) {
        ScatterTrace trace = ScatterTrace.builder(xCol, yCol).mode(ScatterTrace.Mode.LINE).build();
        figure = new Figure(layout, new Trace[]{trace});
    }

    public Figure getFigure() {
        return figure;
    }

}
