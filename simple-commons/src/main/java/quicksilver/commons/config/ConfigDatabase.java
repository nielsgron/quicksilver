/*
 * Copyright 2018 Niels Gron and Contributors All Rights Reserved.
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

package quicksilver.commons.config;

import java.io.File;

public class ConfigDatabase extends SimpleConfig {

    private String DEFAULT_DATABASE_HOST = "localhost";
    private String DEFAULT_DATABASE_PORT = "3306";
    private String DEFAULT_DATABASE_DBNAME = "mysql";
    private String DEFAULT_DATABASE_USERNAME = "root";
    private String DEFAULT_DATABASE_PASSWORD = "password";

    private String PROP_DATABASE_HOST = "database.host";
    private String PROP_DATABASE_PORT = "database.port";
    private String PROP_DATABASE_NAME = "database.name";
    private String PROP_DATABASE_USERNAME = "database.username";
    private String PROP_DATABASE_PASSWORD = "database.password";

    public ConfigDatabase(File configFile) {
        super(configFile);
        addSections();
    }

    public ConfigDatabase(File configFile, String host, String port, String dbname, String username, String password) {
        super(configFile);

        DEFAULT_DATABASE_HOST = host;
        DEFAULT_DATABASE_PORT = port;
        DEFAULT_DATABASE_DBNAME = dbname;
        DEFAULT_DATABASE_USERNAME = username;
        DEFAULT_DATABASE_PASSWORD = password;

        addSections();
    }

    private void addSections() {

        addSection("database.", "Database Server Properties");

    }

    public String getHost(String defaultHost) {
        return getPropertyOrSetDefault(PROP_DATABASE_HOST, defaultHost);
    }

    public String getPort(String defaultPort) {
        return getPropertyOrSetDefault(PROP_DATABASE_PORT, defaultPort);
    }

    public String getDatabase(String defaultDatabase) {
        return getPropertyOrSetDefault(PROP_DATABASE_NAME, defaultDatabase);
    }

    public String getUsername(String defaultUsername) {
        return getPropertyOrSetDefault(PROP_DATABASE_USERNAME, defaultUsername);
    }

    public String getPassword(String defaultPassword) {
        return getPropertyOrSetDefault(PROP_DATABASE_PASSWORD, defaultPassword);
    }

    public void setDefaultPropertiesAndValues() {

        put(PROP_DATABASE_HOST, DEFAULT_DATABASE_HOST);
        put(PROP_DATABASE_PORT, DEFAULT_DATABASE_PORT);
        put(PROP_DATABASE_NAME, DEFAULT_DATABASE_DBNAME);
        put(PROP_DATABASE_USERNAME, DEFAULT_DATABASE_USERNAME);
        put(PROP_DATABASE_PASSWORD, DEFAULT_DATABASE_PASSWORD);

    }

}
