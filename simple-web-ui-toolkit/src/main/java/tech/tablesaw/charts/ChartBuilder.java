package tech.tablesaw.charts;

import org.apache.commons.lang3.NotImplementedException;
import tech.tablesaw.api.Table;
import tech.tablesaw.charts.impl.calheatmap.CalHeatmapChartBuilder;
import tech.tablesaw.charts.impl.chartjs.ChartjsChartBuilder;
import tech.tablesaw.charts.impl.plotly.PlotlyChartBuilder;
import tech.tablesaw.charts.impl.vega.VegaChartBuilder;
import tech.tablesaw.plotly.components.Axis;
import tech.tablesaw.plotly.components.Config;
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

    public enum Axes {
        Individual, MergedSharedScale
    }

    protected Layout.LayoutBuilder layoutBuilder;
    protected Config.Builder configBuilder;

    protected Table dataTable;
    protected CHART_TYPE chartType;
    protected Axes axes = DEFAULT_AXES;
    protected Object[] chartTypeOptions;
    protected Layout layout;
    protected Config config;
    protected String divName;
    protected String[] columnsForViewColumns;
    protected String[] columnsForViewRows;
    protected String[] columnsForLabels;
    protected String[] columnsForDetails;
    protected String columnForColor;
    protected String columnForSize;
    protected EventHandler eventHandler;

    protected String[] traceColors;

    //XXX: internal?
    protected String groupBy;

    public ChartBuilder() {

        initLayoutBuilder();
        configBuilder = Config.builder();
        configBuilder.displayModeBar(false);
    }

    protected void initLayoutBuilder() {
        layoutBuilder = Layout.builder();
    }

    public Layout.LayoutBuilder getLayoutBuilder() {
        return layoutBuilder;
    }

    public static CHART_RENDERER DEFAULT_CHART_RENDERER;
    public final static Axes DEFAULT_AXES = Axes.MergedSharedScale;

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

    public ChartBuilder chartType(CHART_TYPE chartType, Object... chartTypeOptions) {
        this.chartType = chartType;
        this.chartTypeOptions = chartTypeOptions;
        return this;
    }

    public ChartBuilder axesType(Axes axes) {
        this.axes = axes;
        return this;
    }

    public ChartBuilder groupBy(String col) {
        this.groupBy = col;
        return this;
    }

    public ChartBuilder layout(boolean enabledLegend) {
        layout(15, 40, 50, 15, enabledLegend);
        return this;
    }

    public ChartBuilder layout(int width, int height, boolean enabledLegend) {
        layout(width, height, 100, 80, 80, 80, enabledLegend);
        return this;
    }

    public ChartBuilder layout(int marginTop, int marginBottom, int marginLeft, int marginRight, boolean enabledLegend) {

        layoutBuilder
                .margin(Margin.builder()
                        .top(marginTop)
                        .bottom(marginBottom)
                        .left(marginLeft)
                        .right(marginRight)
                        .build())
                .showLegend(enabledLegend);

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
                .showLegend(enabledLegend);

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

    public ChartBuilder displayModeBar(boolean b) {
        configBuilder.displayModeBar(b);
        return this;
    }
    
    public ChartBuilder setTraceColors(String... colors) {
        this.traceColors = colors;
        return this;
    }

    // Getter methods
    public Axes getAxes() {
        return axes;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public String[] getTraceColors() {
        return traceColors;
    }

    public Config getConfig() {
        return config;
    }

    public String getDivName() {
        return divName;
    }

    public Chart build() {

        layout = layoutBuilder.build();
        config = configBuilder.build();

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

    protected Chart buildArea() {
        throw new NotImplementedException("Not Implemented");
    }

    protected Chart buildBubble(){
        throw new NotImplementedException("Not Implemented");
    }

    protected Chart buildCandlestick(){
        throw new NotImplementedException("Not Implemented");
    }

    protected Chart buildHeatmap(){
        throw new NotImplementedException("Not Implemented");
    }

    protected Chart buildHeatmapCalendar(){
        throw new NotImplementedException("Not Implemented");
    }

    protected Chart buildHistogram(){
        throw new NotImplementedException("Not Implemented");
    }

    protected Chart buildHorizontalBar(){
        throw new NotImplementedException("Not Implemented");
    }

    protected Chart buildLine(){
        throw new NotImplementedException("Not Implemented");
    }

    protected Chart buildOHLC(){
        throw new NotImplementedException("Not Implemented");
    }

    protected Chart buildPie(){
        throw new NotImplementedException("Not Implemented");
    }

    protected Chart buildScatterplot(){
        throw new NotImplementedException("Not Implemented");
    }

    protected Chart buildSunburst(){
        throw new NotImplementedException("Not Implemented");
    }

    protected Chart buildTimeseries(){
        throw new NotImplementedException("Not Implemented");
    }

    protected Chart buildTreemap(){
        throw new NotImplementedException("Not Implemented");
    }

    protected Chart buildVerticalBar(){
        throw new NotImplementedException("Not Implemented");
    }


    public Table getDataTable() {
        return dataTable;
    }
    public CHART_TYPE getChartType() { return chartType; }
    public Layout getLayout() { return layout; }

    public String[] getColumnsForViewColumns() { return columnsForViewColumns; }
    public String[] getColumnsForViewRows() { return columnsForViewRows; }
    public String[] getColumnsForLabels() { return columnsForLabels; }
    public String[] getColumnsForDetails() { return columnsForDetails; }

    public String getColumnsForColor() { return columnForColor; }
    public String getColumnsForSize() { return columnForSize; }

    public EventHandler getEventHandler() { return eventHandler; }


}
