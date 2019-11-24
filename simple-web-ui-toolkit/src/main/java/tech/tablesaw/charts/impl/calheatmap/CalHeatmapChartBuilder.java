package tech.tablesaw.charts.impl.calheatmap;

import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.charts.impl.calheatmap.plots.CalHeatmapPlot;
import tech.tablesaw.plotly.components.Figure;

public class CalHeatmapChartBuilder extends ChartBuilder {

    protected Figure buildHeatmapCalendar(){

        Figure figure =null;

        try {
            CalHeatmapPlot plot = new CalHeatmapPlot(layout, dataTable, rowColumns[0], dataColumns[0]);
            figure = plot.getFigure();

        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return figure;

    }

}
