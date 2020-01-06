package tech.tablesaw.charts.impl.plotly.plots;

import tech.tablesaw.api.Table;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.charts.impl.plotly.PlotlyChart;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;

public class PlotlyAbstractPlot extends PlotlyChart {

    protected Layout layout;
    protected Table  table;

    protected String[] columnsForViewColumns;
    protected String[] columnsForViewRows;
    protected String[] columnsForLabels;
    protected String[] columnsForDetails;
    protected String columnForColor;
    protected String columnForSize;

    public void setChartBuilder(ChartBuilder chartBuilder) {

        layout = chartBuilder.getLayout();
        table = chartBuilder.getDataTable();

        columnsForViewColumns = chartBuilder.getColumnsForViewColumns();
        columnsForViewRows = chartBuilder.getColumnsForViewRows();
        columnsForLabels = chartBuilder.getColumnsForLabels();
        columnsForDetails = chartBuilder.getColumnsForDetails();
        columnForColor = chartBuilder.getColumnsForColor();
        columnForSize = chartBuilder.getColumnsForSize();

    }

}
