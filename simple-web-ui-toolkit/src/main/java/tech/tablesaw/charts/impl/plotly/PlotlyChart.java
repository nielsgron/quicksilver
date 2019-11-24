package tech.tablesaw.charts.impl.plotly;

import tech.tablesaw.charts.Chart;
import tech.tablesaw.plotly.components.Figure;

public class PlotlyChart extends Chart {

    private Figure figure;

    public PlotlyChart(Figure figure) {
        this.figure = figure;
    }

    @Override
    public String divString(String divName) {
        return figure.divString(divName);
    }

    @Override
    public String asJavascript(String divName) {
        return figure.asJavascript(divName);
    }
}
