package quicksilver.commons.data;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import tech.tablesaw.api.*;
import tech.tablesaw.columns.Column;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class TSDataSetFactory {

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
                colNames[i] = metadata.getColumnLabel(i + 1);
                colTypes[i] = Class.forName(metadata.getColumnClassName(i + 1));

                switch (metadata.getColumnType(i + 1)) {

                    case -7 : // -7 BIT
                        colObjects[i] = BooleanColumn.create(colNames[i]);
                        break;
                    case -6 : // -6 TINYINT
                        colObjects[i] = DoubleColumn.create(colNames[i]);
                        break;
                    case -5 : // -5 BIGINT
                        colObjects[i] = DoubleColumn.create(colNames[i]);
                        break;
                    case -4 : // -4 LONGVARBINARY
                        colObjects[i] = StringColumn.create(colNames[i]);
                        break;
                    case -3 : // -3 VARBINARY
                        colObjects[i] = StringColumn.create(colNames[i]);
                        break;
                    case -2 : // -2 BINARY
                        colObjects[i] = StringColumn.create(colNames[i]);
                        break;
                    case -1 : // -1 LONGVARCHAR
                        colObjects[i] = StringColumn.create(colNames[i]);
                        break;
                    case 0 : // 0 NULL
                        colObjects[i] = StringColumn.create(colNames[i]);
                        break;
                    case 1 : // 1 CHAR
                        colObjects[i] = StringColumn.create(colNames[i]);
                        break;
                    case 2 : // 2 NUMERIC
                        colObjects[i] = DoubleColumn.create(colNames[i]);
                        break;
                    case 3 : // 3 DECIMAL
                        colObjects[i] = DoubleColumn.create(colNames[i]);
                        break;
                    case 4 : // 4 INTEGER
                        colObjects[i] = DoubleColumn.create(colNames[i]);
                        break;
                    case 5 : // 5 SMALLINT
                        colObjects[i] = DoubleColumn.create(colNames[i]);
                        break;
                    case 6 : // 6 FLOAT
                        colObjects[i] = DoubleColumn.create(colNames[i]);
                        break;
                    case 7 : // 7 REAL
                        colObjects[i] = DoubleColumn.create(colNames[i]);
                        break;
                    case 8 : // 8 DOUBLE
                        colObjects[i] = DoubleColumn.create(colNames[i]);
                        break;
                    case 12 : // 12 VARCHAR
                        colObjects[i] = StringColumn.create(colNames[i]);
                        break;
                    case 91 : // 91 DATE
                        colObjects[i] = DateColumn.create(colNames[i]);
                        break;
                    case 92 : // 92 TIME
                        colObjects[i] = TimeColumn.create(colNames[i]);
                        break;
                    case 93 : // 93 TIMESTAMP
                        colObjects[i] = DateTimeColumn.create(colNames[i]);
                        break;
                    default :  // 1111 OTHER
                        colObjects[i] = StringColumn.create(colNames[i]);

                }
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        TSDataSetMeta meta = new TSDataSetMeta(colObjects, colNames, colTypes);
        TSDataSet dataSet = new TSDataSet(meta);

        while ( rs.next() ) {
            // Get the row values
            Object[] row = new Object[columnCount];
            for (int i = 0; i < columnCount; i++) {
                row[i] = rs.getObject(i + 1);
            }
            dataSet.addRow(row);
        }

        return dataSet;
    }

}
