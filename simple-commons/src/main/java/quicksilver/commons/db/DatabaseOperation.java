package quicksilver.commons.db;

import java.sql.Connection;

public abstract class DatabaseOperation {

    private DatabaseConnection dbConnection;

    public DatabaseOperation(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void run() throws Exception {
        // Acquire database connection for current thread
        dbConnection.connect();

        try {
            // Execute operation with current connection
            executeOperation(dbConnection.getConnection());
        } finally {
            // Release connection for current thread
            dbConnection.disconnect();
        }
    }

    public abstract void executeOperation(Connection connection);

}
