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

package quicksilver.commons.config;

import spark.Service;

import java.io.File;

public class ConfigWebServer extends SimpleConfig {

    public static String DEFAULT_WEBSERVER_PORT = String.valueOf(Service.SPARK_DEFAULT_PORT);
    public static String DEFAULT_WEBSERVER_MAX_THREADS = "100";
    public static String DEFAULT_WEBSERVER_MIN_THREADS = "2";
    public static String DEFAULT_WEBSERVER_TIMEOUT = "30000";

    private static String PROP_WEBSERVER_PORT = "webserver.port";
    private static String PROP_WEBSERVER_MAX_THREADS = "webserver.max_threads";
    private static String PROP_WEBSERVER_MIN_THREADS = "webserver.min_threads";
    private static String PROP_WEBSERVER_TIMEOUT = "webserver.timeout";

    public ConfigWebServer(File configFile) {
        super(configFile);
    }

    public ConfigWebServer(File configFile, String port, String maxThreads, String minThreads, String timeout) {
        super(configFile);

        DEFAULT_WEBSERVER_PORT = port;
        DEFAULT_WEBSERVER_MAX_THREADS = maxThreads;
        DEFAULT_WEBSERVER_MIN_THREADS = minThreads;
        DEFAULT_WEBSERVER_TIMEOUT = timeout;

        addSections();
    }

    private void addSections() {

        addSection("webserver.", "Web Server Properties");

    }

    public String getPort(String defaultPort) {
        return getPropertyOrSetDefault(PROP_WEBSERVER_PORT, defaultPort);
    }

    public String getMaxThreads(String defaultMaxThreads) {
        return getPropertyOrSetDefault(PROP_WEBSERVER_MAX_THREADS, defaultMaxThreads);
    }

    public String getMinThreads(String defaultMinThreads) {
        return getPropertyOrSetDefault(PROP_WEBSERVER_MIN_THREADS, defaultMinThreads);
    }

    public String getTimeOut(String defaultTimeOut) {
        return getPropertyOrSetDefault(PROP_WEBSERVER_TIMEOUT, defaultTimeOut);
    }

    public void setDefaultPropertiesAndValues() {

        put(PROP_WEBSERVER_PORT, DEFAULT_WEBSERVER_PORT);
        put(PROP_WEBSERVER_MAX_THREADS, DEFAULT_WEBSERVER_MAX_THREADS);
        put(PROP_WEBSERVER_MIN_THREADS, DEFAULT_WEBSERVER_MIN_THREADS);
        put(PROP_WEBSERVER_TIMEOUT, DEFAULT_WEBSERVER_TIMEOUT);

    }

}
