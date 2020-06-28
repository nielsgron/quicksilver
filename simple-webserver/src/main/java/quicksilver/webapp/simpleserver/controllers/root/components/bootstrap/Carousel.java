/*
 * Copyright 2018-2020 Niels Gron and Contributors All Rights Reserved.
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
import quicksilver.webapp.simpleui.bootstrap4.components.BSHeading;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSText;
import quicksilver.webapp.simpleui.bootstrap4.components.BSImage;
import quicksilver.webapp.simpleui.html.components.HTMLHeading;
import quicksilver.webapp.simpleui.html.components.HTMLLineBreak;
import quicksilver.webapp.simpleui.html.components.HTMLThematicBreak;

public class Carousel extends AbstractComponentsBootstrapPage {

    public Carousel() {
        getSideBar().setActiveItem("Carousel");
    }

    @Override
    protected BSPanel createContentPanelCenter() {

        BSPanel panel = new BSPanel();

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Example Carousel - Slides Only", 4));
        panel.add(new HTMLThematicBreak());

        // Carousel - Slides only
        {
            BSCarousel c = new BSCarousel();
            BSImage img1 = new BSImage("/image?w=800&h=400&bg=777777&fg=555555&text=First slide", "img1");
            c.add(img1);
            BSImage img2 = new BSImage("/image?w=800&h=400&bg=777777&fg=555555&text=Second slide", "img2");
            c.add(img2);
            BSImage img3 = new BSImage("/image?w=800&h=400&bg=777777&fg=555555&text=Third slide", "img3");
            c.add(img3);

            panel.add(c);
        }

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("With controls", 4));
        panel.add(new HTMLThematicBreak());

        // Carousel - With controls
        {
            BSCarousel c = new BSCarousel();
            BSImage img1 = new BSImage("/image?w=800&h=400&bg=777777&fg=555555&text=First slide", "img1");
            c.add(img1);
            BSImage img2 = new BSImage("/image?w=800&h=400&bg=777777&fg=555555&text=Second slide", "img2");
            c.add(img2);
            BSImage img3 = new BSImage("/image?w=800&h=400&bg=777777&fg=555555&text=Third slide", "img3");
            c.add(img3);

            c.setId("withControls");

            panel.add(c.withControls());
        }

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("With indicators", 4));
        panel.add(new HTMLThematicBreak());

        // Carousel - With indicators
        {
            BSCarousel c = new BSCarousel();
            BSImage img1 = new BSImage("/image?w=800&h=400&bg=777777&fg=555555&text=First slide", "img1");
            c.add(img1);
            BSImage img2 = new BSImage("/image?w=800&h=400&bg=777777&fg=555555&text=Second slide", "img2");
            c.add(img2);
            BSImage img3 = new BSImage("/image?w=800&h=400&bg=777777&fg=555555&text=Third slide", "img3");
            c.add(img3);

            c.setId("withControlsAndIndicators");

            panel.add(c
                    .withControls()
                    .withIndicators());
        }

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("With captions", 4));
        panel.add(new HTMLThematicBreak());

        // Carousel - With captions
        {
            BSCarousel c = new BSCarousel();
            BSImage img1 = new BSImage("/image?w=800&h=400&bg=777777&fg=555555&text=First slide", "img1");
            c.add(new BSCarousel.Item(img1)
                    .withCaption(new BSHeading("First slide heading", 5), new BSText("Explaining some more about the 1st slide")));
            BSImage img2 = new BSImage("/image?w=800&h=400&bg=777777&fg=555555&text=Second slide", "img2");
            c.add(new BSCarousel.Item(img2)
                    .active(true)
                    .withCaption(new BSHeading("Second slide heading", 5), new BSText("Explaining some more about the 2nd slide")));
            BSImage img3 = new BSImage("/image?w=800&h=400&bg=777777&fg=555555&text=Third slide", "img3");
            c.add(new BSCarousel.Item(img3)
                    .withCaption(new BSHeading("Third slide heading", 5), new BSText("Explaining some more about the 3rd slide")));

            c.setId("withControlsAndIndicatorsAndCaptions");

            panel.add(c
                    .withControls()
                    .withIndicators());
        }

        panel.add(new HTMLLineBreak(2));
        panel.add(new HTMLHeading("Crossfades", 4));
        panel.add(new HTMLThematicBreak());

        // Carousel - Crossfades
        {
            BSCarousel c = new BSCarousel();
            c.setCrossfade(true);
            BSImage img1 = new BSImage("/image?w=800&h=400&bg=777777&fg=555555&text=First slide", "img1");
            c.add(img1);
            BSImage img2 = new BSImage("/image?w=800&h=400&bg=777777&fg=555555&text=Second slide", "img2");
            c.add(img2);
            BSImage img3 = new BSImage("/image?w=800&h=400&bg=777777&fg=555555&text=Third slide", "img3");
            c.add(img3);

            c.setId("withFade");

            panel.add(c.withControls());
        }

        panel.add(new HTMLLineBreak(2));

        return panel;
    }

}
