package tech.tablesaw.charts.impl.plotly;

import tech.tablesaw.charts.Chart;
import tech.tablesaw.plotly.components.Figure;

public class PlotlyChart extends Chart {

    protected Figure[] figures;

    public PlotlyChart() {

    }

    public PlotlyChart(Figure[] figures) {
        this.figures = figures;
    }

    public PlotlyChart(Figure figure) {
        this.figures = new Figure[] { figure };
    }

    public void setFigure(Figure fig) {
        this.figures = new Figure[] { fig };
    }

    public void setFigures(Figure[] fig) {
        this.figures = fig;
    }

    public Figure getFigure() {
        return figures[0];
    }

    public Figure[] getFigures() {
        return figures;
    }

    @Override
    public String divString(String divName) {
        return figures[0].divString(divName);
    }

    @Override
    public String asJavascript(String divName) {
        return figures[0].asJavascript(divName);
    }

    @Override
    public String divString(String divName, int figureIndex) {
        return figures[figureIndex].divString(divName);
    }

    @Override
    public String asJavascript(String divName, int figureIndex) {
        return figures[figureIndex].asJavascript(divName);
    }

}
