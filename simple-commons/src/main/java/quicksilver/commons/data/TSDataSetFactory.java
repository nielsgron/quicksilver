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

package quicksilver.commons.data;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import tech.tablesaw.api.*;
import tech.tablesaw.columns.Column;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TSDataSetFactory {

    private static final Map<Integer, ColumnType> SQL_TYPE_TO_TABLESAW_TYPE = initializeMap();

    private static Map<Integer, ColumnType> initializeMap() {
        return new HashMap<>(
                new ImmutableMap.Builder<Integer, ColumnType>()
                        .put(Types.BINARY, ColumnType.BOOLEAN)
                        .put(Types.BOOLEAN, ColumnType.BOOLEAN)
                        .put(Types.BIT, ColumnType.BOOLEAN)

                        // TODO: DateTime on MySQL returns a java.sql.Date value, but DateColumn doesn't support this type
                        .put(Types.DATE, ColumnType.LOCAL_DATE_TIME)
                        //.put(Types.DATE, ColumnType.LOCAL_DATE)
                        .put(Types.TIME, ColumnType.LOCAL_TIME)
                        .put(Types.TIMESTAMP, ColumnType.LOCAL_DATE_TIME)

                        .put(Types.DECIMAL, ColumnType.DOUBLE)
                        .put(Types.DOUBLE, ColumnType.DOUBLE)
                        .put(Types.FLOAT, ColumnType.DOUBLE)
                        .put(Types.NUMERIC, ColumnType.DOUBLE)
                        .put(Types.REAL, ColumnType.FLOAT)

                        .put(Types.INTEGER, ColumnType.INTEGER)
                        .put(Types.SMALLINT, ColumnType.SHORT)
                        .put(Types.TINYINT, ColumnType.SHORT)
                        // TODO: BIGINT on MySQL returns a Long value, but DoubleColumn doesn't support Long or BigInteger.
                        .put(Types.BIGINT, ColumnType.LONG)
                        //.put(Types.BIGINT, ColumnType.DOUBLE)

                        .put(Types.CHAR, ColumnType.STRING)
                        .put(Types.NCHAR, ColumnType.STRING)
                        .put(Types.NVARCHAR, ColumnType.STRING)
                        .put(Types.VARCHAR, ColumnType.STRING)
                        .put(Types.LONGVARCHAR, ColumnType.TEXT)
                        .put(Types.LONGNVARCHAR, ColumnType.TEXT)
                        .build());
    }

    public static TSDataSet createDataSetFromQuery(Connection dbConnection, String query, Object... params ) {

        ResultSetHandler<TSDataSet> h = new ResultSetHandler<TSDataSet>() {
            public TSDataSet handle(ResultSet rs) throws SQLException {
                return createDataSetFromResultSet(rs);
            }
        };

        QueryRunner run = new QueryRunner();
        TSDataSet result;
        try {
            result = run.query(dbConnection, query, h, params);
        } catch ( Exception e ) {
            result = null;
            e.printStackTrace();
        }
        return result;
    }

    public static TSDataSet createDataSetFromResultSet( ResultSet rs ) throws SQLException {

        if ( rs == null ) {
            return null;
        }

        ResultSetMetaData metadata = rs.getMetaData();
        int columnCount = metadata.getColumnCount();

        // Get the column names and types
        String[] colNames = new String[columnCount];
        Class[] colTypes = new Class[columnCount];
        Column[] colObjects = new Column[columnCount];

        try {
            for (int i = 0; i < columnCount; i++) {
                int columnTypeInt = metadata.getColumnType(i + 1);
                colNames[i] = metadata.getColumnLabel(i + 1);
                colTypes[i] = Class.forName(metadata.getColumnClassName(i + 1));
                colObjects[i] = createColumnByJDBCType(columnTypeInt, colNames[i]);
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        //Table t = SqlResultSetReader.read(rs,"tableName");

        TSDataSetMeta meta = new TSDataSetMeta(colObjects, colNames, colTypes);
        TSDataSet dataSet = new TSDataSet(meta);

        while ( rs.next() ) {
            // Get the row values
            Object[] row = new Object[columnCount];
            for (int i = 0; i < columnCount; i++) {
                row[i] = rs.getObject(i + 1);
                if ( row[i] instanceof java.sql.Date ) {
                    row[i] = new java.sql.Timestamp(((java.sql.Date)row[i]).getTime());
                }
            }
            dataSet.addRow(row);
        }

        return dataSet;
    }

    public static Column createColumnByJDBCType(int type, String colName) {

        ColumnType colType = SQL_TYPE_TO_TABLESAW_TYPE.get(type);

        Column colObject;

        if ( colType != null ) {
            colObject = colType.create(colName);
        } else {
            colObject = StringColumn.create(colName);
        }

        return colObject;

    }

    public static Column createColumnByJavaType(Class type, String colName) {

        Column colObject = null;

        if ( type == String.class ) {
            colObject = StringColumn.create(colName);
        } else if ( type == Double.class ) {
            colObject = DoubleColumn.create(colName);
        } else if ( type == Float.class ) {
            colObject = FloatColumn.create(colName);
        } else if ( type == Long.class ) {
            colObject = LongColumn.create(colName);
        } else if ( type == Integer.class ) {
            colObject = IntColumn.create(colName);
        } else if ( type == Short.class ) {
            colObject = ShortColumn.create(colName);
        } else if ( type == BigDecimal.class ) {
            colObject = DoubleColumn.create(colName);
        } else if ( type == BigInteger.class ) {
            colObject = DoubleColumn.create(colName);
        } else if ( type == Boolean.class ) {
            colObject = BooleanColumn.create(colName);
        } else if ( type == java.sql.Timestamp.class ) {
            colObject = DateTimeColumn.create(colName);
        } else if ( type == java.sql.Date.class ) {
            colObject = DateTimeColumn.create(colName);
        } else if ( type == java.util.Date.class ) {
            colObject = DateTimeColumn.create(colName);
        } else if ( type == LocalDate.class ) {
            colObject = DateColumn.create(colName);
        }

        return colObject;

    }

    public static TSDataSet createSampleDataSet(String[] colNames, Class[] colTypes) {

        // Get the column names and types
        Column[] colObjects = new Column[colNames.length];

        // Populate column objects
        for ( int i = 0; i < colNames.length; i++ ) {
            colObjects[i] = TSDataSetFactory.createColumnByJavaType(colTypes[i], colNames[i]);
        }

        // Construct DataSet
        TSDataSetMeta meta = new TSDataSetMeta(colObjects, colNames, colTypes);
        TSDataSet dataSet = new TSDataSet(meta);

        // Populate rows
        // ?

        return dataSet;
    }

    public static TSDataSet createSampleCountryEconomicData() {

        // Country, GDP, Population

        TSDataSet dataSet = createSampleDataSet(new String[]{"Country", "GDP", "GDP_Capita", "GDP_Capita_Thousand", "Population", "Continent"},
                new Class[]{String.class, Double.class, Double.class, Double.class, Double.class, String.class});
        dataSet.addRow(new Object[] { "US"          , 20580000000000D    , 62869D    , 62.869D    ,  327167434D   , "North America"});
        dataSet.addRow(new Object[] { "China"       , 14216000000000D    , 10153D    , 10.153D    , 1403500365D  , "Asia"});
        dataSet.addRow(new Object[] { "Japan"       ,  5176000000000D    , 41021D    , 41.021D    , 126317000D   , "Asia"});
        dataSet.addRow(new Object[] { "Germany"     ,  4117000000000D    , 49692D    , 49.692D    , 83019200D    , "Europe"});
        dataSet.addRow(new Object[] { "India"       ,  2972000000000D    , 2199D     , 2.199D     , 1324171354D  , "Asia"});
        dataSet.addRow(new Object[] { "France"      ,  2845000000000D    , 43500D    , 43.500D    , 67022000D    , "Europe"});
        dataSet.addRow(new Object[] { "UK"          ,  2829000000000D    , 42580D    , 42.580D    , 67545757D    , "Europe"});
        dataSet.addRow(new Object[] { "Canada"      ,  1820000000000D    , 48601D    , 48.601D    , 37602103D    , "North America"});
        dataSet.addRow(new Object[] { "Spain"       ,  1583000000000D    , 34281D    , 34.281D    , 46733038D    , "Europe"});
        dataSet.addRow(new Object[] { "Australia"   ,  1376000000000D    , 53825D    , 53.825D    , 25546800D    , "Australia"});

        dataSet.sort("-GDP");

        return dataSet;
    }

    public static TSDataSet createSampleStockMarketEquities() {

        // Exchange, Ticker, Company, Sector, Industry, MarketCap, Price

        TSDataSet dataSet = createSampleDataSet(new String[]{"Ticker", "Company", "Sector", "Industry", "Country", "MarketCap", "Price", "Change"},
                new Class[]{String.class, String.class, String.class, String.class, String.class, Double.class, Double.class, Double.class});
        dataSet.addRow(new Object[] { "SQ", "Square, Inc", "Technology", "Internet Software & Services", "USA", 27.65D, 61.72D, 1.1D });
        dataSet.addRow(new Object[] { "FB", "Facebook, Inc.", "Technology", "Internet Information Providers", "USA", 544.72D, 186.38D, 1.1D });
        dataSet.addRow(new Object[] { "CRM", "salesforce.com, inc.", "Technology", "Application Software", "USA", 131.78D, 148.12D, 1.1D });
        dataSet.addRow(new Object[] { "DBX", "Dropbox, Inc.", "Technology", "Application Software", "USA", 8.31D, 19.55D, 1.1D });
        dataSet.addRow(new Object[] { "DOCU", "DocuSign, Inc.", "Technology", "Business Software & Services", "USA", 11.81D, 64.91D, 1.1D });
        dataSet.addRow(new Object[] { "APLT", "Applied Therapeutics, Inc.", "Healthcare", "Biotechnology", "USA", 0.25226D, 17.01D, 1.1D });
        dataSet.addRow(new Object[] { "ABMD", "Abiomed, Inc.", "Healthcare", "Medical Appliances & Equipment", "USA", 8.22D, 179.96D, 1.1D });
        dataSet.addRow(new Object[] { "ACM", "AECOM", "Services", "Technical Services", "USA", 6.41D, 40.59D, 1.1D });

        dataSet.sort("MarketCap");

        return dataSet;
    }

    public static TSDataSet createSampleStockPrices() {

        // Stock, Date, Open, High, Low, Close

        TSDataSet dataSet = createSampleDataSet(new String[]{"Date", "Open", "High", "Low", "Close", "Adj Close", "Volume"},
                new Class[]{LocalDate.class, Double.class, Double.class, Double.class, Double.class, Double.class, Long.class});
        dataSet.addRow(new Object[] { LocalDate.of(2019, 5, 1), 120D, 121D, 118D, 119D, 119D, 1000L });
        dataSet.addRow(new Object[] { LocalDate.of(2019, 5, 2), 119D, 119D, 110D, 112D, 119D, 1000L });
        dataSet.addRow(new Object[] { LocalDate.of(2019, 5, 3), 112D, 112D, 103D, 103D, 119D, 1000L });
        dataSet.addRow(new Object[] { LocalDate.of(2019, 5, 4), 103D, 115D, 103D, 114D, 119D, 1000L });
        dataSet.addRow(new Object[] { LocalDate.of(2019, 5, 5), 114D, 131D, 112D, 130D, 119D, 1000L });
        dataSet.addRow(new Object[] { LocalDate.of(2019, 5, 6), 130D, 142D, 129D, 141D, 119D, 1000L });
        dataSet.addRow(new Object[] { LocalDate.of(2019, 5, 7), 141D, 147D, 137D, 145D, 119D, 1000L });
        dataSet.addRow(new Object[] { LocalDate.of(2019, 5, 8), 145D, 165D, 143D, 165D, 119D, 1000L });
        dataSet.addRow(new Object[] { LocalDate.of(2019, 5, 9), 165D, 165D, 153D, 154D, 119D, 1000L });
        dataSet.addRow(new Object[] { LocalDate.of(2019, 5, 10), 154D, 154D, 130D, 130D, 119D, 1000L });
        dataSet.addRow(new Object[] { LocalDate.of(2019, 5, 11), 130D, 130D, 128D, 130D, 119D, 1000L });
        dataSet.addRow(new Object[] { LocalDate.of(2019, 5, 12), 130D, 145D, 129D, 144D, 119D, 1000L });

        dataSet.sort("Date");

        return dataSet;
    }

    //X11 / CSS colors from https://www.w3.org/TR/css-color-3/#svg-color
    private final static Map<String, int[]> CSS_COLORS = new HashMap<>();

    static {
        CSS_COLORS.put("aliceblue", new int[]{240, 248, 255});
        CSS_COLORS.put("antiquewhite", new int[]{250, 235, 215});
        CSS_COLORS.put("aqua", new int[]{0, 255, 255});
        CSS_COLORS.put("aquamarine", new int[]{127, 255, 212});
        CSS_COLORS.put("azure", new int[]{240, 255, 255});
        CSS_COLORS.put("beige", new int[]{245, 245, 220});
        CSS_COLORS.put("bisque", new int[]{255, 228, 196});
        CSS_COLORS.put("black", new int[]{0, 0, 0});
        CSS_COLORS.put("blanchedalmond", new int[]{255, 235, 205});
        CSS_COLORS.put("blue", new int[]{0, 0, 255});
        CSS_COLORS.put("blueviolet", new int[]{138, 43, 226});
        CSS_COLORS.put("brown", new int[]{165, 42, 42});
        CSS_COLORS.put("burlywood", new int[]{222, 184, 135});
        CSS_COLORS.put("cadetblue", new int[]{95, 158, 160});
        CSS_COLORS.put("chartreuse", new int[]{127, 255, 0});
        CSS_COLORS.put("chocolate", new int[]{210, 105, 30});
        CSS_COLORS.put("coral", new int[]{255, 127, 80});
        CSS_COLORS.put("cornflowerblue", new int[]{100, 149, 237});
        CSS_COLORS.put("cornsilk", new int[]{255, 248, 220});
        CSS_COLORS.put("crimson", new int[]{220, 20, 60});
        CSS_COLORS.put("cyan", new int[]{0, 255, 255});
        CSS_COLORS.put("darkblue", new int[]{0, 0, 139});
        CSS_COLORS.put("darkcyan", new int[]{0, 139, 139});
        CSS_COLORS.put("darkgoldenrod", new int[]{184, 134, 11});
        CSS_COLORS.put("darkgray", new int[]{169, 169, 169});
        CSS_COLORS.put("darkgreen", new int[]{0, 100, 0});
        CSS_COLORS.put("darkgrey", new int[]{169, 169, 169});
        CSS_COLORS.put("darkkhaki", new int[]{189, 183, 107});
        CSS_COLORS.put("darkmagenta", new int[]{139, 0, 139});
        CSS_COLORS.put("darkolivegreen", new int[]{85, 107, 47});
        CSS_COLORS.put("darkorange", new int[]{255, 140, 0});
        CSS_COLORS.put("darkorchid", new int[]{153, 50, 204});
        CSS_COLORS.put("darkred", new int[]{139, 0, 0});
        CSS_COLORS.put("darksalmon", new int[]{233, 150, 122});
        CSS_COLORS.put("darkseagreen", new int[]{143, 188, 143});
        CSS_COLORS.put("darkslateblue", new int[]{72, 61, 139});
        CSS_COLORS.put("darkslategray", new int[]{47, 79, 79});
        CSS_COLORS.put("darkslategrey", new int[]{47, 79, 79});
        CSS_COLORS.put("darkturquoise", new int[]{0, 206, 209});
        CSS_COLORS.put("darkviolet", new int[]{148, 0, 211});
        CSS_COLORS.put("deeppink", new int[]{255, 20, 147});
        CSS_COLORS.put("deepskyblue", new int[]{0, 191, 255});
        CSS_COLORS.put("dimgray", new int[]{105, 105, 105});
        CSS_COLORS.put("dimgrey", new int[]{105, 105, 105});
        CSS_COLORS.put("dodgerblue", new int[]{30, 144, 255});
        CSS_COLORS.put("firebrick", new int[]{178, 34, 34});
        CSS_COLORS.put("floralwhite", new int[]{255, 250, 240});
        CSS_COLORS.put("forestgreen", new int[]{34, 139, 34});
        CSS_COLORS.put("fuchsia", new int[]{255, 0, 255});
        CSS_COLORS.put("gainsboro", new int[]{220, 220, 220});
        CSS_COLORS.put("ghostwhite", new int[]{248, 248, 255});
        CSS_COLORS.put("gold", new int[]{255, 215, 0});
        CSS_COLORS.put("goldenrod", new int[]{218, 165, 32});
        CSS_COLORS.put("gray", new int[]{128, 128, 128});
        CSS_COLORS.put("green", new int[]{0, 128, 0});
        CSS_COLORS.put("greenyellow", new int[]{173, 255, 47});
        CSS_COLORS.put("grey", new int[]{128, 128, 128});
        CSS_COLORS.put("honeydew", new int[]{240, 255, 240});
        CSS_COLORS.put("hotpink", new int[]{255, 105, 180});
        CSS_COLORS.put("indianred", new int[]{205, 92, 92});
        CSS_COLORS.put("indigo", new int[]{75, 0, 130});
        CSS_COLORS.put("ivory", new int[]{255, 255, 240});
        CSS_COLORS.put("khaki", new int[]{240, 230, 140});
        CSS_COLORS.put("lavender", new int[]{230, 230, 250});
        CSS_COLORS.put("lavenderblush", new int[]{255, 240, 245});
        CSS_COLORS.put("lawngreen", new int[]{124, 252, 0});
        CSS_COLORS.put("lemonchiffon", new int[]{255, 250, 205});
        CSS_COLORS.put("lightblue", new int[]{173, 216, 230});
        CSS_COLORS.put("lightcoral", new int[]{240, 128, 128});
        CSS_COLORS.put("lightcyan", new int[]{224, 255, 255});
        CSS_COLORS.put("lightgoldenrodyellow", new int[]{250, 250, 210});
        CSS_COLORS.put("lightgray", new int[]{211, 211, 211});
        CSS_COLORS.put("lightgreen", new int[]{144, 238, 144});
        CSS_COLORS.put("lightgrey", new int[]{211, 211, 211});
        CSS_COLORS.put("lightpink", new int[]{255, 182, 193});
        CSS_COLORS.put("lightsalmon", new int[]{255, 160, 122});
        CSS_COLORS.put("lightseagreen", new int[]{32, 178, 170});
        CSS_COLORS.put("lightskyblue", new int[]{135, 206, 250});
        CSS_COLORS.put("lightslategray", new int[]{119, 136, 153});
        CSS_COLORS.put("lightslategrey", new int[]{119, 136, 153});
        CSS_COLORS.put("lightsteelblue", new int[]{176, 196, 222});
        CSS_COLORS.put("lightyellow", new int[]{255, 255, 224});
        CSS_COLORS.put("lime", new int[]{0, 255, 0});
        CSS_COLORS.put("limegreen", new int[]{50, 205, 50});
        CSS_COLORS.put("linen", new int[]{250, 240, 230});
        CSS_COLORS.put("magenta", new int[]{255, 0, 255});
        CSS_COLORS.put("maroon", new int[]{128, 0, 0});
        CSS_COLORS.put("mediumaquamarine", new int[]{102, 205, 170});
        CSS_COLORS.put("mediumblue", new int[]{0, 0, 205});
        CSS_COLORS.put("mediumorchid", new int[]{186, 85, 211});
        CSS_COLORS.put("mediumpurple", new int[]{147, 112, 219});
        CSS_COLORS.put("mediumseagreen", new int[]{60, 179, 113});
        CSS_COLORS.put("mediumslateblue", new int[]{123, 104, 238});
        CSS_COLORS.put("mediumspringgreen", new int[]{0, 250, 154});
        CSS_COLORS.put("mediumturquoise", new int[]{72, 209, 204});
        CSS_COLORS.put("mediumvioletred", new int[]{199, 21, 133});
        CSS_COLORS.put("midnightblue", new int[]{25, 25, 112});
        CSS_COLORS.put("mintcream", new int[]{245, 255, 250});
        CSS_COLORS.put("mistyrose", new int[]{255, 228, 225});
        CSS_COLORS.put("moccasin", new int[]{255, 228, 181});
        CSS_COLORS.put("navajowhite", new int[]{255, 222, 173});
        CSS_COLORS.put("navy", new int[]{0, 0, 128});
        CSS_COLORS.put("oldlace", new int[]{253, 245, 230});
        CSS_COLORS.put("olive", new int[]{128, 128, 0});
        CSS_COLORS.put("olivedrab", new int[]{107, 142, 35});
        CSS_COLORS.put("orange", new int[]{255, 165, 0});
        CSS_COLORS.put("orangered", new int[]{255, 69, 0});
        CSS_COLORS.put("orchid", new int[]{218, 112, 214});
        CSS_COLORS.put("palegoldenrod", new int[]{238, 232, 170});
        CSS_COLORS.put("palegreen", new int[]{152, 251, 152});
        CSS_COLORS.put("paleturquoise", new int[]{175, 238, 238});
        CSS_COLORS.put("palevioletred", new int[]{219, 112, 147});
        CSS_COLORS.put("papayawhip", new int[]{255, 239, 213});
        CSS_COLORS.put("peachpuff", new int[]{255, 218, 185});
        CSS_COLORS.put("peru", new int[]{205, 133, 63});
        CSS_COLORS.put("pink", new int[]{255, 192, 203});
        CSS_COLORS.put("plum", new int[]{221, 160, 221});
        CSS_COLORS.put("powderblue", new int[]{176, 224, 230});
        CSS_COLORS.put("purple", new int[]{128, 0, 128});
        CSS_COLORS.put("red", new int[]{255, 0, 0});
        CSS_COLORS.put("rosybrown", new int[]{188, 143, 143});
        CSS_COLORS.put("royalblue", new int[]{65, 105, 225});
        CSS_COLORS.put("saddlebrown", new int[]{139, 69, 19});
        CSS_COLORS.put("salmon", new int[]{250, 128, 114});
        CSS_COLORS.put("sandybrown", new int[]{244, 164, 96});
        CSS_COLORS.put("seagreen", new int[]{46, 139, 87});
        CSS_COLORS.put("seashell", new int[]{255, 245, 238});
        CSS_COLORS.put("sienna", new int[]{160, 82, 45});
        CSS_COLORS.put("silver", new int[]{192, 192, 192});
        CSS_COLORS.put("skyblue", new int[]{135, 206, 235});
        CSS_COLORS.put("slateblue", new int[]{106, 90, 205});
        CSS_COLORS.put("slategray", new int[]{112, 128, 144});
        CSS_COLORS.put("slategrey", new int[]{112, 128, 144});
        CSS_COLORS.put("snow", new int[]{255, 250, 250});
        CSS_COLORS.put("springgreen", new int[]{0, 255, 127});
        CSS_COLORS.put("steelblue", new int[]{70, 130, 180});
        CSS_COLORS.put("tan", new int[]{210, 180, 140});
        CSS_COLORS.put("teal", new int[]{0, 128, 128});
        CSS_COLORS.put("thistle", new int[]{216, 191, 216});
        CSS_COLORS.put("tomato", new int[]{255, 99, 71});
        CSS_COLORS.put("turquoise", new int[]{64, 224, 208});
        CSS_COLORS.put("violet", new int[]{238, 130, 238});
        CSS_COLORS.put("wheat", new int[]{245, 222, 179});
        CSS_COLORS.put("white", new int[]{255, 255, 255});
        CSS_COLORS.put("whitesmoke", new int[]{245, 245, 245});
        CSS_COLORS.put("yellow", new int[]{255, 255, 0});
        CSS_COLORS.put("yellowgreen", new int[]{154, 205, 50});
    }

    public static TSDataSet createCSSColorsTable() {
        TSDataSet dataSet = TSDataSetFactory.createSampleDataSet(new String[]{"Color", "RGB"},
                new Class[]{String.class, String.class});

        CSS_COLORS.forEach((String name, int[] rgb) -> {
            dataSet.addRow(new Object[]{name, String.format("rgb(%s, %s, %s)", rgb[0], rgb[1], rgb[2])});
        });

        return dataSet;
    }

}
