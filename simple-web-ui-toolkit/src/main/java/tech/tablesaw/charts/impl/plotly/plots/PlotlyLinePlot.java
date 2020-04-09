package tech.tablesaw.charts.impl.plotly.plots;

import java.util.List;
import java.util.stream.Collectors;
import tech.tablesaw.api.Table;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.traces.ScatterTrace;

import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.tablesaw.plotly.components.Line;

public class PlotlyLinePlot extends PlotlyAbstractBasicPlot {

    private final static Logger LOG = LogManager.getLogger();

    private final String xCol;
    private final String yCol;

    public PlotlyLinePlot(ChartBuilder chartBuilder, String groupCol) {
        setChartBuilder(chartBuilder);
        xCol = columnsForViewColumns[0];
        if (columnsForViewColumns.length > 1) {
            LOG.warn("Only one view column is supported but {} received", columnsForViewColumns.length);
        }

        yCol = columnsForViewRows[0];
        if (columnsForViewRows.length > 1) {
            //TODO: Support more columns in columnsForViewRows by creating more figures?
            LOG.warn("Only one view row is supported but {} received", columnsForViewRows.length);
        }

        // TODO : columnForDetails -
        // TODO : columnForSize -

        setFigures(groupBy(groupCol).toArray(Figure[]::new));
    }

    @Override
    protected Stream<Figure> plainFigure(Stream<Table> tables) {
        //XXX: replace tableList with proper stream map, etc.
        List<Table> tableList = tables.collect(Collectors.toList());

        ScatterTrace[] traces = new ScatterTrace[tableList.size()];
        for (int i = 0; i < tableList.size(); i++) {
            Table t = tableList.get(i);
            t = prepareTableForTrace(t, xCol, yCol);
            ScatterTrace.ScatterBuilder builder =
                    ScatterTrace.builder(
                            t.column(xCol), t.column(yCol))
                            .showLegend(true)
                            .name(t.name())
                            .mode(ScatterTrace.Mode.LINE);

            if (columnForColor == null) {
                if (traceColors != null && traceColors.length > i) {
                    builder.line(Line.builder()
                            .color(traceColors[i])
                            .build());
                }
            }

            if (columnsForLabels != null && columnsForLabels.length > 0) {
                if (columnsForLabels.length > 1) {
                    LOG.warn("Plot will only take into account the 1st label column ({} received)", columnsForLabels.length);
                }

                //assert ColumnType.STRING == tableList.get(i).column(columnsForLabels[0]).type();
                //TODO: other column type might make sense (like a date column) but then the text might be different...
                builder.text(t.stringColumn(columnsForLabels[0]).asObjectArray());
                builder.mode(ScatterTrace.Mode.LINE_AND_TEXT);
            }

            traces[i] = builder
                            .build();
        }

        return Stream.of(new Figure(layout, config, traces));
    }

    protected Table prepareTableForTrace(Table t, String xCol, String yCol) {
        return t;
    }

    public PlotlyLinePlot(ChartBuilder chartBuilder) {
        this(chartBuilder, null);
    }

}
