package tech.tablesaw.charts.impl.plotly.plots;

import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.traces.ScatterTrace;
import tech.tablesaw.plotly.traces.Trace;

public class PlotlyOHLCPlot extends PlotlyAbstractPlot {

    public PlotlyOHLCPlot(ChartBuilder chartBuilder) {
        setChartBuilder(chartBuilder);
        String xCol = columnsForViewColumns[0];
        String openCol = columnsForViewRows[0];
        String highCol = columnsForViewRows[1];
        String lowCol = columnsForViewRows[2];
        String closeCol = columnsForViewRows[3];

        // TODO : columnForLabels -
        // TODO : columnForDetails -
        // TODO : columnForColor -
        // TODO : columnForSize -

        ScatterTrace trace =
                ScatterTrace.builder(
                        table.dateColumn(xCol),
                        table.numberColumn(openCol),
                        table.numberColumn(highCol),
                        table.numberColumn(lowCol),
                        table.numberColumn(closeCol))
                        .type("ohlc")
                        .build();

        setFigure( new Figure(layout, config, new Trace[]{trace}) );
    }

}
