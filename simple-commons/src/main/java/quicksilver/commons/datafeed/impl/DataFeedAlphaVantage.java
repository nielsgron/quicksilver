package quicksilver.commons.datafeed.impl;

import quicksilver.commons.datafeed.DataFeed;
import quicksilver.commons.datafeed.DataFeedCSV;
import quicksilver.commons.datafeed.DataFeedJSON;
import tech.tablesaw.api.Table;

import java.io.IOException;

public class DataFeedAlphaVantage extends DataFeedCSV {

    // Public APIs : Finance : https://github.com/public-apis/public-apis#finance
    // Alpha Vantage API docs : https://www.alphavantage.co/documentation/
    // URL Example : https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=5min&apikey=demo

    public static String TIME_SERIES_INTRADAY = "TIME_SERIES_INTRADAY";
    public static String TIME_SERIES_DAILY = "TIME_SERIES_DAILY";
    public static String TIME_SERIES_DAILY_ADJUSTED = "TIME_SERIES_DAILY_ADJUSTED";
    public static String TIME_SERIES_WEEKLY = "TIME_SERIES_WEEKLY";
    public static String TIME_SERIES_WEEKLY_ADJUSTED = "TIME_SERIES_WEEKLY_ADJUSTED";
    public static String TIME_SERIES_MONTHLY = "TIME_SERIES_MONTHLY";
    public static String TIME_SERIES_MONTHLY_ADJUSTED = "TIME_SERIES_MONTHLY_ADJUSTED";
    public static String GLOBAL_QUOTE = "GLOBAL_QUOTE";

    public static String INTERVAL_1_MINUTE = "1min";
    public static String INTERVAL_5_MINUTE = "5min";
    public static String INTERVAL_15_MINUTE = "15min";
    public static String INTERVAL_30_MINUTE = "30min";
    public static String INTERVAL_60_MINUTE = "60min";

    public static String OUTPUTSIZE_COMPACT = "compact";
    public static String OUTPUTSIZE_FULL = "full";

    public DataFeedAlphaVantage() {
        super("https://www.alphavantage.co/query");

        setFunction(TIME_SERIES_INTRADAY);
        setSymbol("AAPL");
        setInterval(INTERVAL_60_MINUTE);

        // addParameter("datatype", "json");
        addParameter("datatype", "csv");

    }

    public void setFunction(String value) {
        addParameter("function", value);
    }

    public void setSymbol(String value) {
        addParameter("symbol", value);
    }

    public void setInterval(String value) {
        addParameter("interval", value);
    }

    public void setOutputSize(String value) {
        addParameter("outputsize", value);
    }

    public void setAPIKey(String value) {
        addParameter("apikey", value);
    }

    public static void main(String[] args) {

        String apiKey = System.getProperty("datafeed.apikey.alphaadvantage");
        if ( apiKey == null ) {
            System.out.println("Set API Key with -Ddatafeed.apikey.alphaadvantage=KEY");
            System.exit(0);
        }

        DataFeedAlphaVantage dataFeed;
        Table dataTable;

        dataFeed = new DataFeedAlphaVantage();
        dataFeed.setFunction(TIME_SERIES_INTRADAY);
        dataFeed.setSymbol("MSFT");
        dataFeed.setInterval(INTERVAL_60_MINUTE);
        dataFeed.setOutputSize(OUTPUTSIZE_COMPACT);
        dataFeed.setAPIKey(apiKey);

        System.out.println("API Requesting URL : " + dataFeed.buildRequestURL());

        try {
            dataFeed.request();
            dataTable = dataFeed.getDataTable();
            System.out.print(dataTable.structure());
            System.out.print(dataTable.print());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}