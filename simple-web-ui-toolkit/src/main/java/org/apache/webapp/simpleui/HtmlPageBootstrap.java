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

package org.apache.webapp.simpleui;

import org.apache.webapp.simpleui.bootstrap4.components.*;

public abstract class HtmlPageBootstrap extends HtmlPage {

    private BSViewport viewport;

    public HtmlPageBootstrap() {
    }

    protected void initViewport() {
        viewport = createViewport();
        viewport.setNavbar(createNavbar());
        viewport.setContentPane(createContentPane());
    }

    protected BSViewport createViewport() {
        return new BSViewport();
    }

    protected BSComponentContainer createContentPane() {
        return new BSPanel();
    }

    protected BSNavbar createNavbar() {
        return new BSNavbar(false, "");
    }

    public BSViewport getViewport() {
        if ( viewport == null ) {
            initViewport();
        }
        return viewport;
    }
    public BSNavbar getNavbar() {
        return getViewport().getNavbar();
    }
    public BSComponentContainer getContentPane() {
        return getViewport().getContentPane();
    }

    public void renderHEAD(HtmlStream stream) {

        super.renderHEAD(stream);

        // Need to write out HEAD items for Bootstrap
        stream.writeln("");

        stream.writeln("<meta charset=\"utf-8\">");
        stream.writeln("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">");

        stream.writeln("<title>" + getTitle() + "</title>");

        stream.writeln("<!-- Bootstrap core CSS -->");
        stream.writeln("<link href=\"/bootstrap-4.0/css/bootstrap.min.css\" rel=\"stylesheet\">");

//        stream.writeln("<!-- Custom styles for this template -->");
//        stream.writeln("<link href=\"starter-template.css\" rel=\"stylesheet\">");

    }

    public void renderBODY(HtmlStream stream) {

        // Render Navbar if one is set
        BSNavbar navBar = getViewport().getNavbar();
        if ( navBar != null ) {
            navBar.render(stream);
        }

        BSComponentContainer contentPane = getViewport().getContentPane();

        // Wrap the content pane in a Bootstrap container based on viewport options
        //stream.writeln("<div class=\"container\">");
        // Render the container
        contentPane.render(stream);
        // Close container wrapper
        stream.writeln("");
        stream.writeln("</div>");

        stream.writeln("<!-- Bootstrap core JavaScript");
        stream.writeln("================================================== -->");
        stream.writeln("<!-- Placed at the end of the document so the pages load faster -->");
        stream.writeln("<script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\" integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\" crossorigin=\"anonymous\"></script>");
        stream.writeln("<script>window.jQuery || document.write('<script src=\"../../../../assets/js/vendor/jquery.min.js\"><\\/script>')</script>");
        stream.writeln("<script src=\"/popper.js/popper.min.js\"></script>");
        stream.writeln("<script src=\"/bootstrap-4.0/js/bootstrap.min.js\"></script>");
        //stream.writeln("<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->");
        //stream.writeln("<script src=\"../../../../assets/js/ie10-viewport-bug-workaround.js\"></script>");

    }

}
