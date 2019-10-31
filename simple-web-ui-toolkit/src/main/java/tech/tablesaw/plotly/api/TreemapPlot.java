package tech.tablesaw.plotly.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.traces.TreemapTrace;

public class TreemapPlot {

    public static Figure create(String title, Table table, boolean familyTree, String... cols) {
        return create(Layout.builder(title).build(), table, familyTree, cols);
    }

    static TableInfo createPairs(Table table, String... cols) {
        return createPairs(table, false, cols);
    }

    static TableInfo createPairs(Table table, boolean familyTree, String... cols) {
        if (cols.length < 2) {
            throw new IllegalStateException("At least two columns needed");
        }
        //it's important the (child, parent) pairs map is sorted to build the list more easily later on
        TreeMap<String, String> pairs = new TreeMap<>();
        TreeMap<String, String>[] idPairs = new TreeMap[cols.length];
        //for a family tree each column gets an id from the same pool
        TreeMap<String, String> globalIds = familyTree ? new TreeMap<>() : null;
        for (int i = 0; i < idPairs.length; i++) {
            idPairs[i] = familyTree ? globalIds : new TreeMap<>();
        }

        pairs(table, cols, new IdGenerator() {
            int[] colCounter = new int[cols.length];

            @Override
            public String id(Row row, int colSubIndex, String column) {
                if (familyTree) {
                    //same counter
                    int v = colCounter[0]++;
                    return "f-" + v;
                } else {
                    int v = colCounter[colSubIndex]++;
                    return colSubIndex + "-" + v;
                }
            }
        }, pairs, idPairs);

        List<String> ids = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        List<String> parents = new ArrayList<>();

        for (TreeMap<String, String> id : idPairs) {
            for (Map.Entry<String, String> e : id.entrySet()) {
                String label = e.getKey();
                String labelId = e.getValue();

                if (!ids.contains(labelId)) {
                    ids.add(labelId);
                    labels.add(label);

                    String parentId = pairs.get(labelId);
                    if (parentId == null) {
                        parentId = "";
                    }

                    parents.add(parentId);
                }

            }
        }

        return new TableInfo(ids.toArray(String[]::new), labels.toArray(), parents.toArray());
    }

    /**
     * @param cols the columns in hierarchy order (smallest element first,
     * parent last)
     */
    public static Figure create(Layout layout, Table table, boolean familyTree, String... cols) {
        TableInfo info = createPairs(table, familyTree, cols);
        Object[] labels = info.labels;
        Object[] labelParents = info.labelParents;

        return create(layout, info.ids, labels, labelParents);
    }

    static class TableInfo {

        String[] ids;
        Object[] labels;
        Object[] labelParents;

        public TableInfo(String[] ids, Object[] labels, Object[] labelParents) {
            this.ids = ids;
            this.labels = labels;
            this.labelParents = labelParents;
        }

    }

    public static Figure create(String title, String[] ids, Object[] labels, Object[] labelParents) {
        return create(Layout.builder(title).build(), ids, labels, labelParents);
    }

    public static Figure create(Layout layout, String[] ids, Object[] labels, Object[] labelParents) {
        sanitize(labels);
        sanitize(labelParents);
        TreemapTrace trace = TreemapTrace.builder(
                ids,
                labels,
                labelParents)
                .build();
        return new Figure(layout, trace);
    }

    interface IdGenerator {

        /**
         *
         * @param row the current row
         * @param colSubIndex the column index within the column cohort used (no
         * relation to the row column index)
         * @param column the colum name
         * @return global unique id for the given column
         */
        String id(Row row, int colSubIndex, String column);
    }

    private static void pairs(Table table, String[] cols, IdGenerator idGen, TreeMap<String, String> pairs, TreeMap<String, String>[] ids) {
        for (Row row : table) {
            String child = row.getString(cols[0]);

            //generate child id
            String childId = ids[0].get(child);
            if (childId == null) {
                childId = idGen.id(row, 0, cols[0]);
                ids[0].put(child, childId);
            }

            for (int i = 1; i < cols.length; i++) {
                String parent = row.getString(cols[i]);

                //generate parent id
                String parentId = ids[i].get(parent);
                if (parentId == null) {
                    parentId = idGen.id(row, i, cols[i]);
                    ids[i].put(parent, parentId);
                }

                pairs.put(childId, parentId);
                child = parent;
                childId = parentId;
            }
        }
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
