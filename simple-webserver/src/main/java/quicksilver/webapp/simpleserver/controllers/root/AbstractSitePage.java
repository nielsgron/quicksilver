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

package quicksilver.webapp.simpleserver.controllers.root;

import quicksilver.webapp.simpleserver.controllers.components.navs.NavSite;
import quicksilver.webapp.simpleui.HtmlPageBootstrapAppLayout;
import quicksilver.webapp.simpleui.bootstrap4.components.BSNavbar;

public class AbstractSitePage extends HtmlPageBootstrapAppLayout {

    public AbstractSitePage() {
        setBootstrapTheme(BootstrapTheme.DEFAULT);
        setCustomCSSURL("/application/demo-custom.css");
    }

//    NOTE : I could override this method to set my own custom theme path or set a pre-defined theme in constructor.
//    protected String getBootstrapBaseURI() {
//        return "/bootstrap-4.1-simplex";
//    }

//    Customize a CSS for styling such as the font size
//    protected String getCustomCSSURL() {
//        return "/application/demo-custom.css";
//    }

    protected BSNavbar createNavbar() {
        // Create a Navbar for the web site
        return new NavSite();
    }

}
