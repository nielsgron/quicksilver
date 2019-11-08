package tech.tablesaw.plotly.api;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.event.EventHandler;
import tech.tablesaw.plotly.traces.SunburstTrace;

public class SunburstPlot {

    public static Figure create(String title, Table table, String... cols) {
        return create(Layout.builder(title).build(), table, cols);
    }

    public static Figure create(Layout layout, Table table, String... cols) {
        return create(layout, table, new EventHandler[0], cols);
    }

    /**
     * @param cols the columns in hierarchy order (smallest element first,
     * parent last). Last column is the numeric value.
     */
    public static Figure create(Layout layout, Table table, EventHandler[] handlers, String... cols) {
        //TODO: Support if(cols.length == 2)
        if (cols.length < 3) {
            throw new IllegalStateException("At least three columns needed");
        }
        String[] hierarchy = Arrays.copyOf(cols, cols.length - 1);
        Extract.TableInfo info = Extract.createPairs(table, hierarchy,
                Collections.singletonMap("values", cols[cols.length - 1]),
                Collections.singletonMap("values", 0d));

        Object[] labels = info.labels;
        Object[] labelParents = info.labelParents;
        double[] values = Stream.of(info.attributeLists.get("values")).mapToDouble(o -> ((Double)o)).toArray();

        return create(layout, info.ids, labels, labelParents, values, handlers);
    }

    public static Figure create(String title, String[] ids, Object[] labels, Object[] labelParents, double[] values) {
        return create(Layout.builder(title).build(), ids, labels, labelParents, values, new EventHandler[0]);
    }

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

}
