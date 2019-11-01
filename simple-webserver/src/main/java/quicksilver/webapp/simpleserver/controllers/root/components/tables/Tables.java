package quicksilver.webapp.simpleserver.controllers.root.components.tables;

import quicksilver.commons.app.SimpleWebServer;
import quicksilver.commons.data.ExcelExporter;
import quicksilver.commons.data.TSDataSet;
import quicksilver.commons.data.TSDataSetFactory;
import quicksilver.webapp.simpleserver.controllers.root.components.charts.ChartsTreemap;
import quicksilver.webapp.simpleui.bootstrap4.components.BSCard;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSTable;
import quicksilver.webapp.simpleui.bootstrap4.quick.QuickBodyPanel;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import spark.Request;
import spark.Response;
import tech.tablesaw.api.Table;

import java.io.*;

import static spark.Spark.halt;

public class Tables extends AbstractComponentsTablesPage {

    public Tables() {
    }

    protected BSPanel createContentPanelCenter() {

        QuickBodyPanel body = new QuickBodyPanel();

        // Build table for economic data
        TSDataSet economicDataSet = getEconomicDataSet();
        BSTable bsTable = new BSTable(economicDataSet, "Economic Data");

        bsTable.setTableCellRenderer( new BSTable.NumberTableCellRenderer(2, true, true, false), 1);
        bsTable.setTableCellRenderer( new BSTable.NumberTableCellRenderer(2, true, true, false), 2);
        bsTable.setTableCellRenderer( new BSTable.NumberTableCellRenderer(0, true, true, false), 3);
        bsTable.setTableCellRenderer( new BSTable.NumberTableCellRenderer(0, true, false, false), 4);

        body.addRowOfColumns( new BSCard( bsTable, "Country GDP & Population"));

        // Build table for stock equities
        TSDataSet stockEquitiesDataSet = getStockEquitiesDataSet();
        bsTable = new BSTable(stockEquitiesDataSet, "Stock Equities");

        bsTable.setTableCellRenderer( new BSTable.NumberTableCellRenderer(2, true, true, false), 5);
        bsTable.setTableCellRenderer( new BSTable.NumberTableCellRenderer(2, true, true, false), 6);
        bsTable.setTableCellRenderer( new BSTable.NumberTableCellRenderer(2, true, false, true), 7);

        body.addRowOfColumns( new BSCard( bsTable, "Stock Equities"));

        // Build table for stock prices
        TSDataSet stockPricesDataSet = getStockPricesDataSet();
        bsTable = new BSTable(stockPricesDataSet, "Stock Prices");

        bsTable.setTableCellRenderer( new BSTable.NumberTableCellRenderer(2, true, true, false), 2);
        bsTable.setTableCellRenderer( new BSTable.NumberTableCellRenderer(2, true, true, false), 3);
        bsTable.setTableCellRenderer( new BSTable.NumberTableCellRenderer(2, true, true, false), 4);
        bsTable.setTableCellRenderer( new BSTable.NumberTableCellRenderer(2, true, true, false), 5);

        body.addRowOfColumns( new BSCard( bsTable, "Stock Prices"));

        body.doLayout();

        BSPanel panel = new BSPanel();
        panel.add(new HTMLLineBreak(1));
        panel.add(body);
        panel.add(new HTMLLineBreak(2));

        return panel;
    }

    private static TSDataSet getEconomicDataSet() {
        return TSDataSetFactory.createSampleCountryEconomicData();
    }

    private static TSDataSet getStockEquitiesDataSet() {
        TSDataSet stockEquitiesDataSet = null;
        try {
            Table t = ChartsTreemap.loadLargeStocks();
            t.removeColumns("No");
            t = t.dropRange(7, t.rowCount() - 1);
            stockEquitiesDataSet = new TSDataSet(t);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stockEquitiesDataSet;
    }

    private static TSDataSet getStockPricesDataSet() {
        TSDataSet stockPricesDataSet = TSDataSetFactory.createSampleStockPrices();
        return stockPricesDataSet;
    }

    public static Object returnExportFile(Request request, Response response, boolean bCompressed) {

        String fileName = "tables-datasets.xlsx";

        TSDataSet economicDataSet = getEconomicDataSet();
        TSDataSet stockEquitiesDataSet = getStockEquitiesDataSet();
        TSDataSet stockPricesDataSet = getStockPricesDataSet();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            ExcelExporter exporter = new ExcelExporter();
            exporter.addDataSet(economicDataSet, "Sheet1");
            exporter.addDataSet(stockEquitiesDataSet, "Sheet2");
            exporter.addDataSet(stockPricesDataSet, "Sheet3");
            exporter.export(outputStream);
        } catch ( Throwable e ) {
            e.printStackTrace();
        }

        //String acceptType = "application/vnd.ms-excel";  // .xls
        //String acceptType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";  // .xlsx
        String acceptType = "application/octet-stream";  // binary

        try {
            SimpleWebServer.writeBinaryToResponse(outputStream.toByteArray(), acceptType, fileName, response, false);
        } catch (Exception e) {
            halt(405, "server error");
        }

        return response.raw();
    }

}
