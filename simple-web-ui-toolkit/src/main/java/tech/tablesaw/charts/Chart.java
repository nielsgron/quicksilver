package tech.tablesaw.charts;

public abstract class Chart {

    public abstract String divString(String divName);

    public abstract String asJavascript(String divName);

    public abstract String divString(String divName, int figureIndex);

    public abstract String asJavascript(String divName, int figureIndex);

    public int figureCount() {
        return 1;
    }

}
