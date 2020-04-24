package quicksilver.webapp.simpleserver.controllers.root.components.explorer;

import java.util.HashMap;
import java.util.Map;
import okhttp3.HttpUrl;
import tech.tablesaw.api.Table;
import tech.tablesaw.charts.Chart;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.charts.impl.plotly.PlotlyChartBuilder;
import tech.tablesaw.plotly.components.Layout;

/**
 * A dummy builder that just defers anything substantial to the Plotly builder
 * but adds an *Edit* button after the chart which opens the 'Data Explorer'
 * page with fields pre-populated for the current chart. It does this by
 * recording what was set for the chart and building the explorer URL.
 */
public class ExplorerChartBuilder extends ChartBuilder {

    PlotlyChartBuilder builder = new PlotlyChartBuilder();
    Map<String, String> query = new HashMap<>();

    @Override
    public ChartBuilder dataTable(Table dataTable) {
        builder.dataTable(dataTable);
        return this;
    }

    @Override
    public ChartBuilder chartType(CHART_TYPE chartType, Object... chartTypeOptions) {
        builder.chartType(chartType, chartTypeOptions);
        query.put("chartType", chartType.name());
        //TODO: save options
        return this;
    }

    @Override
    public ChartBuilder columnsForViewColumns(String... columns) {
        builder.columnsForViewColumns(columns);
        query.put("columns", String.join(" ", columns));
        return this;
    }

    @Override
    public ChartBuilder columnsForViewRows(String... rows) {
        builder.columnsForViewRows(rows);
        query.put("rows", String.join(" ", rows));
        return this;
    }

    @Override
    public ChartBuilder columnForColor(String column) {
        builder.columnForColor(column);
        query.put("color", column);
        return this;
    }

    @Override
    public ChartBuilder axisTitles(String xTitle, String yTitle) {
        builder.axisTitles(xTitle, yTitle);
        //TODO: this is nowhere in the query...
        return this;
    }

    @Override
    public ChartBuilder layout(int width, int height, int marginTop, int marginBottom, int marginLeft, int marginRight, boolean enabledLegend) {
        builder.layout(width, height, marginTop, marginBottom, marginLeft, marginRight, enabledLegend);
        return this;
    }

    @Override
    public ChartBuilder layout(int marginTop, int marginBottom, int marginLeft, int marginRight, boolean enabledLegend) {
        builder.layout(marginTop, marginBottom, marginLeft, marginRight, enabledLegend);
        return this;
    }

    @Override
    public ChartBuilder layout(int width, int height, boolean enabledLegend) {
        builder.layout(width, height, enabledLegend);
        //TODO: this is nowhere in the query...
        return this;
    }

    @Override
    public ChartBuilder layout(boolean enabledLegend) {
        builder.layout(enabledLegend);
        //TODO: this is nowhere in the query...
        return this;
    }

    @Override
    public Layout.LayoutBuilder getLayoutBuilder() {
        //TODO: this is nowhere in the query...
        return builder.getLayoutBuilder();
    }

    @Override
    public ChartBuilder divName(String divName) {
        builder.divName(divName);

        return this;
    }

    private String createEditLink() {
        HttpUrl.Builder b = new HttpUrl.Builder();
        //TODO: figure out the host:port of the currently running application, eg. via WebServerSimpleDemo...
        b.scheme("http").host("localhost").port(4567);
        b.addPathSegment("components").addPathSegment("explorer");
        query.forEach((k, v) -> b.addQueryParameter(k, v));
        return b.build().url().toExternalForm();
    }

    @Override
    public Chart build() {
        Chart chart = builder.build();

        return new Chart() {
            @Override
            public String divString(String divName) {
                return chart.divString(divName) + "<p class='text-right'><a href='" + createEditLink() + "'><small>Edit</small></a></a>";
            }

            @Override
            public String asJavascript(String divName) {
                return chart.asJavascript(divName);
            }

            @Override
            public String divString(String divName, int figureIndex) {
                return chart.divString(divName, figureIndex) + "<p class='text-right'><a href='" + createEditLink() + "'><small>Edit</small></a></a>";
            }

            @Override
            public String asJavascript(String divName, int figureIndex) {
                return chart.asJavascript(divName, figureIndex);
            }

            @Override
            public int figureCount() {
                return chart.figureCount();
            }

        };
    }

}
