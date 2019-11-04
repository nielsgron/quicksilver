package quicksilver.webapp.simpleserver.controllers.root.components.tables;

import tech.tablesaw.api.DateColumn;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.columns.numbers.DoubleColumnType;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

public class TableData {

    public static Table loadCountryGDPPopulation() throws IOException {
        Table dataTable;

        InputStream inputStream = TableData.class.getResourceAsStream("country-gdp-population.csv");
        dataTable = Table.read().csv(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

        // Remove Columns
        dataTable.removeColumns("Rank");

        // Rename Columns
        dataTable.column("country").setName("Country");
        dataTable.column("imfGDP").setName("GDP");
        dataTable.column("gdpPerCapita").setName("GDP_Capita");
        dataTable.column("population").setName("Population");

        // Sort Rows
        dataTable = dataTable.sortDescendingOn("GDP");

        // Transform row values in columns
        DoubleColumn oldColumn = (DoubleColumn) dataTable.column("Population");
        DoubleColumn newColumn = DoubleColumn.create("Population");
        newColumn = newColumn.emptyCopy(oldColumn.size());

        oldColumn.mapInto((Double p) -> { return p * 1000; }, newColumn);
        dataTable.replaceColumn(newColumn);

        // Filter Row by Column Criteria

        //DoubleColumn marketCapColumn = (DoubleColumn)dataTable.column("infGDP");
        //dataTable = dataTable.where(marketCapColumn.isGreaterThan(75000)); // Greater then 75 Billion market cap (113 rows)

        //dataTable = dataTable.sampleN(100);
        //System.out.println(treemapTable.structure());
        //System.out.println("Country-GDP-Population Table Row Count: " + dataTable.rowCount());

        return dataTable;
    }

    public static Table loadCompanyStocks() throws IOException {
        Table dataTable;

        InputStream inputStream = TableData.class.getResourceAsStream("company-stocks.csv");
        dataTable = Table.read().csv(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

        // Remove Columns
        dataTable.removeColumns("No");
        dataTable.removeColumns("Volume");

        // Rename Columns

        // Sort Rows

        // Transform row values in columns

        DoubleColumn marketCapColumn = (DoubleColumn)dataTable.column("MarketCap");
        dataTable = dataTable.where(marketCapColumn.isGreaterThan(75000)); // Greater then 75 Billion market cap (113 rows)

        //XXX: See https://github.com/jtablesaw/tablesaw/pull/703 ,until then convert % by hand to numbers
        StringColumn change = (StringColumn) dataTable.column("Change");
        DoubleColumn numericChange = DoubleColumn.create("Change");
        //apparently mapInto doesn't append, but replace. Need to grow it to size()
        numericChange = numericChange.emptyCopy(change.size());

        change.mapInto((String p) -> {
            double percFactor = 1.0;
            if (p.endsWith("%")) {
                percFactor = 100.0;
                p = p.substring(0, p.length() - 1);
            }
            try {
                return Double.parseDouble(p) / percFactor;
            } catch (NumberFormatException nfe) {
                return DoubleColumnType.missingValueIndicator();
            }

        }, numericChange);

        dataTable.replaceColumn(numericChange);

        // Filter Row by Column Criteria

        //dataTable = dataTable.sampleN(100);
        //System.out.println(dataTable.structure());
        //System.out.println("Company-Stocks Table Row Count: " + dataTable.rowCount());


        return dataTable;
    }

    public static Table loadStockPrices() throws IOException {
        return loadStockPrices(null, null);
    }

    public static Table loadStockPrices(LocalDate from, LocalDate to) throws IOException {
        Table dataTable;

        InputStream inputStream = TableData.class.getResourceAsStream("stock-prices-MSFT.csv");
        dataTable = Table.read().csv(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

        // Remove Columns

        // Rename Columns

        // Sort Rows
        dataTable = dataTable.sortAscendingOn("Date");

        // Transform row values in columns

        // Filter Row by Column Criteria
        DateColumn dateColumn;
        if ( from != null ) {
            dateColumn = (DateColumn)dataTable.column("Date");
            dataTable = dataTable.where(dateColumn.isAfter(from));
        }
        if ( to != null ) {
            dateColumn = (DateColumn)dataTable.column("Date");
            dataTable = dataTable.where(dateColumn.isBefore(to));
        }

        // Filter Rows by Size

        // dataTable = dataTable.sampleN(100);
        //System.out.println(dataTable.structure());
        //System.out.println("Stock-Prices Table Row Count: " + dataTable.rowCount());

        return dataTable;
    }

}
