package tech.tablesaw.charts.impl.calheatmap;

import tech.tablesaw.charts.Chart;
import tech.tablesaw.charts.ChartBuilder;

public class CalHeatmapChartBuilder extends ChartBuilder {

    protected Chart buildHeatmapCalendar(){

        CalHeatmapChart chart = new CalHeatmapChart(layout, dataTable, columnsForViewColumns[0], columnsForViewRows[0]);

        return chart;
    }

}
