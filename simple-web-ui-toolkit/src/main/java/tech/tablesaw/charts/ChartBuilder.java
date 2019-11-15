package tech.tablesaw.charts;

import tech.tablesaw.api.Table;
import tech.tablesaw.charts.impl.chartjs.ChartjsChartBuilder;
import tech.tablesaw.charts.impl.plotly.PlotlyChartBuilder;
import tech.tablesaw.charts.impl.vega.VegaChartBuilder;
import tech.tablesaw.plotly.components.Axis;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.components.Margin;
import tech.tablesaw.plotly.event.EventHandler;

public abstract class ChartBuilder {

    static {

        DEFAULT_CHART_RENDERER = CHART_RENDERER.PLOTLY; // Plotly is the default chart renderer

        /*
        // Example Usage :
        Table table = Table.create();
        Figure figure = ChartBuilder.createBuilder().dataTable(table).chartType(CHART_TYPE.TREEMAP)
                .rowColumns("Sector", "Industry")
                .dataColumns("MarketCap")
                .colorColumn("ChangeAsNumber")
                .build();
        */

    }

    public enum CHART_RENDERER {
        PLOTLY, CHARTJS, VEGA
    }

    public enum CHART_TYPE {
        AREA, BUBBLE, CANDLESTICK, HEATMAP, HISTOGRAM, HORIZONTAL_BAR, LINE, OHLC, PIE, SCATTERPLOT, SUNBURST, TIMESERIES, TREEMAP, VERTICAL_BAR
    }

    protected Layout.LayoutBuilder layoutBuilder = Layout.builder();

    protected Table dataTable;
    protected CHART_TYPE chartType;
    protected Layout layout;
    protected String divName;
    protected String[] rowColumns;
    protected String[] dataColumns;
    protected String[] labelColumns;
    protected String[] detailColumns;
    protected String colorColumn;
    protected String sizeColumn;
    protected EventHandler eventHandler;

    public ChartBuilder() {

    }

    public static CHART_RENDERER DEFAULT_CHART_RENDERER;

    public static ChartBuilder createBuilder() {
        return createBuilder(DEFAULT_CHART_RENDERER);
    }

    public static ChartBuilder createBuilder(CHART_RENDERER chartRenderer) {

        switch (chartRenderer) {

            case PLOTLY:
                return new PlotlyChartBuilder();

            case CHARTJS:
                return new ChartjsChartBuilder();

            case VEGA:
                return new VegaChartBuilder();

            default:
                return new PlotlyChartBuilder();
        }
    }

    public ChartBuilder dataTable(Table dataTable) {
        this.dataTable = dataTable;
        return this;
    }

    public ChartBuilder chartType(CHART_TYPE chartType) {
        this.chartType = chartType;
        return this;
    }

    public ChartBuilder layout(Layout layout) {
        this.layout = layout;
        return this;
    }

    public ChartBuilder layout(int width, int height, boolean enabledLegend) {
        layout(width, height, 0, 0, 0, 0, enabledLegend);
        return this;
    }

    public ChartBuilder layout(int width, int height, int marginTop, int marginBottom, int marginLeft, int marginRight, boolean enabledLegend) {

        layoutBuilder
                .width(width)
                .height(height)
                .margin(Margin.builder()
                        .top(marginTop)
                        .bottom(marginBottom)
                        .left(marginLeft)
                        .right(marginRight)
                        .build())
                .showLegend(enabledLegend).build();

        return this;
    }

    public ChartBuilder axisTitles(String xTitle, String yTitle) {

        layoutBuilder.xAxis(Axis.builder().title(xTitle).build());
        layoutBuilder.yAxis(Axis.builder().title(yTitle).build());

        return this;
    }

    public ChartBuilder divName(String divName) {
        this.divName = divName;
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

    public ChartBuilder eventHandler(EventHandler handler) {
        this.eventHandler = handler;
        return this;
    }

    // Getter methods
    public String getDivName() {
        return divName;
    }

    public Figure build() {

        layout = layoutBuilder.build();

        switch (chartType) {
            case AREA:
                return buildArea();
            case BUBBLE:
                return buildBubble();
            case CANDLESTICK:
                return buildCandlestick();
            case HEATMAP:
                return buildHeatmap();
            case HISTOGRAM:
                return buildHistogram();
            case HORIZONTAL_BAR:
                return buildHorizontalBar();
            case LINE:
                return buildLine();
            case OHLC:
                return buildOHLC();
            case PIE:
                return buildPie();
            case SCATTERPLOT:
                return buildScatterplot();
            case SUNBURST:
                return buildSunburst();
            case TIMESERIES:
                return buildTimeseries();
            case TREEMAP:
                return buildTreemap();
            case VERTICAL_BAR:
                return buildVerticalBar();
            default:
                return null;

        }
    }

    protected abstract Figure buildArea();

    protected abstract Figure buildBubble();

    protected abstract Figure buildCandlestick();

    protected abstract Figure buildHeatmap();

    protected abstract Figure buildHistogram();

    protected abstract Figure buildHorizontalBar();

    protected abstract Figure buildLine();

    protected abstract Figure buildOHLC();

    protected abstract Figure buildPie();

    protected abstract Figure buildScatterplot();

    protected abstract Figure buildSunburst();

    protected abstract Figure buildTimeseries();

    protected abstract Figure buildTreemap();

    protected abstract Figure buildVerticalBar();

}
