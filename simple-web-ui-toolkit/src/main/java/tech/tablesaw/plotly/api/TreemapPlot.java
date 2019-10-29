package tech.tablesaw.plotly.api;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.traces.TreemapTrace;

public class TreemapPlot {

    public static Figure create(String title, Table table, String... cols) {
        return create(Layout.builder(title).build(), table, cols);
    }

    /**
     * @param cols the columns in hierarchy order (smallest element first,
     * parent last)
     */
    public static Figure create(Layout layout, Table table, String... cols) {
        if (cols.length < 2) {
            throw new IllegalStateException("At least two columns needed");
        }
        //it's important the (child, parent) pairs map is sorted to build the list more easily later on
        TreeMap<String, String> pairs = pairs(table, cols);
        Set<String> children = pairs.keySet();
        Collection<String> parents = pairs.values();

        Set<String> uniqueParents = new HashSet<>(parents);
        uniqueParents.removeAll(children);

        Object[] labels = new Object[children.size() + uniqueParents.size()];
        //fill labels with both children and parents
        children.toArray(labels);
        System.arraycopy(uniqueParents.toArray(), 0, labels, children.size(), uniqueParents.size());

        Object[] labelParents = new Object[labels.length];
        parents.toArray(labelParents);
        //other labels are empty
        Arrays.fill(labelParents, parents.size(), labelParents.length, "");

        return create(layout, labels, labelParents);
    }

    public static Figure create(String title, Object[] labels, Object[] labelParents) {
        return create(Layout.builder(title).build(), labels, labelParents);
    }

    public static Figure create(Layout layout, Object[] labels, Object[] labelParents) {
        sanitize(labels);
        sanitize(labelParents);
        TreemapTrace trace = TreemapTrace.builder(
                labels,
                labelParents)
                .build();
        return new Figure(layout, trace);
    }

    private static TreeMap<String, String> pairs(Table table, String... cols) {
        TreeMap<String, String> pairs = new TreeMap<>();

        for (Row row : table) {
            String child = row.getString(cols[0]);
            for (int i = 1; i < cols.length; i++) {
                String parent = row.getString(cols[i]);
                if(parent.equals(child)) {
                    //Do nothing if eg. Conglomerates -> Conglomerates
                } else {
                    pairs.put(child, parent);
                }
                child = parent;
            }
        }

        return pairs;
    }

    //see https://github.com/jtablesaw/tablesaw/pull/699
    private static void sanitize(Object[] list) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] instanceof String) {
                list[i] = ((String) list[i]).replaceAll("\\'", "\\\\'");
            }
        }
    }

}
