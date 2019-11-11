package tech.tablesaw.charts.impl.plotly.plots;

import tech.tablesaw.api.*;
import tech.tablesaw.plotly.api.TimeSeriesPlot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.traces.ScatterTrace;
import tech.tablesaw.plotly.traces.Trace;
import tech.tablesaw.table.TableSliceGroup;

import java.util.List;

public class PlotlyTimeSeriesPlot extends TimeSeriesPlot {

    private Figure figure;

    public PlotlyTimeSeriesPlot(Layout layout, Table table, String dateColX, String yCol, String groupCol) {
        TableSliceGroup tables = table.splitOn(new CategoricalColumn[]{table.categoricalColumn(groupCol)});
        ScatterTrace[] traces = new ScatterTrace[tables.size()];

        for(int i = 0; i < tables.size(); ++i) {
            List<Table> tableList = tables.asTableList();
            Table t = ((Table)tableList.get(i)).sortOn(new String[]{dateColX});
            traces[i] = ScatterTrace.builder(t.dateColumn(dateColX), t.numberColumn(yCol)).showLegend(true).name(((Table)tableList.get(i)).name()).mode(ScatterTrace.Mode.LINE).build();
        }

        figure = new Figure(layout, traces);
    }

    public PlotlyTimeSeriesPlot(Layout layout, Table table, String dateColXName, String yColName) {
        ScatterTrace trace = ScatterTrace.builder(table.column(dateColXName), table.numberColumn(yColName)).mode(ScatterTrace.Mode.LINE).build();
        figure = new Figure(layout, new Trace[]{trace});
    }

    public PlotlyTimeSeriesPlot(Layout layout, String xTitle, DateColumn xCol, String yTitle, NumericColumn<?> yCol) {
        ScatterTrace trace = ScatterTrace.builder(xCol, yCol).mode(ScatterTrace.Mode.LINE).build();
        figure = new Figure(layout, new Trace[]{trace});
    }

    public PlotlyTimeSeriesPlot(Layout layout, String xTitle, DateTimeColumn xCol, String yTitle, NumericColumn<?> yCol) {
        ScatterTrace trace = ScatterTrace.builder(xCol, yCol).mode(ScatterTrace.Mode.LINE).build();
        figure = new Figure(layout, new Trace[]{trace});
    }

    public PlotlyTimeSeriesPlot(Layout layout, Table table, String dateTimeColumnName, String numberColumnName, boolean bDateTime) {
        DateTimeColumn xCol = table.dateTimeColumn(dateTimeColumnName);
        NumericColumn<?> yCol = table.numberColumn(numberColumnName);
        ScatterTrace trace = ScatterTrace.builder(xCol, yCol).mode(ScatterTrace.Mode.LINE).build();
        figure = new Figure(layout, new Trace[]{trace});
    }

    public Figure getFigure() {
        return figure;
    }

}
