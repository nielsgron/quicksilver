package tech.tablesaw.plotly.api;

import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.components.Config;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.event.EventHandler;
import tech.tablesaw.plotly.traces.SunburstTrace;

public class SunburstPlot {

    public static Figure create(Layout layout, Config config, String[] ids, Object[] labels, Object[] labelParents, double[] values, EventHandler[] handlers) {
        SunburstTrace trace = SunburstTrace.builder(
                ids,
                labels,
                labelParents,
                values)
                .build();
        Figure.FigureBuilder builder = Figure.builder()
                .layout(layout)
                .config(config)
                .addTraces(trace)
                .addEventHandlers(handlers);
        return builder.build();
    }

    public static Figure create(Layout layout, Config config, Table table, String idColumn, String labelsColumn, String parentsColumn, String valuesColumn, EventHandler[] handlers) {
        return create(layout,
                config,
                table.stringColumn(idColumn).asObjectArray(),
                table.column(labelsColumn).asObjectArray(),
                table.column(parentsColumn).asObjectArray(),
                table.doubleColumn(valuesColumn).asDoubleArray(),
                handlers);
    }
}
