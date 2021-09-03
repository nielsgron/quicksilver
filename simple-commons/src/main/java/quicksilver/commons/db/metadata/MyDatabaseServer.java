package quicksilver.commons.db.metadata;

import quicksilver.commons.db.DatabaseConnection;
import quicksilver.commons.db.H2DatabaseConnection;
import quicksilver.commons.db.MyDatabaseConnection;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class MyDatabaseServer extends DatabaseServer {

    private HashMap<String, DatabaseConnection> connectionMap = new HashMap<String, DatabaseConnection>();

    private MyDatabaseConnection connection;

    public MyDatabaseServer(String name, String host, String port, String database, String userName, String password) {
        super(name, host, port, database, userName, password);
    }

    public synchronized DatabaseConnection getDatabaseConnection(String databaseName) {

        DatabaseConnection connection = connectionMap.get(databaseName);

        if ( connection == null ) {

            connection = new MyDatabaseConnection(getHost(), getPort(), databaseName, getUserName(), getPassword());

            connectionMap.put(databaseName, connection);
        }

        return connection;

//        if ( connection == null ) {
//            connection = new MyDatabaseConnection(getHost(), getPort(), databaseName, getUserName(), getPassword());
//        }
//
//        return connection;
    }

    public void createDatabasesIfNotExist() {

        List<MetaDatabase> dbList = getDatabases();
        int dbCount = dbList.size();

        DatabaseConnection connection = getDatabaseConnection("mysql");

        for ( int i = 0; i < dbCount; i++ ) {
            MetaDatabase db = dbList.get(i);

            String ddl = db.getCREATE_DDL();

            System.out.println("Execute: " + ddl);
            try {
                connection.execute(ddl);
            } catch ( Exception e ) {
                e.printStackTrace();
            }

        }

    }


}
