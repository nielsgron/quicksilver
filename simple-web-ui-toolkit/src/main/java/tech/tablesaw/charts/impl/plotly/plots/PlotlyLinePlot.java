package tech.tablesaw.charts.impl.plotly.plots;

import tech.tablesaw.api.Table;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.traces.ScatterTrace;
import tech.tablesaw.table.TableSliceGroup;

import java.util.ArrayList;
import java.util.List;
import tech.tablesaw.plotly.components.Line;

public class PlotlyLinePlot extends PlotlyAbstractPlot {

    public PlotlyLinePlot(ChartBuilder chartBuilder, String groupCol) {
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
        for (int i = 0; i < tableList.size(); i++) {
            ScatterTrace.ScatterBuilder builder =
                    ScatterTrace.builder(
                            tableList.get(i).numberColumn(xCol), tableList.get(i).numberColumn(yCol))
                            .showLegend(true)
                            .name(tableList.get(i).name())
                            .mode(ScatterTrace.Mode.LINE);

            if (columnForColor == null) {
                if (traceColors != null && traceColors.length > i) {
                    builder.line(Line.builder()
                            .color(traceColors[i])
                            .build());
                }
            }
            traces[i] = builder
                            .build();
        }

        setFigure( new Figure(layout, config, traces) );
    }

    public PlotlyLinePlot(ChartBuilder chartBuilder) {
        this(chartBuilder, null);
    }

}
