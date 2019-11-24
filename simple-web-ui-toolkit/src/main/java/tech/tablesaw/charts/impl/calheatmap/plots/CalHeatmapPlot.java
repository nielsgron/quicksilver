package tech.tablesaw.charts.impl.calheatmap.plots;

import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;

public class CalHeatmapPlot {

    private Figure figure;

    public CalHeatmapPlot(Layout layout, Table table, String timestampCol, String valueCol) {

    }

    public Figure getFigure() {
        return figure;
    }

}
