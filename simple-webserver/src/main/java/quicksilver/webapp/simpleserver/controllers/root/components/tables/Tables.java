package quicksilver.webapp.simpleserver.controllers.root.components.tables;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import quicksilver.commons.app.SimpleWebServer;
import quicksilver.commons.data.ExcelExporter;
import quicksilver.commons.data.TSDataSet;
import quicksilver.commons.data.TSDataSetFactory;
import quicksilver.webapp.simpleui.bootstrap4.components.BSCard;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSTable;
import quicksilver.webapp.simpleui.bootstrap4.quick.QuickBodyPanel;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import spark.Request;
import spark.Response;
import tech.tablesaw.api.Table;

import java.io.*;
import java.time.LocalDate;

import static spark.Spark.halt;

public class Tables extends AbstractComponentsTablesPage {

    private static Logger LOG = LogManager.getLogger();

    public Tables() {
    }

    protected BSPanel createContentPanelCenter() {

        QuickBodyPanel body = new QuickBodyPanel();

        // Build table for economic data
        TSDataSet economicDataSet = getEconomicDataSet();

        BSTable bsTable = new BSTable(economicDataSet, "Country GDP & Population");

        bsTable.setTableCellRenderer( new BSTable.NumberTableCellRenderer(0, true, true, false), 1);
        bsTable.setTableCellRenderer( new BSTable.NumberTableCellRenderer(0, true, true, false), 2);
        bsTable.setTableCellRenderer( new BSTable.NumberTableCellRenderer(0, true, false, false), 3);

        body.addRowOfColumns( new BSCard( bsTable, "Country GDP & Population"));

        // Build table for stock equities
        TSDataSet companyStockDataSet = getStockEquitiesDataSet();

        bsTable = new BSTable(companyStockDataSet, "Company Stock");

        bsTable.setTableCellRenderer( new BSTable.NumberTableCellRenderer(2, true, true, false), 5);
        bsTable.setTableCellRenderer( new BSTable.NumberTableCellRenderer(2, true, true, false), 6);
        bsTable.setTableCellRenderer( new BSTable.NumberTableCellRenderer(2, true, false, true), 7);

        body.addRowOfColumns( new BSCard( bsTable, "Company Stock"));

        // Build table for stock prices
        TSDataSet stockPricesDataSet = getStockPricesDataSet();

        bsTable = new BSTable(stockPricesDataSet, "MSFT - Stock Prices");

        bsTable.setTableCellRenderer( new BSTable.NumberTableCellRenderer(2, true, true, false), 1);
        bsTable.setTableCellRenderer( new BSTable.NumberTableCellRenderer(2, true, true, false), 2);
        bsTable.setTableCellRenderer( new BSTable.NumberTableCellRenderer(2, true, true, false), 3);
        bsTable.setTableCellRenderer( new BSTable.NumberTableCellRenderer(2, true, true, false), 4);
        bsTable.setTableCellRenderer( new BSTable.NumberTableCellRenderer(2, true, true, false), 5);
        bsTable.setTableCellRenderer( new BSTable.NumberTableCellRenderer(0, true, false, false), 6);

        body.addRowOfColumns( new BSCard( bsTable, "MSFT - Stock Prices"));

        body.doLayout();

        BSPanel panel = new BSPanel();
        panel.add(new HTMLLineBreak(1));
        panel.add(body);
        panel.add(new HTMLLineBreak(2));

        return panel;
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

    private static TSDataSet getEconomicDataSet() {

        TSDataSet economicDataSet;

        try {
            Table t = TableData.loadCountryGDPPopulation();
            t = t.dropRange(10, t.rowCount());
            economicDataSet = new TSDataSet(t);
        } catch ( Exception e ) {
            LOG.error("Failed to load Country-GDP-Population csv", e);
            economicDataSet = getEconomicDataSet();
        }

        return economicDataSet;
    }

    private static TSDataSet getStockEquitiesDataSet() {

        TSDataSet companyStockDataSet;

        try {
            Table t = TableData.loadCompanyStocks();
            t = t.dropRange(10, t.rowCount());
            companyStockDataSet = new TSDataSet(t);
        } catch ( Exception e ) {
            LOG.error("Failed to load Company-Stock csv", e);
            companyStockDataSet = getStockEquitiesDataSet();
        }

        return companyStockDataSet;
    }

    private static TSDataSet getStockPricesDataSet() {

        TSDataSet stockPricesDataSet;

        try {
            Table t = TableData.loadStockPrices(LocalDate.of(2019, 9, 10), LocalDate.of(2019, 9, 24));
            stockPricesDataSet = new TSDataSet(t);
        } catch ( Exception e ) {
            LOG.error("Failed to load Stock-Prices csv", e);
            stockPricesDataSet = TSDataSetFactory.createSampleStockPrices();
        }

        return stockPricesDataSet;
    }

}
