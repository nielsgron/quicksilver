package tech.tablesaw.plotly.api;

import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.event.EventHandler;
import tech.tablesaw.plotly.traces.SunburstTrace;

public class SunburstPlot {

    public static Figure create(Layout layout, String[] ids, Object[] labels, Object[] labelParents, double[] values, EventHandler[] handlers) {
        TreemapPlot.sanitize(labels);
        TreemapPlot.sanitize(labelParents);
        SunburstTrace trace = SunburstTrace.builder(
                ids,
                labels,
                labelParents,
                values)
                .build();
        Figure.FigureBuilder builder = Figure.builder()
                .layout(layout)
                .addTraces(trace)
                .addEventHandlers(handlers);
        return builder.build();
    }

    public static Figure create(Layout layout, Table table, String idColumn, String labelsColumn, String parentsColumn, String valuesColumn, EventHandler[] handlers) {
        return create(layout,
                table.stringColumn(idColumn).asObjectArray(),
                table.column(labelsColumn).asObjectArray(),
                table.column(parentsColumn).asObjectArray(),
                table.doubleColumn(valuesColumn).asDoubleArray(),
                handlers);
    }
}
