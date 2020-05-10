package quicksilver.commons.db.metadata;

import quicksilver.commons.db.DatabaseConnection;
import quicksilver.commons.db.H2DatabaseConnection;

import java.io.File;
import java.util.HashMap;

public class H2DatabaseServer extends DatabaseServer {

    private HashMap<String, DatabaseConnection> connectionMap = new HashMap<String, DatabaseConnection>();

    private String localHost;

    public H2DatabaseServer(String serverName) {
        this(serverName, null, null, null, null, null);
    }

    public H2DatabaseServer(String serverName, String host, String port, String database, String userName, String password) {
        super(serverName, host, port, database, userName, password);

        if ( getHost() == null ) {
            localHost = System.getProperty("user.home") + File.separator + "databases" + File.separator + getServerName();
        } else {
            localHost = getHost();
        }

    }

    public synchronized DatabaseConnection getDatabaseConnection(String databaseName) {

        DatabaseConnection connection = connectionMap.get(databaseName);

        if ( connection == null ) {

            String hostPath = localHost + File.separator + databaseName;

            connection = new H2DatabaseConnection(hostPath, null, databaseName, null, null);

            connectionMap.put(databaseName, connection);
        }

        return connection;
    }

}
