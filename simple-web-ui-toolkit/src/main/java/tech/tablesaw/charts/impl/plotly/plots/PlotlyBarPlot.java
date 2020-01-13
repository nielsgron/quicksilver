package tech.tablesaw.charts.impl.plotly.plots;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.tablesaw.api.Table;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.traces.BarTrace;
import tech.tablesaw.plotly.traces.Trace;

public abstract class PlotlyBarPlot extends PlotlyAbstractPlot {

    private final static Logger LOG = LogManager.getLogger();

    public PlotlyBarPlot(ChartBuilder chartBuilder) {
        setChartBuilder(chartBuilder);
        String groupColName = columnsForViewColumns[0];
        if (columnsForViewColumns.length > 1) {
            LOG.warn("Bar plot will only take into account the 1st view colum ({} received)", columnsForViewColumns.length);
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
                                            .orientation(getOrientation())
                                            .showLegend(true)
                                            .name(color)
                                            .build();
                            return trace;
                        })
                        .toArray(Trace[]::new);

                measureFigures.add(new Figure(layout, traces));
            }

            setFigures(measureFigures.toArray(new Figure[0]));
            return;
        }
        String[] numberColNames = columnsForViewRows;

        // TODO : columnForLabels -
        // TODO : columnForDetails -
        // TODO : columnForSize -

        // TODO : Support Clustered & Area display type. Research BarTrace.builder.mode(ScatterTrace.Mode.LINE) & BarTrace.builder.fill(ScatterTrace.Fill.TO_NEXT_Y) as in PlotlyAreaPlot

        Trace[] traces = new Trace[numberColNames.length];
        for (int i = 0; i < numberColNames.length; i++) {
            String name = numberColNames[i];
            BarTrace trace =
                    BarTrace.builder(table.categoricalColumn(groupColName), table.numberColumn(name))
                            .orientation(getOrientation())
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

    protected abstract BarTrace.Orientation getOrientation();
}
