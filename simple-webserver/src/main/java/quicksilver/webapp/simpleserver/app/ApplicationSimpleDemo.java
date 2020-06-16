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

package quicksilver.webapp.simpleserver.app;

import quicksilver.commons.app.SimpleApplication;
import quicksilver.commons.app.SimpleWebApplication;

import java.io.File;

public class ApplicationSimpleDemo extends SimpleWebApplication {

    private static String APPLICATION_NAME = "Simple Demo";
    private static String APPLICATION_CONFIG_FOLDER = ".simpledemo";

    private static String DEFAULT_DB_HOST = "localhost";
    private static String DEFAULT_DB_PORT = "3306";
    private static String DEFAULT_DB_DBNAME = "project_simpledemo";
    private static String DEFAULT_DB_USERNAME = "root";
    private static String DEFAULT_DB_PASSWORD = "password";

    public static void main(String[] args) {
        // Create the application Simple Demo
        SimpleApplication application = new ApplicationSimpleDemo(APPLICATION_NAME, APPLICATION_CONFIG_FOLDER);

        if ("true".equals(application.getConfigApplication().getProperty("quicksilver.autoredeploy"))) {
            FSWatcher.launch();
        }

        // Initialize database connection pool and test if connection can be established.  If it fails, close application.
        initializeDatabaseOrExitApplication(application, true);

        // Start Job Manager
        //JobManager.getInstance().startTask(new JobSynchRepo(application.getDatabaseConnection()));

        // Create a web server to run the CodeMonster application and start it
        WebServerSimpleDemo webServer = new WebServerSimpleDemo(application);
        webServer.startServer();

    }

    public ApplicationSimpleDemo(String appName, String configFolder) {
        super(appName, configFolder);
    }

    protected void setDefaultConfigDatabase() {
        // Override method and set default database options
        configDatabase.getHost(DEFAULT_DB_HOST);
        configDatabase.getPort(DEFAULT_DB_PORT);
        configDatabase.getDatabase(DEFAULT_DB_DBNAME);
        configDatabase.getUsername(DEFAULT_DB_USERNAME);
        configDatabase.getPassword(DEFAULT_DB_PASSWORD);

        File configFile = configDatabase.getConfigFile();
        if ( !configFile.exists() ) {
            configDatabase.save();
        }

    }

    public boolean isSchedulerEnabled() {
        return false;
    }

    private static void initializeDatabaseOrExitApplication(SimpleApplication app, boolean skipInitialization) {

        if ( skipInitialization ) {
            return;
        }

        try {
            app.initializeDatabaseConnection();
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }

    }

}
