package quicksilver.commons.db;

import org.javalite.activejdbc.Model;

import java.math.BigDecimal;
import java.sql.*;
import java.util.HashMap;

public class BulkImport {

    // https://stackoverflow.com/questions/4355046/java-insert-multiple-rows-into-mysql-with-preparedstatement
    // https://stackoverflow.com/questions/2993251/jdbc-batch-insert-performance

    private boolean enableLog = true;

    private Connection connection;
    private Model dbObject;
    private int batchSize = 1000;
    protected boolean merge = false;
    private int currentBatchQueue = 0;
    private long totalRowsBatched = 0;
    private long totalRowInsertCount = -1;

    private long prepareBatchStartTime = 0;
    private long prepareBatchEndTime = 0;

    private String keyColumn = null;

    private PreparedStatement preparedStatement = null;

    private HashMap<String, Integer> columnIndexes = new HashMap<String, Integer>();

    public BulkImport(Connection connection, Model dbObject, int batchSize) {

        this.connection = connection;
        this.dbObject = dbObject;
        this.batchSize = batchSize;
        this.merge = false;
    }

    public BulkImport(Connection connection, Model dbObject, int batchSize, boolean merge, String keyColumn) {

        this.connection = connection;
        this.dbObject = dbObject;
        this.batchSize = batchSize;
        this.merge = merge;
        this.keyColumn = keyColumn;
    }

    public void setTotalRowInsertCount(long total) {
        totalRowInsertCount = total;
    }

    protected String getOperationCommand() {
        if ( merge ) {
            return "MERGE"; // H2 = MERGE ; MySQL = REPLACE
        } else {
            return "INSERT";
        }
    }

    public void startBulkImport(String tableName, String... columns) throws SQLException {

        for ( int i = 0; i < columns.length; i++ ) {
            columnIndexes.put(columns[i], i+1);
        }

        StringBuilder sqlInsert = new StringBuilder();
        sqlInsert.append(getOperationCommand());
        sqlInsert.append(" INTO ");
        sqlInsert.append(tableName);
        sqlInsert.append(" ( ");

        for ( int i = 0; i < columns.length; i++ ) {
            if ( i > 0 ) {
                sqlInsert.append(", ");
            }
            sqlInsert.append(columns[i]);
        }

        sqlInsert.append(" ) ");

        // H2 requires KEY(keyColumn) for MERGE
        if ( merge && keyColumn != null ) {
            sqlInsert.append("KEY(");
            sqlInsert.append(keyColumn);
            sqlInsert.append(")");
        }

        sqlInsert.append("VALUES(");

        for ( int i = 0; i < columns.length; i++ ) {
            if ( i > 0 ) {
                sqlInsert.append(", ");
            }
            sqlInsert.append("?");
        }

        sqlInsert.append(")");

        if ( enableLog ) {
            System.out.println("Starting bulk import on table : " + tableName);
            System.out.println("PreparedStatement : " + sqlInsert.toString());
        }

        preparedStatement = connection.prepareStatement(sqlInsert.toString());

    }

    public void endBulkImport() throws SQLException {
        assertStarted();

        if ( currentBatchQueue > 0 ) {
            // We still have uncommitted items in the batch, so commit them before stopping the import

            if ( enableLog ) {
                prepareBatchEndTime = System.currentTimeMillis();

                System.out.print(" (" + (prepareBatchEndTime - prepareBatchStartTime) + "/ms)");
                System.out.print(" ... Executing final batch, size : " + currentBatchQueue);
            }

            long startTime = System.currentTimeMillis();
            int[] updatedCounts = preparedStatement.executeBatch();
            long endTime = System.currentTimeMillis();
            if ( enableLog ) {
                System.out.print(" (" + (endTime - startTime) + "/ms)");

                long totalUpdateCounts = 0;
                for ( int i = 0; i < updatedCounts.length; i++ ) {
                    totalUpdateCounts += updatedCounts[i];
                }

                System.out.println(" ... [" + updatedCounts.length + "] Updates. Total Update Counts [" + totalUpdateCounts + "].");
            }
            currentBatchQueue = 0;
        }

        if ( enableLog ) {
            System.out.println("   -> Ending bulk import.");
            System.out.println("");
        }

        preparedStatement.close();
        preparedStatement = null;
        connection = null;
    }

