package tech.tablesaw.charts.impl.plotly.plots;

import tech.tablesaw.api.*;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.traces.ScatterTrace;
import tech.tablesaw.table.TableSliceGroup;

import java.util.ArrayList;
import java.util.List;

public class PlotlyTimeSeriesPlot extends PlotlyAbstractPlot {

    public PlotlyTimeSeriesPlot(ChartBuilder chartBuilder, String groupCol) {
        setChartBuilder(chartBuilder);
        String dateColX = columnsForViewColumns[0];
        String yCol = columnsForViewRows[0];

        // TODO : columnForLabels -
        // TODO : columnForDetails -
        // TODO : columnForColor -
        // TODO : columnForSize -

        List<Table> tableList;

        if ( groupCol != null ) {
            TableSliceGroup tables = table.splitOn(table.categoricalColumn(groupCol));
            tableList = tables.asTableList();
        } else {
            tableList = new ArrayList<Table>();
            tableList.add(table);
        }

        ScatterTrace[] traces = new ScatterTrace[tableList.size()];
        for (int i = 0; i < tableList.size(); i++) {
            Table t = tableList.get(i).sortOn(dateColX);
            traces[i] =
                    ScatterTrace.builder(t.dateColumn(dateColX), t.numberColumn(yCol))
                            .showLegend(true)
                            .name(tableList.get(i).name())
                            .mode(ScatterTrace.Mode.LINE)
                            .build();
        }

        setFigure( new Figure(layout, traces) );
    }

    public PlotlyTimeSeriesPlot(ChartBuilder chartBuilder) {
        this(chartBuilder, null);
    }

}
