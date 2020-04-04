package tech.tablesaw.charts.impl.plotly.plots;

import tech.tablesaw.api.Table;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Marker;
import tech.tablesaw.plotly.traces.ScatterTrace;
import tech.tablesaw.table.TableSliceGroup;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlotlyScatterPlot extends PlotlyAbstractPlot {

    private final static Logger LOG = LogManager.getLogger();

    public PlotlyScatterPlot(ChartBuilder chartBuilder, String groupCol) {
        setChartBuilder(chartBuilder);
        String xCol = columnsForViewColumns[0];
        if (columnsForViewColumns.length > 1) {
            LOG.warn("Only one view column is supported but {} received", columnsForViewColumns.length);
        }

        String yCol = columnsForViewRows[0];
        if (columnsForViewRows.length > 1) {
            //TODO: Support more columns in columnsForViewRows by creating more figures?
            LOG.warn("Only one view row is supported but {} received", columnsForViewRows.length);
        }

        // TODO : columnForDetails -
        // TODO : columnForColor -

        List<Table> tableList;

        if ( groupCol != null ) {
            TableSliceGroup tables = table.splitOn(table.categoricalColumn(groupCol));
            tableList = tables.asTableList();
        } else {
            tableList = new ArrayList<Table>();
            tableList.add(table);
        }

        ScatterTrace[] traces = new ScatterTrace[tableList.size()];
        Marker marker = Marker.builder().opacity(0.75D).build();
        for (int i = 0; i < tableList.size(); i++) {
            Table t = tableList.get(i);
            ScatterTrace.ScatterBuilder builder =
                    ScatterTrace.builder(
                            t.column(xCol), t.column(yCol))
                            .showLegend(true)
                            .marker(marker)
                            .name(tableList.get(i).name());

            if (columnsForLabels != null && columnsForLabels.length > 0) {
                if (columnsForLabels.length > 1) {
                    LOG.warn("Plot will only take into account the 1st label column ({} received)", columnsForLabels.length);
                }

                builder.text(tableList.get(i).stringColumn(columnsForLabels[0]).asObjectArray());
                builder.mode(ScatterTrace.Mode.TEXT_AND_MARKERS);
            }

            traces[i] = builder
                            .build();
        }

        setFigure( new Figure(layout, config, traces) );
    }

    public PlotlyScatterPlot(ChartBuilder chartBuilder) {
        this(chartBuilder, null);
    }

}
