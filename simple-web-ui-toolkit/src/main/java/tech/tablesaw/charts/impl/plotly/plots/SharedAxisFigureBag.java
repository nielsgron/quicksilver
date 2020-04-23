package tech.tablesaw.charts.impl.plotly.plots;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.traces.Trace;

class SharedAxisFigureBag extends AbstractFigureBag {

    List<Trace> allTraces = new ArrayList<>();

    SharedAxisFigureBag(Function<Trace[], Figure> creator) {
        super(creator);
    }

    @Override
    public void addTraces(Trace[] traces) {
        allTraces.addAll(Arrays.asList(traces));
    }

    @Override
    public Figure[] getFigures() {
        Figure f = createFigure(allTraces.toArray(new Trace[0]));
        return new Figure[]{f};
    }

}
