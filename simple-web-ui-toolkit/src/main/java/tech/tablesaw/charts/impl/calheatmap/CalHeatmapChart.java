package tech.tablesaw.charts.impl.calheatmap;

import tech.tablesaw.api.*;
import tech.tablesaw.charts.Chart;
import tech.tablesaw.plotly.components.Layout;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class CalHeatmapChart extends Chart {

    private CalHeatmapLayout layout;
    private Table table;
    private String timestampCol;
    private String valueCol;

    public CalHeatmapChart(CalHeatmapLayout layout, Table table, String timestampCol, String valueCol) {
        this.layout = layout;
        this.table = table;
        this.timestampCol = timestampCol;
        this.valueCol = valueCol;
    }

    /*
    <div id="cal-heatmap"></div>
     */

    @Override
    public String divString(String divName) {
        return String.format("<div id='%s' ></div>" + System.lineSeparator(), divName);
    }

    /*
    <script type="text/javascript">
	    var cal = new CalHeatMap();
	    cal.init({});
    </script>
     */

    @Override
    public String asJavascript(String divName) {

        StringBuilder js = new StringBuilder();

        js.append("<script type=\"text/javascript\">\n");

        JavascriptForVarData(js);
        JavascriptForParser(js);
        JavascriptForObjectInit(js, divName);

        js.append("</script>\n");

        return js.toString();
    }

    @Override
    public String divString(String divName, int figureIndex) {
        return divString(divName);
    }

    @Override
    public String asJavascript(String divName, int figureIndex) {
        return asJavascript(divName);
    }

    private void JavascriptForVarData(StringBuilder js) {

        //DateTimeColumn dtColumn = (DateTimeColumn)table.column(timestampCol);
        DateColumn dtColumn = (DateColumn)table.column(timestampCol);
        NumberColumn valueColumn = (NumberColumn)table.column(valueCol);

        js.append("var datas = [\n");

        for ( int i = 0; i < table.rowCount(); i++ ) {

            //LocalDateTime dt = dtColumn.get(i);
            LocalDate dt = dtColumn.get(i);
            String val = valueColumn.getString(i);

            //long timeInSeconds = dt.toEpochSecond(ZoneOffset.UTC);
            long timeInSeconds = dt.toEpochDay() * 60 * 60 * 24;

            js.append("{date: ");
            js.append(timeInSeconds);
            js.append(", value: ");
            js.append(val);
            js.append("}");

            if ( i < (table.rowCount()-1) ) {
                js.append(",");
            }

            js.append("\n");

        }

//        js.append("{date: 1491177600, value: 52},\n");
//        js.append("{date: 1491264000, value: 53}\n");

        js.append("]\n");

    }

    private void JavascriptForParser(StringBuilder js) {

        js.append("var parser = function(data) {\n");
        js.append("var stats = {};\n");
        js.append("for (var d in data) {\n");
        js.append("stats[data[d].date] = data[d].value;\n");
        js.append("}\n");
        js.append("return stats;\n");
        js.append("};\n");

    }

    private void JavascriptForObjectInit(StringBuilder js, String divName) {

        js.append("var cal = new CalHeatMap();\n");

        js.append("cal.init({\n");

        js.append("data: datas,\n");
        js.append("afterLoadData: parser,\n");
        //js.append("start: new Date(2017, 3),\n");
        js.append("start: new Date(2019, 8),\n");
        js.append("itemSelector: \"#" + divName+ "\",\n");
        //js.append("itemName: [\"ppb\", \"ppb\"],\n");

        js.append("domain: \"" + layout.getDomain() + "\",\n");
        js.append("subDomain: \"" + layout.getSubDomain() + "\",\n");
        js.append("cellSize: " + layout.getCellSize() + ",\n");
        js.append("domainGutter: " + layout.getDomainGutter() + ",\n");
        js.append("subDomainTextFormat: \"" + layout.getSubDomainTextFormat() + "\",\n");
        js.append("range: " + layout.getRange() + ",\n");
        //js.append("highlight: new Date(2017, 3, 6),\n");

        if ( layout.getHighlight() != null ) {
            js.append("highlight: new Date(");
            js.append(layout.getHighlight().getYear() + ", ");
            js.append(layout.getHighlight().getMonthValue() + ", ");
            js.append(layout.getHighlight().getDayOfMonth());
            js.append("),\n");
        }

        if ( layout.getShowLegend() ) {
            js.append("displayLegend: true\n");
        } else {
            js.append("displayLegend: false\n");
        }

        js.append("});\n");

    }

}
