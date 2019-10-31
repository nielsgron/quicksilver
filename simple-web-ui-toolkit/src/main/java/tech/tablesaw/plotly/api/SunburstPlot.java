package tech.tablesaw.plotly.api;

import java.util.Arrays;
import java.util.TreeMap;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.columns.Column;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.traces.SunburstTrace;

public class SunburstPlot {

    public static Figure create(String title, Table table, String... cols) {
        return create(Layout.builder(title).build(), table, cols);
    }

    /**
     * @param cols the columns in hierarchy order (smallest element first,
     * parent last). Last column is the numeric value.
     */
    public static Figure create(Layout layout, Table table, String... cols) {
        //TODO: Support if(cols.length == 2)
        if (cols.length < 3) {
            throw new IllegalStateException("At least three columns needed");
        }
        String[] hierarchy = Arrays.copyOf(cols, cols.length - 1);
        TreemapPlot.TableInfo info = TreemapPlot.createPairs(table, hierarchy);
        TreeMap<?, Double> infoValue = pairs(table, table.column(cols[0]), (DoubleColumn) table.column(cols[cols.length - 1]));

        Object[] labels = info.labels;
        Object[] labelParents = info.labelParents;

        double[] values = new double[labels.length];
        for (int i = 0; i < labels.length; i++) {
            Double v = infoValue.get(labels[i]);
            if (v != null) {
                values[i] = v;
            }
        }

        return create(layout, info.ids, labels, labelParents, values);
    }

    static <K, V> TreeMap<K, V> pairs(Table table, Column<K> col1, Column<V> col2) {
        TreeMap<K, V> pairs = new TreeMap<>();
        for(int i=0;i<Math.min(col1.size(), col2.size());i++) {
            pairs.put(col1.get(i), col2.get(i));
        }
        return pairs;
    }


    public static Figure create(String title, String[] ids, Object[] labels, Object[] labelParents, double[] values) {
        return create(Layout.builder(title).build(), ids, labels, labelParents, values);
    }

    public static Figure create(Layout layout, String[] ids, Object[] labels, Object[] labelParents, double[] values) {
        TreemapPlot.sanitize(labels);
        TreemapPlot.sanitize(labelParents);
        SunburstTrace trace = SunburstTrace.builder(
                ids,
                labels,
                labelParents,
                values)
                .build();
        return new Figure(layout, trace);
    }

}
