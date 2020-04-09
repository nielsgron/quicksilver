package tech.tablesaw.charts.impl.plotly.plots;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.table.TableSliceGroup;

public abstract class PlotlyAbstractBasicPlot extends PlotlyAbstractPlot {

    protected Stream<Figure> groupBy(String groupCol) {
        List<Table> tableList;

        if (groupCol != null) {
            TableSliceGroup tables = table.splitOn(table.categoricalColumn(groupCol));
            tableList = tables.asTableList();
        } else {
            tableList = new ArrayList<>();
            tableList.add(table);
        }

        return axes(tableList.stream());
    }

    protected Stream<Figure> axes(Stream<Table> tableList) {
        if (individualAxes) {
            return tableList
                    .flatMap(t -> colorColumn(Stream.of(t)));
        } else {
            return colorColumn(tableList);
        }
    }

    protected Stream<Figure> colorColumn(Stream<Table> tableList) {
        if (columnForColor != null && !columnForColor.isEmpty()) {
            return tableList
                    .flatMap(tab -> plainFigure(tab.splitOn(columnForColor).asTableList().stream()));
        }

        return plainFigure(tableList);
    }

    protected abstract Stream<Figure> plainFigure(Stream<Table> tableList);
}
