package tech.tablesaw.plotly.api;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.traces.TreemapTrace;

public class TreemapPlot {

    /**
     * @param col the labels column name
     * @param parentCol the parent column name
     */
    public static Figure create(String title, Table table, String col, String parentCol) {
        Object[] children = table.column(col).asObjectArray();
        Object[] parents = table.column(parentCol).asObjectArray();

        Set<?> uniqueParents = new HashSet<>(Arrays.asList(parents));
        uniqueParents.removeAll(Arrays.asList(children));

        Object[] labels = new Object[children.length + uniqueParents.size()];

        //fill labels with both children and parents
        System.arraycopy(children, 0, labels, 0, children.length);
        System.arraycopy(uniqueParents.toArray(), 0, labels, children.length, uniqueParents.size());

        Object[] labelParents = new Object[labels.length];
        System.arraycopy(parents, 0, labelParents, 0, parents.length);
        //other labels are empty
        Arrays.fill(labelParents, parents.length, labelParents.length, "");

        return create(title, labels, labelParents);
    }

    public static Figure create(String title, Object[] labels, Object[] labelParents) {
        TreemapTrace trace = TreemapTrace.builder(
                labels,
                labelParents)
                .build();
        return new Figure(Layout.builder(title).build(), trace);
    }

}
