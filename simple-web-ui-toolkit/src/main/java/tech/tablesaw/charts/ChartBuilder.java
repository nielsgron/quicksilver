package tech.tablesaw.charts;

import org.apache.commons.lang3.NotImplementedException;
import tech.tablesaw.api.Table;
import tech.tablesaw.charts.impl.calheatmap.CalHeatmapChartBuilder;
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
                .columnsForViewColumns("Sector", "Industry")
                .columnsForViewRows("MarketCap")
                .columnForColor("ChangeAsNumber")
                .build();
        */

    }

    public enum CHART_RENDERER {
        PLOTLY, CHARTJS, VEGA, CALHEATMAP
    }

    public enum CHART_TYPE {
        AREA, BUBBLE, CANDLESTICK, HEATMAP, HEATMAP_CALENDAR, HISTOGRAM, HORIZONTAL_BAR, LINE, OHLC, PIE, SCATTERPLOT, SUNBURST, TIMESERIES, TREEMAP, VERTICAL_BAR
    }

    protected Layout.LayoutBuilder layoutBuilder = Layout.builder();

    protected Table dataTable;
    protected CHART_TYPE chartType;
    protected Layout layout;
    protected String divName;
    protected String[] columnsForViewColumns;
    protected String[] columnsForViewRows;
    protected String[] columnsForLabels;
    protected String[] columnsForDetails;
    protected String columnForColor;
    protected String columnForSize;
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

            case CALHEATMAP:
                return new CalHeatmapChartBuilder();

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

    public ChartBuilder columnsForViewColumns(String... columns) {
        this.columnsForViewColumns = columns;
        return this;
    }

    public ChartBuilder columnsForViewRows(String... columns) {
        this.columnsForViewRows = columns;
        return this;
    }

    public ChartBuilder columnsForLabels(String... columns) {
        this.columnsForLabels = columns;
        return this;
    }

    public ChartBuilder columnsForDetails(String... columns) {
        this.columnsForDetails = columns;
        return this;
    }

    public ChartBuilder columnForColor(String column) {
        this.columnForColor = column;
        return this;
    }

    public ChartBuilder columnForSize(String column) {
        this.columnForSize = column;
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
            case HEATMAP_CALENDAR:
                return buildHeatmapCalendar();
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
                throw new NotImplementedException("Not Implemented");

        }
    }

    protected Figure buildArea() {
        throw new NotImplementedException("Not Implemented");
    }

    protected Figure buildBubble(){
        throw new NotImplementedException("Not Implemented");
    }

    protected Figure buildCandlestick(){
        throw new NotImplementedException("Not Implemented");
    }

    protected Figure buildHeatmap(){
        throw new NotImplementedException("Not Implemented");
    }

    protected Figure buildHeatmapCalendar(){
        throw new NotImplementedException("Not Implemented");
    }

    protected Figure buildHistogram(){
        throw new NotImplementedException("Not Implemented");
    }

    protected Figure buildHorizontalBar(){
        throw new NotImplementedException("Not Implemented");
    }

    protected Figure buildLine(){
        throw new NotImplementedException("Not Implemented");
    }

    protected Figure buildOHLC(){
        throw new NotImplementedException("Not Implemented");
    }

    protected Figure buildPie(){
        throw new NotImplementedException("Not Implemented");
    }

    protected Figure buildScatterplot(){
        throw new NotImplementedException("Not Implemented");
    }

    protected Figure buildSunburst(){
        throw new NotImplementedException("Not Implemented");
    }

    protected Figure buildTimeseries(){
        throw new NotImplementedException("Not Implemented");
    }

    protected Figure buildTreemap(){
        throw new NotImplementedException("Not Implemented");
    }

    protected Figure buildVerticalBar(){
        throw new NotImplementedException("Not Implemented");
    }

}
