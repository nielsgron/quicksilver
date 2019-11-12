package tech.tablesaw.charts.impl.plotly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import quicksilver.webapp.simpleui.bootstrap4.charts.TSTreeMapChartPanel;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.charts.impl.plotly.plots.*;
import tech.tablesaw.plotly.api.Histogram;
import tech.tablesaw.plotly.api.SunburstPlot;
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
            PlotlyAreaPlot plot = new PlotlyAreaPlot(layout, dataTable, rowColumns[0], dataColumns[0]);
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
            //figure = BubblePlot.create("", table, xCol, yCol, sizeColumn);
            PlotlyBubblePlot plot = new PlotlyBubblePlot(layout, dataTable, rowColumns[0], dataColumns[0], sizeColumn);
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
            PlotlyCandlestickPlot plot = new PlotlyCandlestickPlot(layout, dataTable, rowColumns[0], dataColumns[0], dataColumns[1], dataColumns[2], dataColumns[3]);
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
            PlotlyHeatMapPlot plot = new PlotlyHeatMapPlot(layout, dataTable, rowColumns[0], rowColumns[1]);
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
            PlotlyHistogramPlot plot = new PlotlyHistogramPlot(layout, dataTable, dataColumns[0]);
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
            PlotlyHorizontalBarPlot plot = new PlotlyHorizontalBarPlot(layout, dataTable, rowColumns[0], dataColumns[0]);
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
            PlotlyLinePlot plot = new PlotlyLinePlot(layout, dataTable, rowColumns[0], dataColumns[0]);
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
            PlotlyOHLCPlot plot = new PlotlyOHLCPlot(layout, dataTable, rowColumns[0], dataColumns[0], dataColumns[1], dataColumns[2], dataColumns[3]);
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
            PlotlyPiePlot plot = new PlotlyPiePlot(layout, dataTable, rowColumns[0], dataColumns[0]);
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
            PlotlyScatterPlot plot = new PlotlyScatterPlot(layout, dataTable, rowColumns[0], dataColumns[0]);
            figure = plot.getFigure();
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return figure;
    }

    @Override
    protected Figure buildSunburst() {
        Figure figure =null;
        return figure;
    }

    @Override
    protected Figure buildTimeseries() {
        Figure figure =null;

        try {
            //figure = TimeSeriesPlot.create("", table, dateColXName, yColName);
            PlotlyTimeSeriesPlot plot = new PlotlyTimeSeriesPlot(layout, dataTable, rowColumns[0], dataColumns[0]);
            figure = plot.getFigure();

        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return figure;
    }

    @Override
    protected Figure buildTreemap() {
        Figure figure =null;
        try {
            List<String> cols = new ArrayList<>();
            cols.addAll(Arrays.asList(rowColumns));
            cols.addAll(Arrays.asList(dataColumns));
            figure = TreemapPlot.create(layout, dataTable, true /* ? */, cols.toArray(String[]::new), Collections.EMPTY_MAP, Collections.EMPTY_MAP, new EventHandler[0]);
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
            PlotlyVerticalBarPlot plot = new PlotlyVerticalBarPlot(layout, dataTable, rowColumns[0], dataColumns[0]);
            figure = plot.getFigure();
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return figure;
    }

}
