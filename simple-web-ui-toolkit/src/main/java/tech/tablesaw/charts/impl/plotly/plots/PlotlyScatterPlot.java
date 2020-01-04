package tech.tablesaw.charts.impl.plotly.plots;

import tech.tablesaw.api.Table;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Marker;
import tech.tablesaw.plotly.traces.ScatterTrace;
import tech.tablesaw.table.TableSliceGroup;

import java.util.ArrayList;
import java.util.List;

public class PlotlyScatterPlot extends PlotlyAbstractPlot {

    public PlotlyScatterPlot(ChartBuilder chartBuilder, String groupCol) {
        setChartBuilder(chartBuilder);
        String xCol = columnsForViewColumns[0];
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
        Marker marker = Marker.builder().opacity(0.75D).build();
        for (int i = 0; i < tableList.size(); i++) {
            traces[i] =
                    ScatterTrace.builder(
                            tableList.get(i).numberColumn(xCol), tableList.get(i).numberColumn(yCol))
                            .showLegend(true)
                            .marker(marker)
                            .name(tableList.get(i).name())
                            .build();
        }

        setFigure( new Figure(layout, traces) );
    }

    public PlotlyScatterPlot(ChartBuilder chartBuilder) {
        this(chartBuilder, null);
    }

}
