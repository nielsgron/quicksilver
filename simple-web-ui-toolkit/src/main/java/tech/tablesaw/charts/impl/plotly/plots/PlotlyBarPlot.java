package tech.tablesaw.charts.impl.plotly.plots;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.tablesaw.api.Table;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Marker;
import tech.tablesaw.plotly.traces.BarTrace;
import tech.tablesaw.plotly.traces.Trace;

public abstract class PlotlyBarPlot extends PlotlyAbstractPlot {

    private final static Logger LOG = LogManager.getLogger();

    public PlotlyBarPlot(ChartBuilder chartBuilder) {
        setChartBuilder(chartBuilder);
        String groupColName = columnsForViewColumns[0];
        if (columnsForViewColumns.length > 1) {
            LOG.warn("Bar plot will only take into account the 1st view colum ({} received)", columnsForViewColumns.length);
        }

        final String[] numberColNames = columnsForViewRows;

        // TODO : columnForDetails -

        final Optional<String> columnForLabel;
        if (columnsForLabels != null && columnsForLabels.length > 0) {
            columnForLabel = Optional.of(columnsForLabels[0]);
            if(columnsForLabels.length > 1) {
                LOG.warn("Bar plot will only take into account the 1st label column ({} received)", columnsForLabels.length);
            }
        } else {
            columnForLabel = Optional.empty();
        }

        final Optional<String> size = Optional.ofNullable(this.columnForSize);

        if (columnForColor != null && !groupColName.equals(columnForColor)) {
            //add column values
            List<Figure> measureFigures = new ArrayList<>();

            //create a figure for each measure
            for (String measure : columnsForViewRows) {

                Trace[] traces = table.splitOn(columnForColor).asTableList().stream()
                        .map(colorTable -> {
                            BarTrace trace = createTrace(colorTable, groupColName, measure, colorTable.name())
                                    .build();
                            return trace;
                        })
                        .toArray(Trace[]::new);

                measureFigures.add(new Figure(layout, config, traces));
            }

            setFigures(measureFigures.toArray(new Figure[0]));
            return;
        }


        // TODO : Support Clustered & Area display type. Research BarTrace.builder.mode(ScatterTrace.Mode.LINE) & BarTrace.builder.fill(ScatterTrace.Fill.TO_NEXT_Y) as in PlotlyAreaPlot

        Trace[] traces = new Trace[numberColNames.length];
        for (int i = 0; i < numberColNames.length; i++) {
            String name = numberColNames[i];
            BarTrace.BarBuilder builder = createTrace(table, groupColName, name, name);
            if(columnForLabel.isPresent()) {
                builder.text(table.stringColumn(columnForLabel.get()));
            }
            if(size.isPresent()) {
                builder.width(table.numberColumn(size.get()));
            }
            if (columnForColor == null) {
                if (traceColors != null && traceColors.length > i) {
                    builder.marker(Marker.builder()
                            .color(traceColors[i])
                            .build());
                }
            }
            BarTrace trace = builder
                            .build();
            traces[i] = trace;
        }

        if (columnForColor == null) {
            //create one figure for each viewRows column
            setFigures(Stream.of(traces)
                    .map(t -> new Figure(layout, config, new Trace[]{t}))
                    .toArray(Figure[]::new));
        } else {
            assert groupColName.equals(columnForColor);

            setFigure(new Figure(layout, config, traces));
        }

    }

    private BarTrace.BarBuilder createTrace(Table table, String groupColName, String numberColumn, String traceName) {
        BarTrace.BarBuilder builder
                = BarTrace.builder(table.categoricalColumn(groupColName), table.numberColumn(numberColumn))
                        .orientation(getOrientation())
                        .showLegend(true)
                        .name(traceName);
        return builder;
    }

    protected abstract BarTrace.Orientation getOrientation();
}
