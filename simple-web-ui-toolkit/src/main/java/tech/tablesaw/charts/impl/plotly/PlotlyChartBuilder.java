package tech.tablesaw.charts.impl.plotly;

import tech.tablesaw.charts.Chart;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.charts.impl.plotly.plots.*;
import tech.tablesaw.plotly.components.Grid;
import tech.tablesaw.plotly.components.Layout;

/**
 * Supported chart type options:
 *
 * <ul>
 * <li>Layout.BarMode - sets the bar mode. Useful to create, eg. a stacked bar chart.
 * <li>Grid - sets a layout grid. Useful to create, eg. pie chart subplots.
 * </ul>
 */
public class PlotlyChartBuilder extends ChartBuilder {

    @Override
    public ChartBuilder layout(int width, int height, boolean enabledLegend) {

        switch (chartType) {
            case AREA:
                layout(width, height, 5, 20, 35, 5, enabledLegend);
                break;
            case BUBBLE:
                layout(width, height, 5, 20, 35, 5, enabledLegend);
                break;
            case CANDLESTICK:
                layout(width, height, 5, 5, 5, 5, enabledLegend);
                break;
            case HEATMAP:
                layout(width, height, 5, 20, 80, 5, enabledLegend);
                break;
            case HISTOGRAM:
                layout(width, height, 5, 20, 35, 20, enabledLegend);
                configureLayoutBuilderBarMode();
                break;
            case HORIZONTAL_BAR:
                layoutHorizontalBar(width, height, enabledLegend);
                break;
            case LINE:
                layout(width, height, 5, 20, 35, 5, enabledLegend);
                break;
            case OHLC:
                layout(width, height, 5, 5, 5, 5, enabledLegend);
                break;
            case PIE:
                layout(width, height, 5, 40, 5, 5, enabledLegend);
                configureLayoutBuilderBarMode();
                break;
            case SCATTERPLOT:
                layout(width, height, 5, 20, 35, 5, enabledLegend);
                break;
            case SUNBURST:
                layout(width, height, 0, 0, 0, 0, enabledLegend);
                break;
            case TIMESERIES:
                layout(width, height, 5, 20, 35, 5, enabledLegend);
                break;
            case TREEMAP:
                layout(width, height, 0, 0, 0, 0, enabledLegend);
                break;
            case VERTICAL_BAR:
                layoutVerticalBar(width, height, enabledLegend);
                break;
            default:
                layout(width, height, 0, 0, 0, 0, enabledLegend);
                break;

        }

        return this;
    }

    @Override
    protected Chart buildArea() {
        try {
            PlotlyAreaPlot plot = new PlotlyAreaPlot(this, groupBy);
            return plot;
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected Chart buildBubble() {
        try {
            PlotlyBubblePlot plot = new PlotlyBubblePlot(this);
            return plot;
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected Chart buildCandlestick() {
        try {
            PlotlyCandlestickPlot plot = new PlotlyCandlestickPlot(this);
            return plot;
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected Chart buildHeatmap() {
        try {
            PlotlyHeatMapPlot plot = new PlotlyHeatMapPlot(this);
            return plot;
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected Chart buildHistogram() {
        try {
            PlotlyHistogramPlot plot = new PlotlyHistogramPlot(this);
            return plot;
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected Chart buildHorizontalBar() {
        try {
            PlotlyHorizontalBarPlot plot = new PlotlyHorizontalBarPlot(this);
            return plot;
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected Chart buildLine() {
        try {
            PlotlyLinePlot plot = new PlotlyLinePlot(this, groupBy);
            return plot;
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected Chart buildOHLC() {
        try {
            PlotlyOHLCPlot plot = new PlotlyOHLCPlot(this);
            return plot;
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected Chart buildPie() {
        try {
            PlotlyPiePlot plot = new PlotlyPiePlot(this);
            return plot;
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected Chart buildScatterplot() {
        try {
            PlotlyScatterPlot plot = new PlotlyScatterPlot(this, groupBy);
            return plot;
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected Chart buildSunburst() {
        try {
            PlotlySunburstPlot plot = new PlotlySunburstPlot(this);
            return plot;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected Chart buildTimeseries() {
        try {
            PlotlyTimeSeriesPlot plot = new PlotlyTimeSeriesPlot(this);
            return plot;
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected Chart buildTreemap() {
        try {
            PlotlyTreeMapPlot plot = new PlotlyTreeMapPlot(this);
            return plot;
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected Chart buildVerticalBar() {
        try {
            PlotlyVerticalBarPlot plot = new PlotlyVerticalBarPlot(this);
            return plot;
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
    }

    private void layoutHorizontalBar(int width, int height, boolean enabledLegend) {
        layout(width, height, 5, 20, 55, 5, enabledLegend);
        configureLayoutBuilderBarMode();
    }

    private void layoutVerticalBar(int width, int height, boolean enabledLegend) {
        layout(width, height, 5, 40, 35, 5, enabledLegend);
        configureLayoutBuilderBarMode();
    }

    private void configureLayoutBuilderBarMode() {
        for (Object o : chartTypeOptions) {
            if (o instanceof Layout.BarMode) {
                //EMI: XXX: Normally this should be called on bar charts only if a color column is set
                // (but seems harmless since otherwise each row column gets a separate figure so there's nothing to stack anyhow)
                layoutBuilder.barMode((Layout.BarMode) o);
            }
            if (o instanceof Grid) {
                layoutBuilder.grid((Grid) o);
            }
        }
    }

}
