package quicksilver.commons.db.metadata;

import quicksilver.commons.db.DatabaseConnection;
import quicksilver.commons.db.H2DatabaseConnection;
import quicksilver.commons.db.MyDatabaseConnection;

public class MyDatabaseServer extends DatabaseServer {

    private DatabaseConnection connection;

    public MyDatabaseServer(String name, String host, String port, String database, String userName, String password) {
        super(name, host, port, database, userName, password);
    }

    public synchronized DatabaseConnection getDatabaseConnection(String databaseName) {

        if ( connection == null ) {
            connection = new MyDatabaseConnection(getHost(), getPort(), databaseName, getUserName(), getPassword());
        }

        return connection;
    }

}
