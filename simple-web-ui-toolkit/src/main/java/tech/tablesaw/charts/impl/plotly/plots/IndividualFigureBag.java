package tech.tablesaw.charts.impl.plotly.plots;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.traces.Trace;

class IndividualFigureBag extends AbstractFigureBag {

    List<Figure> figures = new ArrayList<>();

    IndividualFigureBag(Function<Trace[], Figure> creator) {
        super(creator);
    }

    @Override
    public void addTraces(Trace[] traces) {
        figures.add(createFigure(traces));
    }

    @Override
    public Figure[] getFigures() {
        return figures.toArray(new Figure[0]);
    }

}
