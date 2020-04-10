package tech.tablesaw.charts.impl.plotly.plots;

import tech.tablesaw.api.Table;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.traces.ScatterTrace;
import tech.tablesaw.table.TableSliceGroup;

import java.util.ArrayList;
import java.util.Iterator;
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

        // TODO : columnForDetails -
        setFigures(groupBy(groupCol).toArray(Figure[]::new));
    }

    private Stream<Figure> groupBy(String groupCol) {
        List<Table> tableList;

        if (groupCol != null) {
            TableSliceGroup tables = table.splitOn(table.categoricalColumn(groupCol));
            tableList = tables.asTableList();
        } else {
            tableList = new ArrayList<>();
            tableList.add(table);
        }

        return axes(tableList.stream());
    }

    private Stream<Figure> axes(Stream<Table> tableList) {
        if (individualAxes) {
            return tableList
                    .flatMap(t -> colorColumn(Stream.of(t)));
        } else {
            return colorColumn(tableList);
        }
    }

    private Stream<Figure> colorColumn(Stream<Table> tableList) {
        if (columnForColor != null && !columnForColor.isEmpty()) {
            return tableList
                    .flatMap(tab -> plainFigure(tab.splitOn(columnForColor).asTableList().stream()));
        }

        return traceColors(tableList);
    }

    private Stream<Figure> traceColors(Stream<Table> tables) {
        return plainFigure(tables, traceColors != null);
    }

    private Stream<Figure> plainFigure(Stream<Table> tables) {
        return plainFigure(tables, false);
    }

    private Stream<Figure> plainFigure(Stream<Table> tables, boolean fillColors) {
        Stream<ScatterTrace.ScatterBuilder> builders = tables.map(this::createTrace);
        if (fillColors) {
            Iterator<String> colors = Stream.of(traceColors).iterator();
            builders = builders.map(builder -> builder.fillColor(colors.next()));
        }
        ScatterTrace[] traces = builders
                .map(ScatterTrace.ScatterBuilder::build)
                .toArray(ScatterTrace[]::new);

        return Stream.of(new Figure(layout, config, traces));
    }

    private ScatterTrace.ScatterBuilder createTrace(Table t) {
        ScatterTrace.ScatterBuilder builder = ScatterTrace.builder(
                t.numberColumn(xCol), t.numberColumn(yCol))
                .showLegend(true)
                .name(t.name())
                .mode(ScatterTrace.Mode.LINE)
                .fill(ScatterTrace.Fill.TO_NEXT_Y);

        if (columnsForLabels != null && columnsForLabels.length > 0) {
            if (columnsForLabels.length > 1) {
                LOG.warn("Plot will only take into account the 1st label column ({} received)", columnsForLabels.length);
            }
            builder = builder.text(t.stringColumn(columnsForLabels[0]).asObjectArray());
        }

        return builder;
    }

    public PlotlyAreaPlot(ChartBuilder chartBuilder) {
        this(chartBuilder, null);
    }

}
