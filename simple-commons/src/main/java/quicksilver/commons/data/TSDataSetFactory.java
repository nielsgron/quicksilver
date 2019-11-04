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
import tech.tablesaw.io.jdbc.SqlResultSetReader;

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

    public static TSDataSet createSampleFamilyTreeData() {
        TSDataSet dataSet = createSampleDataSet(new String[]{"Name", "Parent"},
                new Class[]{String.class, String.class});
        dataSet.addRow(new Object[] { "FreeBSD"         , "386BSD" });
        dataSet.addRow(new Object[] { "OpenBSD"         , "NetBSD" });
        dataSet.addRow(new Object[] { "NetBSD"          , "386BSD" });
        dataSet.addRow(new Object[] { "DragonFly BSD"   , "FreeBSD" });
        dataSet.addRow(new Object[] { "macOS"           , "Darwin" });
        dataSet.addRow(new Object[] { "Darwin"          , "FreeBSD" });

        return dataSet;
    }

    public static TSDataSet createSampleCountryEconomicData() {

        // Country, GDP, Population

        TSDataSet dataSet = createSampleDataSet(new String[]{"Country", "GDP", "GDP_Capita", "Population"},
                new Class[]{String.class, Double.class, Double.class, Double.class});
        dataSet.addRow(new Object[] { "US"          , 20580000000000D    , 62869D    , 327167434D });
        dataSet.addRow(new Object[] { "China"       , 14216000000000D    , 10153D    , 1403500365D });
        dataSet.addRow(new Object[] { "Japan"       ,  5176000000000D    , 41021D    , 126317000D });
        dataSet.addRow(new Object[] { "Germany"     ,  4117000000000D    , 49692D    , 83019200D });
        dataSet.addRow(new Object[] { "India"       ,  2972000000000D    , 2199D     , 1324171354D });
        dataSet.addRow(new Object[] { "France"      ,  2845000000000D    , 43500D    , 67022000D });
        dataSet.addRow(new Object[] { "UK"          ,  2829000000000D    , 42580D    , 67545757D });
        dataSet.addRow(new Object[] { "Canada"      ,  1820000000000D    , 48601D    , 37602103D });
        dataSet.addRow(new Object[] { "Spain"       ,  1583000000000D    , 34281D    , 46733038D });
        dataSet.addRow(new Object[] { "Australia"   ,  1376000000000D    , 53825D    , 25546800D });

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

}
