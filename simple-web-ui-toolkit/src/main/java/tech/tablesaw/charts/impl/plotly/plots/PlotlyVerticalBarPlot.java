package tech.tablesaw.charts.impl.plotly.plots;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import tech.tablesaw.api.Table;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.traces.BarTrace;
import tech.tablesaw.plotly.traces.Trace;

public class PlotlyVerticalBarPlot extends PlotlyAbstractPlot {

    private static final Logger LOG = Logger.getLogger(PlotlyVerticalBarPlot.class.getName());

    BarTrace.Orientation orientation = BarTrace.Orientation.VERTICAL;

    public PlotlyVerticalBarPlot(ChartBuilder chartBuilder) {
        setChartBuilder(chartBuilder);
        String groupColName = columnsForViewColumns[0];
        if (columnsForViewColumns.length > 1) {
            LOG.log(Level.WARNING, "Vertical bar plot will only take into account the 1st view colum ({0} received)", columnsForViewColumns.length);
        }

        if (columnForColor != null && !groupColName.equals(columnForColor)) {
            //add column values
            List<String> colorDimension = table.stringColumn(columnForColor).unique().asList();
            List<Figure> measureFigures = new ArrayList<>();

            //create a figure for each measure
            for (String measure : columnsForViewRows) {

                Trace[] traces = colorDimension.stream()
                        .map(color -> {
                            Table colorTable = table.where(table.stringColumn(columnForColor).isEqualTo(color));
                            BarTrace trace
                                    = BarTrace.builder(colorTable.categoricalColumn(groupColName), colorTable.numberColumn(measure))
                                            .orientation(orientation)
                                            .showLegend(true)
                                            .name(color)
                                            .build();
                            return trace;
                        })
                        .toArray(Trace[]::new);

                measureFigures.add(new Figure(layout, traces));
            }

            setFigures(measureFigures.toArray(Figure[]::new));
            return;
        }
        String[] numberColNames = columnsForViewRows;

        // TODO : columnForLabels -
        // TODO : columnForDetails -
        // TODO : columnForSize -

        Trace[] traces = new Trace[numberColNames.length];
        for (int i = 0; i < numberColNames.length; i++) {
            String name = numberColNames[i];
            BarTrace trace =
                    BarTrace.builder(table.categoricalColumn(groupColName), table.numberColumn(name))
                            .orientation(orientation)
                            .showLegend(true)
                            .name(name)
                            .build();
            traces[i] = trace;
        }

        if (columnForColor == null) {
            //create one figure for each viewRows column
            setFigures(Stream.of(traces)
                    .map(t -> new Figure(layout, new Trace[]{t}))
                    .toArray(Figure[]::new));
        } else {
            assert groupColName.equals(columnForColor);

            setFigure(new Figure(layout, traces));
        }

    }

}
