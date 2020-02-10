package tech.tablesaw.charts.impl.plotly.plots;

import tech.tablesaw.api.Table;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.charts.impl.plotly.PlotlyChart;
import tech.tablesaw.plotly.components.Config;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.event.EventHandler;

public class PlotlyAbstractPlot extends PlotlyChart {

    protected Layout layout;
    protected Config config;
    protected Table  table;
    protected EventHandler eventHandler;

    protected String[] columnsForViewColumns;
    protected String[] columnsForViewRows;
    protected String[] columnsForLabels;
    protected String[] columnsForDetails;
    protected String columnForColor;
    protected String columnForSize;

    protected String[] traceColors;

    public void setChartBuilder(ChartBuilder chartBuilder) {

        layout = chartBuilder.getLayout();
        config = chartBuilder.getConfig();
        table = chartBuilder.getDataTable();
        eventHandler = chartBuilder.getEventHandler();

        columnsForViewColumns = chartBuilder.getColumnsForViewColumns();
        columnsForViewRows = chartBuilder.getColumnsForViewRows();
        columnsForLabels = chartBuilder.getColumnsForLabels();
        columnsForDetails = chartBuilder.getColumnsForDetails();
        columnForColor = chartBuilder.getColumnsForColor();
        columnForSize = chartBuilder.getColumnsForSize();

        traceColors = chartBuilder.getTraceColors();
    }

}
