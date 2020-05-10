package quicksilver.commons.db;

import org.javalite.activejdbc.Model;

import java.sql.Connection;

public class H2DatabaseConnection extends DatabaseConnection {

    private static String h2DriverClass = "org.h2.Driver";

    static {
        try {
            Class.forName(h2DriverClass);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public H2DatabaseConnection(String host, String port, String database, String userName, String password) {
        super(host, port, database, userName, password);
    }

    protected String getURL() {
        if ( _port != null && _port.length() > 0 ) {
            return "jdbc:h2:" + _host + ":" + _port;
        } else {
            return "jdbc:h2:" + _host;
        }
    }

    public BulkImport createBulkImport(Connection connection, Model dbObject, int batchSize) {
        return new BulkImport(connection, dbObject, batchSize);
    }

    public BulkImport createBulkImport(Connection connection, Model dbObject, int batchSize, boolean merge, String keyColumns) {
        return new BulkImport(connection, dbObject, batchSize, merge, keyColumns);
    }

}
