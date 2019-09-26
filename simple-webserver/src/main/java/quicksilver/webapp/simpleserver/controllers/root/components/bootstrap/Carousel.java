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

package quicksilver.webapp.simpleserver.controllers.root.components.bootstrap;

import quicksilver.webapp.simpleui.bootstrap4.components.BSCarousel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSText;
import quicksilver.webapp.simpleui.html.components.HTMLHeading;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import quicksilver.webapp.simpleui.html.components.HTMLThematicBreak;

public class Carousel extends AbstractComponentsBootstrapPage {

    public Carousel() {
        getSideBar().setActiveItem("Carousel");
    }

    protected BSPanel createContentPanelCenter() {

        BSPanel panel = new BSPanel();

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Example Carousel - Slides Only", 4));
        panel.add(new HTMLThematicBreak());

        // Carousel - Slides only
        panel.add(new BSCarousel());

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("With controls", 4));
        panel.add(new HTMLThematicBreak());

        // Carousel - With controls

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("With indicators", 4));
        panel.add(new HTMLThematicBreak());

        // Carousel - With indicators

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("With captions", 4));
        panel.add(new HTMLThematicBreak());

        // Carousel - With captions

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Crossfades", 4));
        panel.add(new HTMLThematicBreak());

        // Carousel - Crossfades


        panel.add(new BSCarousel());

        return panel;
    }

}
