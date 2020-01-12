package tech.tablesaw.charts.impl.plotly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tech.tablesaw.api.Table;
import tech.tablesaw.charts.Chart;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.charts.impl.plotly.plots.*;
import tech.tablesaw.plotly.api.SunburstPlot;
import tech.tablesaw.plotly.api.TableExtract;
import tech.tablesaw.plotly.api.TreemapPlot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.event.EventHandler;

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
            PlotlyAreaPlot plot = new PlotlyAreaPlot(this);
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
            PlotlyLinePlot plot = new PlotlyLinePlot(this);
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
            PlotlyScatterPlot plot = new PlotlyScatterPlot(this);
            return plot;
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected Chart buildSunburst() {
        Figure figure =null;
        try {
            if (columnForSize == null) {
                throw new IllegalStateException("Missing size column");
            }

            Table table = TableExtract.unique(TableExtract.aggregate(dataTable, columnsForViewColumns, new String[]{getWithDefaultAggregation(columnForSize, "ZERO")}), "ids");

            EventHandler[] eventHandlers = eventHandler == null ? new EventHandler[0] : new EventHandler[]{eventHandler};
                    
            figure = SunburstPlot.create(layout, table,
                    "ids", "Label", "Parent",
                    columnForSize,
                    eventHandlers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PlotlyChart(figure);
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

    static String getWithDefaultAggregation(String measure, String defaultAgg) {
        if (TableExtract.agg(measure) == null) {
            return defaultAgg + " [" + measure + "]";
        }
        return measure;
    }

    @Override
    protected Chart buildTreemap() {
        Figure figure =null;
        try {
            
            List<String> extraCols = new ArrayList<>();
            if(columnForSize != null) {
                //For columnForSize(), we default to SUM()
                String sizeWithAgg = getWithDefaultAggregation(columnForSize, "SUM");
                extraCols.add(sizeWithAgg);
            }
            if (columnsForLabels != null && columnsForLabels.length > 0) {
                String labelWithAgg = getWithDefaultAggregation(columnsForLabels[0], "EMPTY");
                extraCols.add(labelWithAgg);
            }
            if(columnForColor !=null){
                //For columnForColor(), we default to MEAN (aka average)
                String colorWithAgg = getWithDefaultAggregation(columnForColor, "MEAN");
                extraCols.add(colorWithAgg);
            }
            
            Table table = TableExtract.unique(TableExtract.aggregate(dataTable, columnsForViewColumns, extraCols.stream().toArray(String[]::new)), "ids");
            
            Map<String, Object[]> extra = new HashMap<>();
            if (columnForSize != null) {
                extra.put("values", table.column(TableExtract.measure(columnForSize)).asObjectArray());
            }
            if (columnsForLabels != null && columnsForLabels.length > 0) {
                //TODO: log if columnsForDetails has more then 1 item?
                extra.put("text", table.column(TableExtract.measure(columnsForLabels[0])).asObjectArray());
            }
            if (columnForColor != null) {
                extra.put("marker.colors", table.column(TableExtract.measure(columnForColor)).asObjectArray());
            }

            EventHandler[] eventHandlers = eventHandler == null ? new EventHandler[0] : new EventHandler[]{eventHandler};

            figure = TreemapPlot.create(layout, table,
                    "ids", "Label", "Parent",
                    extra,
                    eventHandlers);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return new PlotlyChart(figure);
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
        }
    }

}
