package quicksilver.webapp.simpleserver.controllers.root.components.charts;

import quicksilver.commons.data.TSDataSetFactory;
import quicksilver.webapp.simpleserver.controllers.root.components.tables.TableData;
import quicksilver.webapp.simpleui.bootstrap4.charts.TSFigurePanel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSCard;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.quick.QuickBodyPanel;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import tech.tablesaw.api.Table;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.charts.impl.calheatmap.CalHeatmapLayout;

import java.time.LocalDate;

public class ChartsCalHeatmap extends AbstractComponentsChartsPage {

    public ChartsCalHeatmap() {
        super();
        toolbar.setActiveButton("Cal-Heatmap");
    }

    protected BSPanel createContentPanelCenter() {

        QuickBodyPanel body = new QuickBodyPanel();

        String divName = "calheatmapDiv";

        boolean autoSize = true;

        Table stockPricesTable = null;

        try {
            stockPricesTable = TableData.loadStockPrices(LocalDate.of(2019, 9, 10), LocalDate.of(2019, 9, 24));
        } catch ( Exception e ) {
            stockPricesTable = TSDataSetFactory.createSampleStockPrices().getTSTable();
        }


        // Add Chart
        Table areaTable = stockPricesTable;

        ChartBuilder chartBuilder = ChartBuilder.createBuilder(ChartBuilder.CHART_RENDERER.CALHEATMAP)
                .dataTable(areaTable)
                .chartType(ChartBuilder.CHART_TYPE.HEATMAP_CALENDAR)
                .layout(500, 200, false)
                .columnsForViewColumns("Date")
                .columnsForViewRows("Volume")
                ;

        CalHeatmapLayout.LayoutBuilder layoutBuilder = (CalHeatmapLayout.LayoutBuilder)chartBuilder.getLayoutBuilder();

        if ( !autoSize ) {
            chartBuilder.layout(1000, 200, false);
        } else {
            chartBuilder.layout(false);
            chartBuilder.getLayoutBuilder()
                    .autosize(true)
                    .height(250);
        }

        layoutBuilder.domain("month")
                .subDomain("x_day")
                .cellSize(21)
                .domainGutter(14)
                .range(6)
                .highlight(LocalDate.of(2019,10, 15))
                .subDomainTextFormat("%d");

        body.addRowOfColumns(
                new BSCard(new TSFigurePanel(chartBuilder.divName(divName + "1").build(), divName + "1"),
                        "Wide Chart")
        );

        if ( !autoSize ) {
            chartBuilder.layout(450, 200, false);
        }

        layoutBuilder.domain("month")
                .subDomain("x_day")
                .cellSize(20)
                .domainGutter(12)
                .range(3)
                .highlight(null)
                .subDomainTextFormat("%d");

        body.addRowOfColumns(
                new BSCard(new TSFigurePanel(chartBuilder.divName(divName + "2").build(), divName + "2"),
                        "Half Width Chart"),
                new BSCard(new TSFigurePanel(chartBuilder.divName(divName + "3").build(), divName + "3"),
                        "Half Width Chart")
        );

        if ( !autoSize ) {
            chartBuilder.layout(300, 200, false);
        }

        layoutBuilder.domain("month")
                .subDomain("x_day")
                .cellSize(19)
                .domainGutter(8)
                .range(2)
                .highlight(null)
                .subDomainTextFormat("%d");

        body.addRowOfColumns(
                new BSCard(new TSFigurePanel(chartBuilder.divName(divName + "4").build(), divName + "4"),
                        "Narrow Chart"),
                new BSCard(new TSFigurePanel(chartBuilder.divName(divName + "5").build(), divName + "5"),
                        "Narrow Chart"),
                new BSCard(new TSFigurePanel(chartBuilder.divName(divName + "6").build(), divName + "6"),
                        "Narrow Chart")
        );

        body.doLayout();

        BSPanel panel = new BSPanel();
        panel.add(new HTMLLineBreak(1));
        panel.add(body);
        panel.add(new HTMLLineBreak(2));

        return panel;

    }

}