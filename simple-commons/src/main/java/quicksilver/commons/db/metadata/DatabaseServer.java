package quicksilver.commons.db.metadata;

import quicksilver.commons.db.DatabaseConnection;

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

    public void createTablesIfNotExist() {

        List<MetaDatabase> dbList = getDatabases();
        int dbCount = dbList.size();

        for ( int i = 0; i < dbCount; i++ ) {
            MetaDatabase db = dbList.get(i);

            DatabaseConnection connection = getDatabaseConnection(db.getName());

            db.createTablesIfNotExist(connection);
        }

    }

}
