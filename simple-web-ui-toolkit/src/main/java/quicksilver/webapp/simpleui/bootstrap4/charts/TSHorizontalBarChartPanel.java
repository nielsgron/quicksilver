package quicksilver.webapp.simpleui.bootstrap4.charts;

import quicksilver.webapp.simpleui.html.components.HTMLText;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.api.HorizontalBarPlot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.components.Margin;
import tech.tablesaw.plotly.traces.BarTrace;
import tech.tablesaw.plotly.traces.Trace;

public class TSHorizontalBarChartPanel extends TSFigurePanel {

    public TSHorizontalBarChartPanel(Table table, String divName) {
        super(divName);
    }

    public TSHorizontalBarChartPanel(Table table, String divName, int height, int width, boolean enableLegend) {
        super(divName);

        setHeight(height);
        setWidth(width);
        enableLegend(enableLegend);

        Figure figure = null;

        try {
            figure = createFigure( table,"Name", "Value", "");
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        HTMLText divS = new HTMLText(figure.divString(divName));
        HTMLText divJS = new HTMLText(figure.asJavascript(divName));

        this.add(divS);
        this.add(divJS);

    }

    public Figure createFigure(Table table, String groupColName, String numberColName, String title) {

        Layout layout = Layout.builder()
                .title(title)
                .height(height)
                .width(width)
                .showLegend(enabledLegend)
                .margin(Margin.builder().left(40).right(5).bottom(30).top(5).build())
                .build();
        BarTrace trace = BarTrace.builder(table.categoricalColumn(groupColName), table.numberColumn(numberColName)).orientation(BarTrace.Orientation.HORIZONTAL).build();
        return new Figure(layout, new Trace[]{trace});

    }

}
