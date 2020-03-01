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
import quicksilver.commons.app.SimpleWebServer;
import quicksilver.webapp.simpleserver.controllers.root.About;
import quicksilver.webapp.simpleserver.controllers.root.about.Contact;
import quicksilver.webapp.simpleserver.controllers.root.about.Project;
import quicksilver.webapp.simpleserver.controllers.root.about.Team;
import quicksilver.webapp.simpleserver.controllers.root.components.bootstrap.Bootstrap;
import quicksilver.webapp.simpleserver.controllers.root.Index;
import quicksilver.webapp.simpleserver.controllers.root.Search;
import quicksilver.webapp.simpleserver.controllers.root.components.explorer.Explorer;
import quicksilver.webapp.simpleserver.controllers.root.components.charts.*;
import quicksilver.webapp.simpleserver.controllers.root.components.customforms.CustomForms;
import quicksilver.webapp.simpleserver.controllers.root.components.explorer.Explorer2;
import quicksilver.webapp.simpleserver.controllers.root.components.extras.Extras;
import quicksilver.webapp.simpleserver.controllers.root.components.bootstrap.*;
import quicksilver.webapp.simpleserver.controllers.root.components.tables.Tables;
import quicksilver.webapp.simpleui.HtmlPage;
import quicksilver.webapp.simpleui.HtmlStream;
import quicksilver.webapp.simpleui.HtmlStreamStringBuffer;

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
        webServer.get("/about/project", (request, response) -> {
            return renderPageAndReturnStream(new Project(), new HtmlStreamStringBuffer()).getText();
        });
        webServer.get("/about/team", (request, response) -> {
            return renderPageAndReturnStream(new Team(), new HtmlStreamStringBuffer()).getText();
        });
        webServer.get("/about/contact", (request, response) -> {
            return renderPageAndReturnStream(new Contact(), new HtmlStreamStringBuffer()).getText();
        });

        webServer.get("/search", (request, response) -> {
            return renderPageAndReturnStream(new Search(), new HtmlStreamStringBuffer()).getText();
        });

        // Components Pages
        webServer.get("/components/bootstrap", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new Bootstrap(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/extras", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new Extras(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/explorer", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new Explorer(request.queryMap()), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/explorer2", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new Explorer2(request.queryMap()), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/customforms", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new CustomForms(), new HtmlStreamStringBuffer());
            return stream.getText();
        });

        webServer.get("/components/bootstrap/alerts", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new Alerts(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/bootstrap/badges", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new Badges(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/bootstrap/breadcrum", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new Breadcrum(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/bootstrap/buttons", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new Buttons(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/bootstrap/buttongroup", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new ButtonGroup(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/bootstrap/card", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new Card(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/bootstrap/carousel", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new Carousel(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/bootstrap/collapse", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new Collapse(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/bootstrap/dropdowns", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new Dropdowns(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/bootstrap/forms", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new Forms(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/bootstrap/inputgroup", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new InputGroup(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/bootstrap/jumbotron", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new Jumbotron(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/bootstrap/listgroup", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new ListGroup(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/bootstrap/modal", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new Modal(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/bootstrap/navs", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new Navs(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/bootstrap/navbar", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new Navbar(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/bootstrap/pagination", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new Pagination(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/bootstrap/popovers", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new Popovers(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/bootstrap/progress", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new Progress(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/bootstrap/scrollspy", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new Scrollspy(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/bootstrap/tooltips", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new Tooltips(), new HtmlStreamStringBuffer());
            return stream.getText();
        });


        webServer.get("/components/charts", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new ChartsAll(), new HtmlStreamStringBuffer());
            return stream.getText();
        });

        webServer.get("/components/charts/area", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new ChartsArea(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/charts/bubble", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new ChartsBubble(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/charts/candlestick", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new ChartsCandlestick(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/charts/heatmap", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new ChartsHeatmap(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/charts/histogram", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new ChartsHistogram(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/charts/hbar", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new ChartsHBar(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/charts/line", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new ChartsLine(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/charts/ohlc", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new ChartsOHLC(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/charts/pie", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new ChartsPie(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/charts/scatter", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new ChartsScatter(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/charts/sunburst", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new ChartsSunburst(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/charts/timeseries", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new ChartsTimeseries(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/charts/treemap", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new ChartsTreemap(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/charts/vbar", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new ChartsVBar(), new HtmlStreamStringBuffer());
            return stream.getText();
        });
        webServer.get("/components/charts/calheatmap", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new ChartsCalHeatmap(), new HtmlStreamStringBuffer());
            return stream.getText();
        });


        webServer.get("/components/tables", (request, response) -> {
            HtmlStream stream = renderPageAndReturnStream(new Tables(), new HtmlStreamStringBuffer());
            return stream.getText();
        });

        webServer.get("/components/tables/export", (request, response) -> {
            return Tables.returnExportFile(request, response, true);
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
