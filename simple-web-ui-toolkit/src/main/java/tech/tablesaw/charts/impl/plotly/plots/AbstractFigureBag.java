package tech.tablesaw.charts.impl.plotly.plots;

import java.util.function.Function;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.traces.Trace;

abstract class AbstractFigureBag implements FigureBag {

    protected final Function<Trace[], Figure> creator;

    protected AbstractFigureBag(Function<Trace[], Figure> creator) {
        this.creator = creator;
    }

    @Override
    public Figure createFigure(Trace[] traces) {
        return creator.apply(traces);
    }

}
