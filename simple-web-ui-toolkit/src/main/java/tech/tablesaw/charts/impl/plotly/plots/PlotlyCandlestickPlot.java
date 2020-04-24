package tech.tablesaw.charts.impl.plotly.plots;

import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Line;
import tech.tablesaw.plotly.components.change.Decreasing;
import tech.tablesaw.plotly.components.change.Increasing;
import tech.tablesaw.plotly.traces.ScatterTrace;
import tech.tablesaw.plotly.traces.Trace;

public class PlotlyCandlestickPlot extends PlotlyAbstractPlot {

    public PlotlyCandlestickPlot(ChartBuilder chartBuilder) {
        setChartBuilder(chartBuilder);
        String xCol = columnsForViewColumns[0];
        String openCol = columnsForViewRows[0];
        String highCol = columnsForViewRows[1];
        String lowCol = columnsForViewRows[2];
        String closeCol = columnsForViewRows[3];

        // TODO : columnForDetails -

        ScatterTrace.ScatterBuilder builder =
                ScatterTrace.builder(
                        table.dateColumn(xCol),
                        table.numberColumn(openCol),
                        table.numberColumn(highCol),
                        table.numberColumn(lowCol),
                        table.numberColumn(closeCol))
                        .type("candlestick")
                        ;
        if (traceColors != null && traceColors.length > 1) {
            builder.decreasing(Decreasing.builder()
                    .line(Line.builder()
                            .color(traceColors[0])
                            .build())
                    .build());
            builder.increasing(Increasing.builder()
                    .line(Line.builder()
                            .color(traceColors[1])
                            .build())
                    .build());
        }

        ScatterTrace trace = builder
                        .build();

        setFigure( new Figure(layout, config, new Trace[]{trace}) );
    }

}
