package tech.tablesaw.charts;

import tech.tablesaw.api.Table;
import tech.tablesaw.charts.impl.chartjs.ChartjsChartBuilder;
import tech.tablesaw.charts.impl.plotly.PlotlyChartBuilder;
import tech.tablesaw.plotly.components.Figure;

public abstract class ChartBuilder {

    static {

        // Example Usage :

        Table table = Table.create();
        Figure figure = ChartBuilder.createBuilder().dataTable(table).chartType("treemap")
                .rowColumns("Sector", "Industry")
                .dataColumns("MarketCap")
                .colorColumn("ChangeAsNumber")
                .build();

    }

    private Table dataTable;
    private String chartType;
    private String[] rowColumns;
    private String[] dataColumns;
    private String[] labelColumns;
    private String[] detailColumns;
    private String colorColumn;
    private String sizeColumn;

    public ChartBuilder() {

    }

    public static ChartBuilder createBuilder() {
        return createBuilder("plotly"); // Plotly is the default chart renderer
    }

    public static ChartBuilder createBuilder(String chartRenderer) {
        if ( "plotly".equals(chartRenderer)) {
            return new PlotlyChartBuilder();
        } else if ( "chartjs".equals(chartRenderer)) {
            return new ChartjsChartBuilder();
        } else if ( "vega".equals(chartRenderer)) {
            return new ChartjsChartBuilder();
        } else {
            return new PlotlyChartBuilder();
        }
    }

    public ChartBuilder dataTable(Table dataTable) {
        this.dataTable = dataTable;
        return this;
    }

    public ChartBuilder chartType(String chartType) {
        this.chartType = chartType;
        return this;
    }

    public ChartBuilder rowColumns(String... columns) {
        this.rowColumns = columns;
        return this;
    }

    public ChartBuilder dataColumns(String... columns) {
        this.dataColumns = columns;
        return this;
    }

    public ChartBuilder labelColumns(String... columns) {
        this.labelColumns = columns;
        return this;
    }

    public ChartBuilder detailColumns(String... columns) {
        this.detailColumns = columns;
        return this;
    }

    public ChartBuilder colorColumn(String column) {
        this.colorColumn = column;
        return this;
    }

    public ChartBuilder sizeColumn(String column) {
        this.sizeColumn = column;
        return this;
    }

    public Figure build() {

        Figure figure;

        if ( "horizontalbar".equals(chartType) ) {
            figure = new Figure();
        } else if ( "verticalbar".equals(chartType) ) {
            figure = new Figure();
        } else if ( "treemap".equals(chartType) ) {
            figure = new Figure();
        } else if ( "sunburst".equals(chartType) ) {
            figure = new Figure();
        } else {
            figure = new Figure();
        }

        return figure;
    }

}
