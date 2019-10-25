package tech.tablesaw.plotly.api;

import java.util.Arrays;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.traces.TreemapTrace;

public class TreemapPlot {

    /**
     *
     * @param title
     * @param table
     * @param col
     * @param parentCol the parent column, which is assumed to be a separate list of entities compared to `col`.
     * @return
     */
    public static Figure create(String title, Table table, String col, String parentCol) {
        Object[] children = table.column(col).asObjectArray();
        Object[] parents = table.column(parentCol).asObjectArray();

        Object[] labels = new Object[children.length + parents.length];

        //fill labels with both children and parents
        System.arraycopy(children, 0, labels, 0, children.length);
        System.arraycopy(parents, 0, labels, children.length, parents.length);

        Object[] labelParents = new Object[labels.length];
        System.arraycopy(parents, 0, labelParents, 0, parents.length);
        //other labels are empty
        Arrays.fill(labelParents, parents.length, labelParents.length, "");

        TreemapTrace trace = TreemapTrace.builder(
                labels,
                labelParents)
                .build();
        return new Figure(trace);
    }
}
