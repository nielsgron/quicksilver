package tech.tablesaw.charts.impl.plotly.plots;

import tech.tablesaw.api.CategoricalColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.api.BubblePlot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.components.Marker;
import tech.tablesaw.plotly.traces.ScatterTrace;
import tech.tablesaw.plotly.traces.Trace;
import tech.tablesaw.table.TableSliceGroup;

import java.util.List;

public class PlotlyBubblePlot extends BubblePlot {

    private Figure figure;

    public PlotlyBubblePlot(Layout layout, Table table, String xCol, String yCol, String sizeColumn, String groupCol) {
        TableSliceGroup tables = table.splitOn(new CategoricalColumn[]{table.categoricalColumn(groupCol)});
        ScatterTrace[] traces = new ScatterTrace[tables.size()];

        for(int i = 0; i < tables.size(); ++i) {
            List<Table> tableList = tables.asTableList();
            Marker marker = Marker.builder().size(((Table)tableList.get(i)).numberColumn(sizeColumn)).build();
            traces[i] = ScatterTrace.builder(((Table)tableList.get(i)).numberColumn(xCol), ((Table)tableList.get(i)).numberColumn(yCol)).showLegend(true).marker(marker).name(((Table)tableList.get(i)).name()).build();
        }

        figure = new Figure(layout, traces);
    }

    public PlotlyBubblePlot(Layout layout, Table table, String xCol, String yCol, String sizeColumn) {
        Marker marker = Marker.builder().size(table.numberColumn(sizeColumn)).build();
        ScatterTrace trace = ScatterTrace.builder(table.numberColumn(xCol), table.numberColumn(yCol)).marker(marker).build();
        figure = new Figure(layout, new Trace[]{trace});
    }

    public PlotlyBubblePlot(Layout layout, double[] xCol, double[] yCol) {
        ScatterTrace trace = ScatterTrace.builder(xCol, yCol).build();
        figure = new Figure(layout, new Trace[]{trace});
    }

    public Figure getFigure() {
        return figure;
    }

}
