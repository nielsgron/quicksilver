package tech.tablesaw.plotly.api;

import java.util.Map;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.event.EventHandler;
import tech.tablesaw.plotly.traces.TreemapTrace;

public class TreemapPlot {

    public static Figure create(String title, String[] ids, Object[] labels, Object[] labelParents) {
        return create(Layout.builder(title).build(), ids, labels, labelParents, null);
    }

    public static Figure create(Layout layout, String[] ids, Object[] labels, Object[] labelParents, Map<String, Object[]> extra) {
        return create(layout, ids, labels, labelParents, extra, new EventHandler[0]);
    }

    public static Figure create(Layout layout, Table table, String idColumn, String labelsColumn, String parentsColumn, Map<String, Object[]> extra, EventHandler[] handlers) {
        return create(layout,
                table.stringColumn(idColumn).asObjectArray(),
                table.stringColumn(labelsColumn).asObjectArray(),
                table.stringColumn(parentsColumn).asObjectArray(),
                extra,
                handlers);
    }

    public static Figure create(Layout layout, String[] ids, Object[] labels, Object[] labelParents, Map<String, Object[]> extra,
            EventHandler[] handlers) {
        sanitize(labels);
        sanitize(labelParents);
        TreemapTrace trace = TreemapTrace.builder(
                ids,
                labels,
                labelParents,
                extra)
                .build();
        Figure.FigureBuilder builder = Figure.builder()
                .layout(layout)
                .addTraces(trace)
                .addEventHandlers(handlers);
        return builder.build();
    }

    //see https://github.com/jtablesaw/tablesaw/pull/699
    static void sanitize(Object[] list) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] instanceof String) {
                list[i] = ((String) list[i]).replaceAll("\\'", "\\\\'");
            }
        }
    }

}
