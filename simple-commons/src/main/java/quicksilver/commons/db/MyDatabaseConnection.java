package quicksilver.commons.db;

import org.javalite.activejdbc.Model;

import java.sql.Connection;

public class MyDatabaseConnection extends DatabaseConnection {

    private static String mysqlDriverClass = "com.mysql.cj.jdbc.Driver";

    static {
        try {
            Class.forName(mysqlDriverClass);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public MyDatabaseConnection(String host, String port, String database, String userName, String password) {
        super(host, port, database, userName, password);
    }

    protected String getURL() {
        return "jdbc:mysql://" + _host + ":" + _port + "/" + _database + "?verifyServerCertificate=false&useSSL=true&requireSSL=true&serverTimezone=UTC&useServerPrepStmts=false&rewriteBatchedStatements=true";
    }

    public BulkImport createBulkImport(Connection connection, Model dbObject, int batchSize) {
        return new MyBulkImport(connection, dbObject, batchSize);
    }

    public BulkImport createBulkImport(Connection connection, Model dbObject, int batchSize, boolean merge, String keyColumns) {
        return new MyBulkImport(connection, dbObject, batchSize, merge);
    }

}
