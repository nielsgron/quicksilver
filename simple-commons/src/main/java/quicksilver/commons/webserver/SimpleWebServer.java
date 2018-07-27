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

package quicksilver.commons.webserver;

import quicksilver.commons.app.SimpleApplication;
import spark.Service;

public class SimpleWebServer {

    protected final Service webServer = Service.ignite();

    protected SimpleApplication application;

    public SimpleWebServer(SimpleApplication app) {

        application = app;

        initServer();
        initRoutes();
        initFilters();

    }

    protected void initServer() {

        webServer.staticFiles.location("/public");

        int port = Service.SPARK_DEFAULT_PORT;
        int maxThreads = 15;
        int minThreads = 2;
        int timeOutMillis = 30000;

        webServer.port(port);
        webServer.threadPool(maxThreads, minThreads, timeOutMillis);

    }

    protected void initRoutes() {

    }

    protected void initFilters() {

    }

    protected SimpleApplication getApplication() {
        return application;
    }

    public void startServer() {
        webServer.init();
    }

}
