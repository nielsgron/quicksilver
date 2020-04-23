package tech.tablesaw.charts.impl.plotly.plots;

import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.traces.Trace;

interface FigureBag {

    void addTraces(Trace[] traces);

    Figure[] getFigures();

    Figure createFigure(Trace[] traces);

}
