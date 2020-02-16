package tech.tablesaw.charts.impl.plotly.plots;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tech.tablesaw.api.Table;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.plotly.api.TableExtract;
import tech.tablesaw.plotly.api.TreemapPlot;
import tech.tablesaw.plotly.event.EventHandler;

public class PlotlyTreeMapPlot extends PlotlyAbstractPlot {

    public PlotlyTreeMapPlot(ChartBuilder chartBuilder) {
        setChartBuilder(chartBuilder);

        // TODO : columnForDetails -

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

        Table table = TableExtract.unique(TableExtract.aggregate(this.table, columnsForViewColumns, extraCols.stream().toArray(String[]::new)), "ids");

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
        } else if (traceColors != null && traceColors.length > 0) {
            extra.put("marker.colors", traceColors);
        }

        EventHandler[] eventHandlers = eventHandler == null ? new EventHandler[0] : new EventHandler[]{eventHandler};

        setFigure(TreemapPlot.create(layout, config, table,
                "ids", "Label", "Parent",
                extra,
                eventHandlers));
    }

    static String getWithDefaultAggregation(String measure, String defaultAgg) {
        if (TableExtract.agg(measure) == null) {
            return defaultAgg + " [" + measure + "]";
        }
        return measure;
    }

}