    public void saveIt() throws SQLException {
        assertStarted();

        if ( currentBatchQueue == 0 ) {
            prepareBatchStartTime = System.currentTimeMillis();
            if ( enableLog ) {
                System.out.print("   Preparing batches ...");
            }
        }

        try {
            //System.out.print(".");
            preparedStatement.addBatch();
            currentBatchQueue++;
            totalRowsBatched++;
        } catch (SQLException e) {
            throw e;
        }

        if ( currentBatchQueue >= batchSize ) {
            if ( enableLog ) {
                prepareBatchEndTime = System.currentTimeMillis();

                System.out.print(" (" + (prepareBatchEndTime - prepareBatchStartTime) + "/ms)");
                System.out.print(" ... Executing current batch, size : " + currentBatchQueue + " [ " + totalRowsBatched + " of " + totalRowInsertCount + " ]");
            }

            long startTime = System.currentTimeMillis();
            int[] updatedCounts = preparedStatement.executeBatch();
            long endTime = System.currentTimeMillis();
            if ( enableLog ) {
                System.out.print(" (" + (endTime - startTime) + "/ms)");

                long totalUpdateCounts = 0;
                for ( int i = 0; i < updatedCounts.length; i++ ) {
                    totalUpdateCounts += updatedCounts[i];
                }

                System.out.println(" ... [" + updatedCounts.length + "] Updates. Total Update Counts [" + totalUpdateCounts + "].");

            }
            currentBatchQueue = 0;
        }

    }

    public void setString(String columnName, String value) {
        try {
            if ( value == null ) {
                preparedStatement.setNull(columnIndexes.get(columnName), Types.VARCHAR);
            } else {
                preparedStatement.setString(columnIndexes.get(columnName), value);
            }
        } catch ( SQLException e ) { e.printStackTrace(); }
    }

    public void setBigDecimal(String columnName, BigDecimal value) {
        try {
            if ( value == null ) {
                preparedStatement.setNull(columnIndexes.get(columnName), Types.DECIMAL);
            } else {
                preparedStatement.setBigDecimal(columnIndexes.get(columnName), value);
            }
        } catch ( SQLException e ) { e.printStackTrace(); }
    }

    public void setFloat(String columnName, Float value) {
        try {
            if ( value == null ) {
                preparedStatement.setNull(columnIndexes.get(columnName), Types.FLOAT);
            } else {
                preparedStatement.setFloat(columnIndexes.get(columnName), value);
            }
        } catch ( SQLException e ) { e.printStackTrace(); }
    }

    public void setDouble(String columnName, Double value) {
        try {
            if ( value == null ) {
                preparedStatement.setNull(columnIndexes.get(columnName), Types.DOUBLE);
            } else {
                preparedStatement.setDouble(columnIndexes.get(columnName), value);
            }
        } catch ( SQLException e ) { e.printStackTrace(); }
    }

    public void setLong(String columnName, Long value) {
        try {
            if ( value == null ) {
                preparedStatement.setNull(columnIndexes.get(columnName), Types.DECIMAL);
            } else {
                preparedStatement.setLong(columnIndexes.get(columnName), value);
            }
        } catch ( SQLException e ) { e.printStackTrace(); }
    }

    public void setInt(String columnName, Integer value) {
        try {
            if ( value == null ) {
                preparedStatement.setNull(columnIndexes.get(columnName), Types.INTEGER);
            } else {
                preparedStatement.setInt(columnIndexes.get(columnName), value);
            }
        } catch ( SQLException e ) { e.printStackTrace(); }
    }

    public void setShort(String columnName, Short value) {
        try {
            if ( value == null ) {
                preparedStatement.setNull(columnIndexes.get(columnName), Types.TINYINT);
            } else {
                preparedStatement.setShort(columnIndexes.get(columnName), value);
            }
        } catch ( SQLException e ) { e.printStackTrace(); }
    }

    public void setBoolean(String columnName, Boolean value) {
        try {
            if ( value == null ) {
                preparedStatement.setNull(columnIndexes.get(columnName), Types.BOOLEAN);
            } else {
                preparedStatement.setBoolean(columnIndexes.get(columnName), value);
            }
        } catch ( SQLException e ) { e.printStackTrace(); }
    }

    public void setDate(String columnName, Date value) {
        try {
            if ( value == null ) {
                preparedStatement.setNull(columnIndexes.get(columnName), Types.DATE);
            } else {
                preparedStatement.setDate(columnIndexes.get(columnName), value);
            }
        } catch ( SQLException e ) { e.printStackTrace(); }
    }

    private void assertStarted() throws SQLException {
        if ( preparedStatement == null ) {
            throw new SQLException("Bulk import has not started. Call this.startBulkImport().");
        }
    }

}
