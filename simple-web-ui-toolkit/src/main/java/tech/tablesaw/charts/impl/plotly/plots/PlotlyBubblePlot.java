package tech.tablesaw.charts.impl.plotly.plots;

import tech.tablesaw.api.Table;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Marker;
import tech.tablesaw.plotly.traces.ScatterTrace;
import tech.tablesaw.table.TableSliceGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import quicksilver.commons.data.TSDataSetFactory;
import tech.tablesaw.api.ColumnType;
import tech.tablesaw.api.StringColumn;

public class PlotlyBubblePlot extends PlotlyAbstractPlot {

    private final static Logger LOG = LogManager.getLogger();

    public PlotlyBubblePlot(ChartBuilder chartBuilder, String groupCol) {
        setChartBuilder(chartBuilder);
        String xCol = columnsForViewColumns[0];
        String yCol = columnsForViewRows[0];
        String sizeColumn = columnForSize;

        if (columnsForLabels != null && columnsForLabels.length > 1) {
            LOG.warn("Only one labels column is supported but {} received", columnsForLabels.length);
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
        for (int i = 0; i < tableList.size(); i++) {

            String[] colors = getColors(tableList.get(i), columnForColor);

            Marker.MarkerBuilder markerBuilder =
                    Marker.builder()
                            .size(tableList.get(i).numberColumn(sizeColumn))
                            // .opacity(.75)
                    ;
            if (colors != null) {
                markerBuilder.color(colors);
            } else if (traceColors != null && traceColors.length > i) {
                markerBuilder.color(traceColors[i]);
            }

            Marker marker = markerBuilder
                            .build();

            ScatterTrace.ScatterBuilder builder =
                    ScatterTrace.builder(
                            tableList.get(i).numberColumn(xCol), tableList.get(i).numberColumn(yCol))
                            .showLegend(true)
                            .marker(marker)
                            .name(tableList.get(i).name());
            if (columnsForLabels != null && columnsForLabels.length > 0) {
                builder = builder
                        .mode(ScatterTrace.Mode.TEXT_AND_MARKERS)
                        .text(tableList.get(i).stringColumn(columnsForLabels[0]).asObjectArray());
            }
            traces[i] = builder
                            .build();
        }

        setFigure( new Figure(layout, config, traces) );
    }

    private static String[] getColors(Table t, String columnForColor) {
        if (columnForColor != null) {

            if (t.column(columnForColor).type() != ColumnType.STRING) {
                LOG.warn("Numeric color columns not supported yet");
            } else {
                StringColumn colorCol = t.stringColumn(columnForColor);

                StringColumn uniqueColors = colorCol.unique();

                //TODO: Here a random sample of colors is picked. Smarter algorithms could pick a better palette based on proximity, etc.
                Table cssColors = TSDataSetFactory.createCSSColorsTable().getTSTable().sampleN(uniqueColors.size());
                StringColumn cssColorName = cssColors.stringColumn("Color");

                //create a mapping between column values and a color
                Map<String, String> colorMapping = new HashMap<>();
                for (int k = 0; k < uniqueColors.size(); k++) {
                    colorMapping.put(uniqueColors.get(k), cssColorName.get(k));
                }

                StringColumn mappedColors = colorCol.map(colorMapping::get);

                return mappedColors.asObjectArray();
            }
        }
        return null;
    }

    public PlotlyBubblePlot(ChartBuilder chartBuilder) {
        this(chartBuilder, null);
    }

}
