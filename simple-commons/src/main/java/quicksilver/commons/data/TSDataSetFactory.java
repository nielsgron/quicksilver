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

}
