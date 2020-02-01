package tech.tablesaw.charts.impl.plotly.plots;

import tech.tablesaw.api.Table;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.traces.ScatterTrace;
import tech.tablesaw.table.TableSliceGroup;

import java.util.ArrayList;
import java.util.List;

public class PlotlyAreaPlot extends PlotlyAbstractPlot {

    public PlotlyAreaPlot(ChartBuilder chartBuilder, String groupCol) {
        setChartBuilder(chartBuilder);
        String xCol = columnsForViewColumns[0];
        String yCol = columnsForViewRows[0];

        // TODO : columnForLabels -
        // TODO : columnForDetails -
        // TODO : columnForSize -

        List<Table> tableList;

        if ( groupCol != null ) {
            TableSliceGroup tables = table.splitOn(table.categoricalColumn(groupCol));
            tableList = tables.asTableList();
        } else {
            tableList = new ArrayList<Table>();
            tableList.add(table);
        }

        if(columnForColor != null && !columnForColor.isEmpty()) {
            Figure[] figures = tableList.stream()
                    .map(tab -> {
                        ScatterTrace[] traces = tab.splitOn(columnForColor).asTableList().stream()
                                .map(t -> {
                                    return ScatterTrace.builder(
                                            t.numberColumn(xCol), t.numberColumn(yCol))
                                            .showLegend(true)
                                            .name(t.name())
                                            .mode(ScatterTrace.Mode.LINE)
                                            .fill(ScatterTrace.Fill.TO_NEXT_Y)
                                            .build();
                                })
                                .toArray(ScatterTrace[]::new);
                        return new Figure(layout, config, traces);
                    })
                    .toArray(Figure[]::new);
            setFigures(figures);
        } else {
            ScatterTrace[] traces = new ScatterTrace[tableList.size()];
            for (int i = 0; i < tableList.size(); i++) {
                traces[i] =
                        ScatterTrace.builder(
                                tableList.get(i).numberColumn(xCol), tableList.get(i).numberColumn(yCol))
                                .showLegend(true)
                                .name(tableList.get(i).name())
                                .mode(ScatterTrace.Mode.LINE)
                                .fill(ScatterTrace.Fill.TO_NEXT_Y)
                                .build();
            }

            setFigure( new Figure(layout, config, traces) );
        }
    }

    public PlotlyAreaPlot(ChartBuilder chartBuilder) {
        this(chartBuilder, null);
    }

}
