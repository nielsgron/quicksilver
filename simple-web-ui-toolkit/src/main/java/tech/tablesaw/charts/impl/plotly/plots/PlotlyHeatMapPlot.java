package tech.tablesaw.charts.impl.plotly.plots;

import tech.tablesaw.api.Table;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.columns.Column;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.traces.HeatmapTrace;
import tech.tablesaw.plotly.traces.Trace;
import tech.tablesaw.util.DoubleArrays;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.tablesaw.plotly.api.TableExtract;

public class PlotlyHeatMapPlot extends PlotlyAbstractPlot {

    private final static Logger LOG = LogManager.getLogger();

    public PlotlyHeatMapPlot(ChartBuilder chartBuilder) {
        setChartBuilder(chartBuilder);

        if (columnsForViewRows != null && columnsForViewRows.length > 0) {
            LOG.warn("No view row supported but {} received", columnsForViewRows.length);
        }

        String categoryCol1 = columnsForViewColumns[0];
        String categoryCol2 = columnsForViewColumns[1];
        if (columnsForViewColumns.length > 2) {
            LOG.warn("Only 2 view columns supported but {} received", columnsForViewColumns.length);
        }

        // TODO : columnForDetails -
        // TODO : columnForColor -

        Table counts = table.xTabCounts(categoryCol1, categoryCol2);
        counts = counts.dropRows(counts.rowCount() - 1);
        List<Column<?>> columns = counts.columns();
        columns.remove(counts.columnCount() - 1);
        Column<?> yColumn = columns.remove(0);
        double[][] z = DoubleArrays.to2dArray(counts.numericColumns());

        Object[] x = counts.columnNames().toArray();
        Object[] y = yColumn.asObjectArray();
        HeatmapTrace trace = HeatmapTrace.builder(x, y, z).build();

        //Semi-related note: if the `y` text is too wide manually changing layout.margin.l
        // aka .margin(Margin.builder().left(200).build()) will do the trick.
        Figure.FigureBuilder builder = Figure.builder()
                .layout(layout)
                .config(config)
                .addTraces(new Trace[]{trace});

        if (columnsForLabels != null && columnsForLabels.length > 0) {
            if (columnsForLabels.length > 1) {
                LOG.warn("Plot will only take into account the 1st label column ({} received)", columnsForLabels.length);
            }

            //we find a first labels value for the given categoryCol1.
            Table summ = table.select(categoryCol1, columnsForLabels[0])
                    .splitOn(categoryCol1)
                    .aggregate(columnsForLabels[0], TableExtract.firstString)
                    //sorting them so they are the same order as as `y`
                    .sortAscendingOn(categoryCol1);
            String[] labels = summ
                    .stringColumn(1)
                    .asObjectArray();

            //we display that value for non-zero
            builder.addEventHandlers((String targetName, String divName) -> {
                return "var textValues = " + tech.tablesaw.plotly.Utils.dataAsString(labels) + ";\n"
                        //+ targetName + ".layout.annotations = [];\n"
                        + "var annotations2 = [];\n"
                        + "for ( var i = 0; i < data[0].y.length; i++ ) {\n"
                        + "  for ( var j = 0; j < data[0].x.length; j++ ) {\n"
                        + "    var currentValue = data[0].z[i][j];\n"
                        + "    if (currentValue != 0.0) {\n"
                        + "      var textColor = 'white';\n"
                        //                        + "    }else{\n"
                        //                        + "      var textColor = 'black';\n"
                        //                        + "    }\n"
                        + "      var result = {\n"
                        + "        xref: 'x1',\n"
                        + "        yref: 'y1',\n"
                        + "        x: data[0].x[j],\n"
                        + "        y: data[0].y[i],\n"
                        + "        text: textValues[i],\n"
                        + "        font: {\n"
                        + "          family: 'Arial',\n"
                        + "          size: 12,\n"
                        + "          color: textColor\n"
                        + "        },\n"
                        + "        showarrow: false,\n"
                        + "      };\n"
                        //                        + "    "+targetName+".layout.annotations.push(result);\n"
                        + "      annotations2.push(result);\n"
                        + "    }\n"
                        + "  }\n"
                        + "}\n"
                        + "layout.annotations = annotations2; //this is part 1 of the refresh trick\n"
                        + "Plotly.relayout(" + targetName + ", {'layout.annotations': annotations2}); //part 2 of refreshing";

            });
        }

        setFigure(builder.build());
    }

}
