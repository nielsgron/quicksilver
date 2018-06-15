/*
 * Copyright 2018 Niels Gron All Rights Reserved.
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

package org.apache.simplecommons.app;

import org.apache.commons.dbutils.DbUtils;
import org.apache.simplecommons.config.ConfigApplication;
import org.apache.simplecommons.config.ConfigDatabase;
import org.apache.simplecommons.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class SimpleApplication {

    protected DatabaseConnection dbConnection;

    private String applicationName;
    private String configFolderName;

    public SimpleApplication(String appName, String configFolder) {
        applicationName = appName;
        configFolderName = configFolder;
    }

    public void initializeDatabaseConnection (
            String defaultHost,
            String defaultPort,
            String defaultDatabase,
            String defaultUsername,
            String defaultPassword )
        throws SQLException {

        ConfigApplication configApplication = this.getConfigApplication();
        ConfigDatabase configDatabase = configApplication.getConfigDatabase();

        dbConnection = new DatabaseConnection(
                configDatabase.getHost(defaultHost),
                configDatabase.getPort(defaultPort),
                configDatabase.getDatabase(defaultDatabase),
                configDatabase.getUsername(defaultUsername),
                configDatabase.getPassword(defaultPassword)
        );

        // Test direct connection before we try to create a connection pool.
        // If connection fails, then it will throw exception and exit
        Connection conn = null;
        try {
            conn = dbConnection.testDirectJDBCConnection();
        } finally {
            DbUtils.closeQuietly(conn);
        }

    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getConfigFolderName() {
        return configFolderName;
    }

    public ConfigApplication getConfigApplication() {
        return new ConfigApplication();
    }

    public DatabaseConnection getDatabaseConnection() {
        return dbConnection;
    }

}
