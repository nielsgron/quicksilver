package quicksilver.commons.db.metadata;

import quicksilver.commons.data.TSDataSet;
import quicksilver.commons.data.TSDataSetFactory;
import quicksilver.commons.data.TSDataSetMeta;
import quicksilver.commons.db.DatabaseConnection;
import tech.tablesaw.api.IntColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.columns.Column;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class DatabaseServer {

    protected String serverName;
    protected String host;
    protected String port;
    protected String database;
    protected String userName;
    protected String password;

    protected HashMap<String, MetaDatabase> databaseMap = new HashMap<String, MetaDatabase>();
    protected ArrayList<MetaDatabase> databaseList = new ArrayList<MetaDatabase>();

    public DatabaseServer(String serverName, String host, String port, String database, String userName, String password) {
        this.serverName = serverName;
        this.host = host;
        this.port = port;
        this.database = database;
        this.userName = userName;
        this.password = password;
    }

    public String getServerName() {
        return serverName;
    }
    public String getHost() {
        return host;
    }
    public String getPort() {
        return port;
    }
    public String getDatabase() {
        return database;
    }
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }

    public MetaDatabase addDatabase(MetaDatabase db) {

        databaseMap.put(db.getName(), db);
        databaseList.add(db);

        return db;
    }

    public abstract DatabaseConnection getDatabaseConnection(String databaseName);

    public MetaDatabase getDatabase(String name) {
        return databaseMap.get(name);
    }

    public List<MetaDatabase> getDatabases() {
        return databaseList;
    }

    public abstract void createDatabasesIfNotExist();

    public void createTablesIfNotExist() {

        List<MetaDatabase> dbList = getDatabases();
        int dbCount = dbList.size();

        for ( int i = 0; i < dbCount; i++ ) {
            MetaDatabase db = dbList.get(i);

            DatabaseConnection connection = getDatabaseConnection(db.getName());

            db.createTablesIfNotExist(connection);
        }

    }

    public void createIndexesIfNotExist(String databaseName, String tableName, boolean includePrimaryKey) {

        List<MetaDatabase> dbList = getDatabases();
        int dbCount = dbList.size();

        for ( int i = 0; i < dbCount; i++ ) {
            MetaDatabase db = dbList.get(i);

            if ( db.getName().equals(databaseName) ) {
                DatabaseConnection connection = getDatabaseConnection(db.getName());
                db.createIndexesIfNotExist(connection, tableName, includePrimaryKey);
            }
        }
    }

    public void dropIndexesIfExist(String databaseName, String tableName, boolean includePrimaryKey) {

        List<MetaDatabase> dbList = getDatabases();
        int dbCount = dbList.size();

        for ( int i = 0; i < dbCount; i++ ) {
            MetaDatabase db = dbList.get(i);

            if ( db.getName().equals(databaseName) ) {
                DatabaseConnection connection = getDatabaseConnection(db.getName());
                db.dropIndexesIfExist(connection, tableName, includePrimaryKey);
            }
        }
    }

    public void deleteRows(String databaseName, String tableName) {

        List<MetaDatabase> dbList = getDatabases();
        int dbCount = dbList.size();

        for ( int i = 0; i < dbCount; i++ ) {
            MetaDatabase db = dbList.get(i);

            if ( db.getName().equals(databaseName) ) {
                DatabaseConnection connection = getDatabaseConnection(db.getName());
                db.deleteRows(connection, tableName);
            }
        }

    }

    // Static Utility Methods

    public static DBResults createDataSetFromSELECTParams(String serverName, String databaseName, String columnNames, String tableName, String whereClause, String groupBy, String orderBy ) {

        StringBuilder query = new StringBuilder();

        query.append("SELECT ").append(columnNames).append(" ");
        query.append("FROM ").append(tableName).append(" ");
        if ( whereClause != null ) {
            query.append("WHERE ").append(whereClause).append(" ");
        }
        if ( groupBy != null ) {
            query.append("GROUP BY ").append(groupBy).append(" ");
        }
        if ( orderBy != null ) {
            query.append("ORDER BY ").append(orderBy).append(" ");
        }

        return createDataSetFromQuery(serverName, databaseName, query.toString());
    }

    public static DBResults createDataSetFromQuery(String serverName, String databaseName, String query) {

        DatabaseServer dbServer = null;

        try {
            dbServer = ServerManager.getServer(serverName);
        } catch (Throwable e) {
            e.printStackTrace();
            return new DBResults(e);
        }
        if ( dbServer == null ) {
            return new DBResults("Database Server [" + serverName + "] does not exists");
        }

        DatabaseConnection dbConnection = dbServer.getDatabaseConnection(databaseName);

        if ( dbConnection == null ) {
            return new DBResults("Database Connection to [" + databaseName + "] didn't return instance");
        }

        TSDataSet dataset = null;

        try {
            // Acquire database connection for current thread
            dbConnection.connect();

            Connection conn = dbConnection.getConnection();

            try {
                dataset = TSDataSetFactory.createDataSetFromQuery(conn, query);
            } catch ( Exception e ) {
                e.printStackTrace();
                return new DBResults(e);
            }

        } catch (Throwable e) {
            e.printStackTrace();
            return new DBResults(e);
        } finally {
            // Release connection for current thread
            dbConnection.disconnect();
        }

        return new DBResults(dataset);

    }

    public static DBResults createDataSetFromDBMetaData(String serverName, String databaseName, String tableName) {

        DatabaseServer dbServer = null;

        try {
            dbServer = ServerManager.getServer(serverName);
        } catch (Throwable e) {
            e.printStackTrace();
            return new DBResults(e);
        }
        if ( dbServer == null ) {
            return new DBResults("Database Server [" + serverName + "] does not exists");
        }

        DatabaseConnection dbConnection = dbServer.getDatabaseConnection(databaseName);

        if ( dbConnection == null ) {
            return new DBResults("Database Connection to [" + databaseName + "] didn't return instance");
        }

        TSDataSet dataSet = null;

        IntColumn indexColumn = IntColumn.create("Index");
        StringColumn nameColumn = StringColumn.create("Column Name");
        StringColumn columnTypeColumn = StringColumn.create("Column Type");
        IntColumn sizeColumn = IntColumn.create("Size");
        StringColumn nullableColumn = StringColumn.create("Nullable");

        Column[] colObjects = new Column[] { indexColumn, nameColumn, columnTypeColumn, sizeColumn, nullableColumn } ;
        String[] colNames = new String[] { "Index", "Column Name", "Column Type", "Size", "Nullable" };
        Class[] colTypes = new Class[] { Integer.class, String.class, String.class, Integer.class, String.class };

        try {
            // Acquire database connection for current thread
            dbConnection.connect();

            Connection conn = dbConnection.getConnection();

            DatabaseMetaData dbMetaData = conn.getMetaData();

            ResultSet rs = dbMetaData.getColumns(databaseName.toUpperCase(), null, tableName.toUpperCase(), null);
//            dataSet = TSDataSetFactory.createDataSetFromResultSet(rs);

            int rowCount = 0;
            while ( rs.next() ) {
                indexColumn.append(rowCount);
                nameColumn.append(rs.getString("COLUMN_NAME"));
                columnTypeColumn.append(rs.getString("TYPE_NAME"));
                sizeColumn.append(rs.getInt("COLUMN_SIZE"));
                nullableColumn.append(rs.getString("IS_NULLABLE"));

                rowCount++;
            }

        } catch (Throwable e) {
            e.printStackTrace();
            return new DBResults(e);
        } finally {
            // Release connection for current thread
            dbConnection.disconnect();
        }

        TSDataSetMeta meta = new TSDataSetMeta(colObjects, colNames, colTypes);
        dataSet = new TSDataSet(meta);

        return new DBResults(dataSet);

    }

    public static class DBResults {

        public boolean success = false;
        public TSDataSet results = null;
        public Throwable errorException = null;
        public String errorMessage = null;

        public DBResults(TSDataSet results) {
            this.success = true;
            this.results = results;
        }

        public DBResults(Throwable errorException) {
            this.success = false;
            this.errorException = errorException;

            this.results = TSDataSetFactory.createSampleDataSet(new String[]{"Error Message"}, new Class[]{String.class});
            this.results.addRow(new Object[] { errorException.getMessage() });
        }

        public DBResults(String errorMessage) {
            this.success = false;
            this.errorMessage = errorMessage;

            this.results = TSDataSetFactory.createSampleDataSet(new String[]{"Error Message"}, new Class[]{String.class});
            this.results.addRow(new Object[] { errorMessage });
        }

    }

}
