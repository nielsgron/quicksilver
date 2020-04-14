package tech.tablesaw.charts.impl.plotly.plots;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.tablesaw.api.Table;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.plotly.components.Domain;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Marker;
import tech.tablesaw.plotly.traces.PieTrace;

public class PlotlyPiePlot extends PlotlyAbstractPlot {

    private final static Logger LOG = LogManager.getLogger();

    public PlotlyPiePlot(ChartBuilder chartBuilder) {
        setChartBuilder(chartBuilder);
        String groupColName = columnsForViewColumns[0];
        if (columnsForViewColumns.length > 1) {
            LOG.warn("Pie plot will only take into account the 1st view colum ({} received)", columnsForViewColumns.length);
        }

        // TODO : columnForLabels -
        // TODO : columnForDetails -
        // TODO : columnForColor -
        // TODO : columnForSize -
        if (individualAxes) {
            setFigures(Stream.of(columnsForViewRows)
                    .map(numberColName -> simpleFigure(table, groupColName, numberColName))
                    .toArray(Figure[]::new));
        } else {
            if (columnsForViewRows.length > 1) {
                //TODO: EMI: there's no clean way to check if the layout has a grid... perhaps reflection or add public getters?
                if (!layout.asJavascript().contains("grid:")) {
                    LOG.warn("Pie plot has multiple view rows and shared axes but no layout.grid is defined");
                }
                //doesn't have to be atomic, just a counter to use in the lambda bellow
                AtomicInteger counter = new AtomicInteger();
                PieTrace[] traces = Stream.of(columnsForViewRows)
                        .map(numberColName -> trace(table, groupColName, numberColName))
                        //XXX: EMI: row is always 0, not sure how to allow the user control the grid size
                        .map(builder -> builder.domain(Domain.builder().row(0).column(counter.getAndIncrement()).build()))
                        .map(builder -> builder.build())
                        .toArray(PieTrace[]::new);

                setFigure(new Figure(layout, config, traces));
            } else {
                String numberColName = columnsForViewRows[0];
                setFigure(simpleFigure(table, groupColName, numberColName));
            }
        }
    }

    private PieTrace.PieBuilder trace(Table table, String groupColName, String numberColName) {
        PieTrace.PieBuilder builder
                = PieTrace.builder(table.column(groupColName), table.numberColumn(numberColName))
                        .showLegend(true);

        if (columnForColor == null) {
            if (traceColors != null && traceColors.length > 0) {
                builder.marker(Marker.builder()
                        .colors(traceColors)
                        .build());
            }
        }

        return builder;
    }

    private Figure simpleFigure(Table table, String groupColName, String numberColName) {
        PieTrace trace = trace(table, groupColName, numberColName)
                        .build();

        return new Figure(layout, config, trace);
    }

}
