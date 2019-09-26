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

package quicksilver.commons.app;

import org.apache.commons.dbutils.DbUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import quicksilver.commons.config.*;
import quicksilver.commons.db.DatabaseConnection;
import quicksilver.commons.email.EmailService;
import quicksilver.commons.i18n.I18N;
import quicksilver.commons.scheduler.SchedulerService;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;

public class SimpleApplication {

    protected DatabaseConnection dbConnection;

    private String applicationName;
    private ApplicationHomePath applicationHomePath;

    protected ConfigApplication configApplication;
    protected ConfigDatabase configDatabase;
    protected ConfigEmailServer configEmailServer;
    protected ConfigLogger configLogger;
    protected ConfigScheduler configScheduler;
    protected ConfigWebServer configWebServer;

    public SimpleApplication(String appName, String appHomeFolder) {

        applicationName = appName;
        applicationHomePath = new ApplicationHomePath(appHomeFolder);

        // Initialize the Application Server
        initialize();

    }

    protected void initialize() {

        initializeConfigs();
        initializeLogger();
        initializeI18N();
        initializeEmailService();
        initializeSchedulerService();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                // Shutdown all services
                EmailService.shutdown();
                SchedulerService.shutdown();
            }
        });

    }

    protected void initializeConfigs() {

        configApplication = new ConfigApplication(new File(applicationHomePath.getConfigPath(), "config-application.properties"));
        setDefaultConfigApplication();

        configDatabase = new ConfigDatabase(new File(applicationHomePath.getConfigPath(), "config-database.properties"));
        setDefaultConfigDatabase();

        configEmailServer = new ConfigEmailServer(new File(applicationHomePath.getConfigPath(), "config-emailserver.properties"));
        setDefaultConfigEmailServer();

        configLogger = new ConfigLogger(applicationName, applicationHomePath.getAppFolderName(), applicationHomePath.getLogPath(), new File(applicationHomePath.getConfigPath(), "config-logger.properties"));
        setDefaultConfigLogger();

        configScheduler = new ConfigScheduler(new File(applicationHomePath.getConfigPath(), "config-scheduler.properties"));
        setDefaultConfigScheduler();

        configWebServer = new ConfigWebServer(new File(applicationHomePath.getConfigPath(), "config-webserver.properties"));
        setDefaultConfigWebServer();

    }

    protected void setDefaultConfigApplication() {
        setDefaultConfig(configApplication);
    }
    protected void setDefaultConfigDatabase() {
        setDefaultConfig(configDatabase);
    }
    protected void setDefaultConfigEmailServer() {
        setDefaultConfig(configEmailServer);
    }
    protected void setDefaultConfigLogger() {
        // We will check to see if there is a Log4j-2 config file in the config folder, and create one with defaults if it doesn't exist
        setDefaultConfig(configLogger);
        // We will now set the property for the log4j.configuration file if a Log4j-2 config file exists
        if ( configLogger.getConfigFile().exists() ) {
            String fullPath = configLogger.getConfigFile().getAbsolutePath();
            System.setProperty("log4j.configurationFile", fullPath);
            System.out.println("[Application::Logger] Setting System property [log4j.configurationFile] to : " + fullPath);
        } else {

        }
    }
    protected void setDefaultConfigScheduler() {
        setDefaultConfig(configScheduler);
    }
    protected void setDefaultConfigWebServer() {
        setDefaultConfig(configWebServer);
    }

    protected void setDefaultConfig(SimpleConfig config) {
        File configFile = config.getConfigFile();
        if ( !configFile.exists() ) {
            config.setDefaultPropertiesAndValues();
            config.save();
        }
    }

    public void initializeLogger() {

        // Enable debug of log4j2 configuration setup before initialization
        //System.setProperty("log4j2.debug", "true");

        // This is the first call to get a Log4j-2 logger, which will initialize it
        Logger LOG = LogManager.getLogger();

        String fullPath = configLogger.getConfigFile().getAbsolutePath();
        LOG.debug("Logger service started and configured with : " + fullPath);

    }

    public void initializeI18N() {

        I18N.initialize(Locale.ENGLISH, "quicksilver/commons/", "quicksilver-prompts", Locale.FRENCH, Locale.GERMAN);
    }

    public void initializeEmailService() {

        EmailService.createDefaultInstance(configEmailServer);
    }

    public void initializeSchedulerService() {

        SchedulerService.createDefaultInstance(configScheduler);
    }

    public void initializeDatabaseConnection ()
        throws SQLException {

        // Create Database Connection
        dbConnection = new DatabaseConnection(
                configDatabase.getHost(null),
                configDatabase.getPort(null),
                configDatabase.getDatabase(null),
                configDatabase.getUsername(null),
                configDatabase.getPassword(null)
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

    public DatabaseConnection getDatabaseConnection() {
        return dbConnection;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public ApplicationHomePath getApplicationHomePath() {
        return applicationHomePath;
    }

    public ConfigApplication getConfigApplication() {
        return configApplication;
    }
    public ConfigDatabase getConfigDatabase() {
        return configDatabase;
    }
    public ConfigEmailServer getConfigEmailServer() {
        return configEmailServer;
    }
    public ConfigLogger getConfigLogger() {
        return configLogger;
    }
    public ConfigScheduler getConfigScheduler() {
        return configScheduler;
    }
    public ConfigWebServer getConfigWebServer() {
        return configWebServer;
    }

    public void saveConfigs() {
        configApplication.save();
        configDatabase.save();
        configEmailServer.save();
        configLogger.save();
        configScheduler.save();
        configWebServer.save();
    }

}
