package tech.tablesaw.charts.impl.plotly.plots;

import tech.tablesaw.api.Table;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.plotly.api.SunburstPlot;
import tech.tablesaw.plotly.api.TableExtract;
import tech.tablesaw.plotly.event.EventHandler;

public class PlotlySunburstPlot extends PlotlyAbstractPlot {

    public PlotlySunburstPlot(ChartBuilder chartBuilder) {
        setChartBuilder(chartBuilder);

        // TODO : columnForLabels -
        // TODO : columnForDetails -
        // TODO : columnForColor -

        if (columnForSize == null) {
            throw new IllegalStateException("Missing size column");
        }

        Table table = TableExtract.unique(TableExtract.aggregate(this.table, columnsForViewColumns, new String[]{getWithDefaultAggregation(columnForSize, "ZERO")}), "ids");

        EventHandler[] eventHandlers = eventHandler == null ? new EventHandler[0] : new EventHandler[]{eventHandler};

        setFigure(SunburstPlot.create(layout, config, table,
                "ids", "Label", "Parent",
                columnForSize,
                eventHandlers));
    }

    static String getWithDefaultAggregation(String measure, String defaultAgg) {
        if (TableExtract.agg(measure) == null) {
            return defaultAgg + " [" + measure + "]";
        }
        return measure;
    }

}
