package tech.tablesaw.charts.impl.plotly.plots;

import tech.tablesaw.api.Table;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.traces.ScatterTrace;
import tech.tablesaw.table.TableSliceGroup;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.tablesaw.plotly.components.Line;

public class PlotlyTimeSeriesPlot extends PlotlyAbstractPlot {

    private final static Logger LOG = LogManager.getLogger();

    public PlotlyTimeSeriesPlot(ChartBuilder chartBuilder, String groupCol) {
        setChartBuilder(chartBuilder);
        String dateColX = columnsForViewColumns[0];
        if (columnsForViewColumns.length > 1) {
            LOG.warn("Only one view column is supported but {} received", columnsForViewColumns.length);
        }

        String yCol = columnsForViewRows[0];
        if (columnsForViewRows.length > 1) {
            //TODO: Support more columns in columnsForViewRows by creating more figures?
            LOG.warn("Only one view row is supported but {} received", columnsForViewRows.length);
        }

        // TODO : columnForDetails -
        // TODO : columnForSize -

        List<Table> tableList;

        if ( groupCol != null ) {
            TableSliceGroup tables = table.splitOn(table.categoricalColumn(groupCol));
            tableList = tables.asTableList();

            if (columnForColor != null) {
                if (!columnForColor.equals(groupCol)) {
                    LOG.warn("Cannot use a different color column when a group column is also present: color column ignored.");
                }
            }
        } else {
            if (columnForColor == null) {
                tableList = new ArrayList<Table>();
                tableList.add(table);
            } else {
                TableSliceGroup colorTables = table.splitOn(table.categoricalColumn(columnForColor));
                tableList = colorTables.asTableList();
            }
        }

        ScatterTrace[] traces = new ScatterTrace[tableList.size()];
        for (int i = 0; i < tableList.size(); i++) {
            Table t = tableList.get(i).sortOn(dateColX);
            ScatterTrace.ScatterBuilder builder =
                    ScatterTrace.builder(
                            t.column(dateColX), t.column(yCol))
                            .showLegend(true)
                            .name(tableList.get(i).name())
                            .mode(ScatterTrace.Mode.LINE);

            if (columnForColor == null) {
                if (traceColors != null && traceColors.length > i) {
                    builder.line(Line.builder()
                            .color(traceColors[i])
                            .build());
                }
            }

            if (columnsForLabels != null && columnsForLabels.length > 0) {
                if (columnsForLabels.length > 1) {
                    LOG.warn("Plot will only take into account the 1st label column ({} received)", columnsForLabels.length);
                }

                //assert ColumnType.STRING == tableList.get(i).column(columnsForLabels[0]).type();
                //TODO: other column type might make sense (like a date column) but then the text might be different...
                builder.text(tableList.get(i).stringColumn(columnsForLabels[0]).asObjectArray());
                builder.mode(ScatterTrace.Mode.LINE_AND_TEXT);
            }

            traces[i] = builder
                            .build();
        }

        setFigure( new Figure(layout, config, traces) );
    }

    public PlotlyTimeSeriesPlot(ChartBuilder chartBuilder) {
        this(chartBuilder, null);
    }

}
