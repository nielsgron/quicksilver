/*
 * Copyright 2018-2019 Niels Gron and Contributors All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package quicksilver.commons.datafeed.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Iterator;
import java.util.Map;

import quicksilver.commons.datafeed.AbstractHttpRequester;
import quicksilver.commons.datafeed.DataFeedCSV;
import quicksilver.commons.datafeed.DataFeedJSON;
import tech.tablesaw.api.ColumnType;
import tech.tablesaw.api.Table;
import tech.tablesaw.columns.dates.DateColumnType;
import tech.tablesaw.columns.numbers.DoubleColumnType;
import tech.tablesaw.columns.numbers.LongColumnType;
import tech.tablesaw.columns.strings.StringColumnType;
import tech.tablesaw.io.csv.CsvReadOptions;

import java.io.IOException;

public class DataFeedAlphaVantage extends DataFeedCSV {

    // Public APIs : Finance : https://github.com/public-apis/public-apis#finance
    // Alpha Vantage API docs : https://www.alphavantage.co/documentation/
    // URL Example : https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=5min&apikey=demo

    public static String BATCH_STOCK_QUOTES = "BATCH_STOCK_QUOTES";

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

    /**
     * Transform the JSON format
     * <pre>
     * {
     * "Meta Data": { ... }
     *   "Time Series (60min)": {
     *     "2019-10-11 15:30:00": {
     *       "1. open": "v1",
     *       "2. high": "v2",
     *       "3. low": "v3",
     *       "4. close": "v4",
     *       "5. volume": "v5"
     *     }, ...
     *   }
     * }
     * </pre>
     *
     * into what Tablesaw can parse:
     * <pre>
     * [
     *   {
     *     "timestamp": "2019-10-11 15:30:00",
     *     "1. open": "v1",
     *     "2. high": "v2",
     *     "3. low": "v3",
     *     "4. close": "v4",
     *     "5. volume": "v5"
     *   },...
     * ]
     * </pre>
     *
     * @param text the transformed JSON or the same text if any (IO)error happened
     * @return
     */
    @Override
    protected byte[] transformPayload(byte[] text) {
        //transform the JSON format only
        if (!"json".equals(getParameter("datatype"))) {
            return text;
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = null;
        try {
            tree = mapper.readTree(text);
        } catch (IOException ex) {
            //not really possible?
            return text;
        }

        tree = tree.at("/Time Series (60min)");

        ArrayNode array = mapper.createArrayNode();
        Iterator<Map.Entry<String, JsonNode>> fields = tree.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            if (field.getValue() instanceof ObjectNode) {
                ((ObjectNode) field.getValue()).put("timestamp", field.getKey());
            }
            array.add(field.getValue());
        }
        try {
            return mapper.writeValueAsBytes(array);
        } catch (JsonProcessingException ex) {
            //TODO: log
            return text;
        }
    }

    @Override
    protected void buildDataSet() throws IOException {

        dataTable = null;

        CsvReadOptions.Builder builder = CsvReadOptions.builder(new InputStreamReader(new ByteArrayInputStream(dataPayload), charset))
                .columnTypes( new ColumnType[] {
                        StringColumnType.instance(), // symbol
                        DoubleColumnType.instance(), // open
                        DoubleColumnType.instance(), // high
                        DoubleColumnType.instance(), // low
                        DoubleColumnType.instance(), // price
                        LongColumnType.instance(),   // volume
                        DateColumnType.instance(),   // latestDay
                        DoubleColumnType.instance(), // previousClose
                        DoubleColumnType.instance(), // change
                        StringColumnType.instance(), // changePercent
                } );

        dataTable = Table.read().csv(builder);
        //dataTable = Table.read().csv(new InputStreamReader(new ByteArrayInputStream(dataPayload), charset));
    }

    public void request(AbstractHttpRequester req) throws IOException {

        // Only 1 symbol can be retrieved per HTTP request, lets break up the list and merge table results

        String symbols = getParameter("symbol");
        String[] sym = symbols.split(",");

        Table finalTable = null;

        for ( int i = 0; i < sym.length; i++ ) {
            addParameter("symbol", sym[i].trim());

            URI uri = buildRequest();

            System.out.println("Requesting Symbol : " + sym[i].trim());
            dataPayload = req.requestURL(uri.toURL());
            dataPayload = transformPayload(dataPayload);

            try {
                buildDataSet();
            } catch ( Exception e ) {
                e.printStackTrace();
            }

            if ( dataTable != null ) {
                if (finalTable == null) {
                    finalTable = dataTable;
                } else {
                    finalTable.append(dataTable);
                }
            }

            try {
                Thread.sleep(15_000);
            } catch ( Exception e ) {
                e.printStackTrace();
            }

        }

        dataTable = finalTable;
        dataPayload = dataTable.write().toString("csv").getBytes();

        System.out.println(dataTable.printAll());

        addParameter("symbol", symbols);
    }

    public void setFeedTableName(String feedTableName) {
        setFunction(feedTableName);
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

        System.out.println("API Requesting URL : " + dataFeed.buildRequest());

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