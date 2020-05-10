/*
 * Copyright 2018-2019 Niels Gron and Contributors All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package quicksilver.commons.db;

import java.sql.Connection;

public abstract class DatabaseOperation {

    private DatabaseConnection dbConnection;

    public int recordCount;

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
