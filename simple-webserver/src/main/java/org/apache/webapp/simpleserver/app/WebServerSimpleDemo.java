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

package org.apache.webapp.simpleserver.app;

import org.apache.simplecommons.app.SimpleApplication;
import org.apache.simplecommons.webserver.SimpleWebServer;
import org.apache.webapp.simpleserver.controllers.root.About;
import org.apache.webapp.simpleserver.controllers.root.Components;
import org.apache.webapp.simpleserver.controllers.root.Index;
import org.apache.webapp.simpleserver.controllers.root.Search;
import org.apache.webapp.simpleui.HtmlPage;
import org.apache.webapp.simpleui.HtmlStream;
import org.apache.webapp.simpleui.HtmlStreamStringBuffer;

import java.sql.Connection;

public class WebServerSimpleDemo  extends SimpleWebServer {

    public WebServerSimpleDemo(SimpleApplication app) {
        super(app);
    }

    protected void initRoutes() {

        // Home Page
        webServer.get("/", (request, response) -> {

            // Database connection
            //getApplication().getDatabaseConnection().connect();
            //Connection conn = getApplication().getDatabaseConnection().getConnection();

            // HTML Page Generation (Session, Login, Connection)
            HtmlStream stream = renderPageAndReturnStream(new Index(null), new HtmlStreamStringBuffer());

            // Database disconnect
            //getApplication().getDatabaseConnection().disconnect();

            return stream.getText();

        });

        // Top level site pages
        webServer.get("/about", (request, response) -> {
            return renderPageAndReturnStream(new About(), new HtmlStreamStringBuffer()).getText();
        });
        webServer.get("/search", (request, response) -> {
            return renderPageAndReturnStream(new Search(), new HtmlStreamStringBuffer()).getText();
        });

        // Components Pages
        webServer.get("/components", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new Components(), new HtmlStreamStringBuffer());
            return stream.getText();
        });

    }

    protected void initFilters() {

    }

    private HtmlStream renderPageAndReturnStream(HtmlPage page, HtmlStream stream) {
        try {
            page.render(stream);
        } catch (Throwable t) {
            t.printStackTrace();
            throw t;
        }
        return stream;
    }

}
