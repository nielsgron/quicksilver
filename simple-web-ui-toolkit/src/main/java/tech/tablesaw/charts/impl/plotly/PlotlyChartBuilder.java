package tech.tablesaw.charts.impl.plotly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tech.tablesaw.api.Table;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.charts.impl.plotly.plots.*;
import tech.tablesaw.plotly.api.SunburstPlot;
import tech.tablesaw.plotly.api.TableExtract;
import tech.tablesaw.plotly.api.TreemapPlot;
import tech.tablesaw.plotly.components.Figure;
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
                layout(width, height, 5, 20, 55, 5, enabledLegend);
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
                layout(width, height, 5, 40, 35, 5, enabledLegend);
                break;
            default:
                layout(width, height, 0, 0, 0, 0, enabledLegend);
                break;

        }

        return this;
    }

    @Override
    protected Figure buildArea() {
        Figure figure =null;

        try {
            //figure = AreaPlot.create("", table, xCol, yCol);
            PlotlyAreaPlot plot = new PlotlyAreaPlot(layout, dataTable, columnsForViewColumns[0], columnsForViewRows[0]);
            figure = plot.getFigure();
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return figure;
    }

    @Override
    protected Figure buildBubble() {
        Figure figure =null;

        try {
            //figure = BubblePlot.create("", table, xCol, yCol, columnForSize);
            PlotlyBubblePlot plot = new PlotlyBubblePlot(layout, dataTable, columnsForViewColumns[0], columnsForViewRows[0], columnForSize);
            figure = plot.getFigure();

        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return figure;
    }

    @Override
    protected Figure buildCandlestick() {
        Figure figure =null;

        try {
            //figure = CandlestickPlot.create("", table, xCol, openCol, highCol, lowCol, closeCol);
            PlotlyCandlestickPlot plot = new PlotlyCandlestickPlot(layout, dataTable, columnsForViewColumns[0], columnsForViewRows[0], columnsForViewRows[1], columnsForViewRows[2], columnsForViewRows[3]);
            figure = plot.getFigure();

        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return figure;
    }

    @Override
    protected Figure buildHeatmap() {
        Figure figure =null;

        try {
            //figure = Heatmap.create("", table, categoryCol1, categoryCol2);
            PlotlyHeatMapPlot plot = new PlotlyHeatMapPlot(layout, dataTable, columnsForViewColumns[0], columnsForViewColumns[1]);
            figure = plot.getFigure();
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return figure;
    }

    @Override
    protected Figure buildHistogram() {
        Figure figure =null;

        try {
            //figure = Histogram.create("", table, numericColumnName);
            PlotlyHistogramPlot plot = new PlotlyHistogramPlot(layout, dataTable, columnsForViewRows[0]);
            figure = plot.getFigure();
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return figure;
    }

    @Override
    protected Figure buildHorizontalBar() {
        Figure figure =null;

        try {
            //figure = createFigure(layout, table, groupColName, numberColName);
            PlotlyHorizontalBarPlot plot = new PlotlyHorizontalBarPlot(layout, dataTable, columnsForViewColumns[0], columnsForViewRows[0]);
            figure = plot.getFigure();
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return figure;
    }

    @Override
    protected Figure buildLine() {
        Figure figure =null;

        try {
            //figure = LinePlot.create("", table, xCol, yCol);
            PlotlyLinePlot plot = new PlotlyLinePlot(layout, dataTable, columnsForViewColumns[0], columnsForViewRows[0]);
            figure = plot.getFigure();
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return figure;
    }

    @Override
    protected Figure buildOHLC() {
        Figure figure =null;

        try {
            //figure = OHLCPlot.create("", table, xCol, openCol, highCol, lowCol, closeCol);
            PlotlyOHLCPlot plot = new PlotlyOHLCPlot(layout, dataTable, columnsForViewColumns[0], columnsForViewRows[0], columnsForViewRows[1], columnsForViewRows[2], columnsForViewRows[3]);
            figure = plot.getFigure();
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return figure;
    }

    @Override
    protected Figure buildPie() {
        Figure figure =null;

        try {
            //figure = PiePlot.create("", table, groupColName, numberColName);
            PlotlyPiePlot plot = new PlotlyPiePlot(layout, dataTable, columnsForViewColumns[0], columnsForViewRows[0]);
            figure = plot.getFigure();
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return figure;
    }

    @Override
    protected Figure buildScatterplot() {
        Figure figure =null;

        try {
            //figure = ScatterPlot.create("", table, xCol, yCol);
            PlotlyScatterPlot plot = new PlotlyScatterPlot(layout, dataTable, columnsForViewColumns[0], columnsForViewRows[0]);
            figure = plot.getFigure();
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return figure;
    }

    @Override
    protected Figure buildSunburst() {
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
        return figure;
    }

    @Override
    protected Figure buildTimeseries() {
        Figure figure =null;

        try {
            //figure = TimeSeriesPlot.create("", table, dateColXName, yColName);
            PlotlyTimeSeriesPlot plot = new PlotlyTimeSeriesPlot(layout, dataTable, columnsForViewColumns[0], columnsForViewRows[0]);
            figure = plot.getFigure();

        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return figure;
    }

    static String getWithDefaultAggregation(String measure, String defaultAgg) {
        if (TableExtract.agg(measure) == null) {
            return defaultAgg + " [" + measure + "]";
        }
        return measure;
    }

    @Override
    protected Figure buildTreemap() {
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
        return figure;
    }

    @Override
    protected Figure buildVerticalBar() {
        Figure figure =null;

        try {
            //figure = VerticalBarPlot.create("", table, groupColName, numberColName);
            PlotlyVerticalBarPlot plot = new PlotlyVerticalBarPlot(layout, dataTable, columnsForViewColumns[0], columnsForViewRows[0]);
            figure = plot.getFigure();
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return figure;
    }

}
