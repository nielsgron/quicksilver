package quicksilver.commons.db;

import org.javalite.activejdbc.Model;

import java.sql.Connection;

public class MyBulkImport extends BulkImport {

    public MyBulkImport(Connection connection, Model dbObject, int batchSize) {
        super(connection, dbObject, batchSize);
    }

    public MyBulkImport(Connection connection, Model dbObject, int batchSize, boolean merge) {
        super(connection, dbObject, batchSize, merge, null);
    }

    protected String getOperationCommand() {
        if ( merge ) {
            return "REPLACE"; // H2 = MERGE ; MySQL = REPLACE
        } else {
            return "INSERT";
        }
    }

}
