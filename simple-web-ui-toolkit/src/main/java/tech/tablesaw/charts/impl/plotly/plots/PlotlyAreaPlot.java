package tech.tablesaw.charts.impl.plotly.plots;

import tech.tablesaw.api.Table;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.traces.ScatterTrace;
import tech.tablesaw.table.TableSliceGroup;

import java.util.ArrayList;
import static java.util.Collections.singletonList;
import java.util.List;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlotlyAreaPlot extends PlotlyAbstractPlot {

    private final static Logger LOG = LogManager.getLogger();

    private final String xCol;
    private final String yCol;

    public PlotlyAreaPlot(ChartBuilder chartBuilder, String groupCol) {
        setChartBuilder(chartBuilder);
        xCol = columnsForViewColumns[0];

        if (columnsForViewColumns.length > 1) {
            LOG.warn("Area plot will only take into account the 1st view colum ({} received)", columnsForViewColumns.length);
        }

        yCol = columnsForViewRows[0];

        if (columnsForViewRows.length > 1) {
            LOG.warn("Area plot will only take into account the 1st view row ({} received)", columnsForViewRows.length);
        }

        // TODO : columnForLabels -
        // TODO : columnForDetails -

        List<Table> tableList;

        if ( groupCol != null ) {
            TableSliceGroup tables = table.splitOn(table.categoricalColumn(groupCol));
            tableList = tables.asTableList();
        } else {
            tableList = new ArrayList<Table>();
            tableList.add(table);
        }

        if (individualAxes) {
            setFigures(tableList.stream()
                    .map(t -> createFigure(singletonList(t)))
                    .flatMap(Stream::of)
                    .toArray(Figure[]::new));
        } else {
            setFigures(createFigure(tableList));
        }

    }

    private Figure[] createFigure(List<Table> tableList) {
        if(columnForColor != null && !columnForColor.isEmpty()) {
            Figure[] figures = tableList.stream()
                    .map(tab -> {
                        ScatterTrace[] traces = tab.splitOn(columnForColor).asTableList().stream()
                                .map(this::createTrace)
                                .map(ScatterTrace.ScatterBuilder::build)
                                .toArray(ScatterTrace[]::new);
                        return new Figure(layout, config, traces);
                    })
                    .toArray(Figure[]::new);
            return figures;
        } else {
            ScatterTrace[] traces = new ScatterTrace[tableList.size()];
            for (int i = 0; i < tableList.size(); i++) {
                ScatterTrace.ScatterBuilder builder = createTrace(tableList.get(i));
                if (traceColors != null && traceColors.length > i) {
                    builder.fillColor(traceColors[i]);
                }
                traces[i] = builder
                                .build();
            }

            return new Figure[]{new Figure(layout, config, traces)};
        }
    }

    private ScatterTrace.ScatterBuilder createTrace(Table t) {
        return ScatterTrace.builder(
                t.numberColumn(xCol), t.numberColumn(yCol))
                .showLegend(true)
                .name(t.name())
                .mode(ScatterTrace.Mode.LINE)
                .fill(ScatterTrace.Fill.TO_NEXT_Y);
    }

    public PlotlyAreaPlot(ChartBuilder chartBuilder) {
        this(chartBuilder, null);
    }

}
