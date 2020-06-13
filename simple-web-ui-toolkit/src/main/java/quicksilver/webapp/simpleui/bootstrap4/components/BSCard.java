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
package quicksilver.webapp.simpleui.bootstrap4.components;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import quicksilver.webapp.simpleui.HtmlStream;
import quicksilver.webapp.simpleui.html.components.HTMLComponent;
import quicksilver.webapp.simpleui.html.components.HTMLDiv;
import quicksilver.webapp.simpleui.html.components.HTMLHeading;
import quicksilver.webapp.simpleui.html.components.HTMLParagraph;
import quicksilver.webapp.simpleui.html.components.HTMLText;

/**
 *
 * Example :
 *
 * <div class="card" style="width: 18rem;">
 * <img class="card-img-top" src=".../100px180/" alt="Card image cap">
 * <div class="card-body">
 * <h5 class="card-title">Card title</h5>
 * <p class="card-text">Some quick example text to build on the card title and
 * make up the bulk of the card's content.</p>
 * <a href="#" class="btn btn-primary">Go somewhere</a>
 * </div>
 * </div>
 *
 * @see
 * <a href='https://www.w3schools.com/bootstrap4/bootstrap_cards.asp'>W3Schools</a>
 * @see <a href='https://getbootstrap.com/docs/4.1/components/card/'>Bootstrap
 * Docs</a>
 */
public class BSCard extends BSComponentContainer {

    private @Nullable
    String style;

    public BSCard() {
    }

    public BSCard(@NonNull HTMLComponent body, @NonNull String header) {
        header(header);
        body(body);
    }

    /**
     * @param title
     * @param subTitle
     * @param text
     * @param links pairs of link / link text, eg. ["#", "anchor text",
     * "http://example.com", "The example site"]
     */
    public BSCard body(@Nullable String title, @Nullable String subTitle, @NonNull String text, String... links) {
        add(new CardBody(title, subTitle, text, links));
        return this;
    }

    public BSCard image(@NonNull String imageURL, @NonNull String imageAlt) {
        add(new CardImageTop(imageURL, imageAlt));
        return this;
    }

    @Override
    protected void defineAttributes() {
        putComponentAttribute(COMPONENT_ATTRIB_NAME, "BSCard");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "div");

        addTagAttribute("class", getClassNames());
        if (style != null) {
            addTagAttribute("style", style);
        }
    }

    @Override
    protected String getClassNames() {
        return "card";
    }

    public BSCard body(String text) {
        return body(new CardText(new BSText(text)));
    }

    public BSCard body(HTMLComponent... components) {
        add(new CardBody(components));
        return this;
    }

    public BSCard style(String s) {
        setStyle(s);
        return this;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public BSCard listGroup(String... list) {
        add(new ListBody(list));
        return this;
    }

    public BSCard header(@NonNull String header) {
        add(new CardHeader(header));
        return this;
    }

    public BSCard footer(@NonNull String footer) {
        add(new CardFooter(footer));
        return this;
    }

    private static class CardBody extends HTMLDiv {

        public CardBody() {
            super("card-body");
        }

        public CardBody(HTMLComponent... components) {
            this();
            for (HTMLComponent c : components) {
                add(c);
            }
        }

        public CardBody(@Nullable String title, @Nullable String subTitle, @NonNull String text, String... links) {
            this();
            if (title != null) {
                add(new CardTitle(title));
            }
            if (subTitle != null) {
                add(new CardSubtitle(subTitle));
            }
            add(new CardText(new BSText(text)));
            for (int i = 0; i < links.length; i += 2) {
                add(new CardLink(links[i + 1], links[i]));
            }
        }

    }

    private static class CardHeader extends HTMLDiv {

        public CardHeader(String header) {
            super("card-header");
            add(new HTMLText(header));
        }
    }

    private static class CardFooter extends HTMLDiv {

        public CardFooter(String header) {
            super("card-footer text-muted");
            add(new HTMLText(header));
        }
    }

    private static class ListBody extends HTMLComponent {

        private final String[] list;

        public ListBody(String... list) {
            this.list = list;
        }

        protected void defineAttributes() {

        }

        @Override
        public void render(HtmlStream stream) {
            stream.writeln(" <ul class=\"list-group list-group-flush\">");
            for (String s : list) {
                stream.write("<li class=\"list-group-item\">");
                //TODO: escape HTML
                stream.write(s);
                stream.writeln("</li>");
            }
            stream.writeln("</ul>");
        }
    }

    private static class CardImageTop extends BSImage {

        public CardImageTop(String url, String alt) {
            super(url, alt);
        }

        @Override
        protected String getClassNames() {
            return "card-img-top";
        }
    }

    private static class CardTitle extends HTMLHeading {

        public CardTitle(String text) {
            super(text, 5);
        }

        @Override
        protected String getClassNames() {
            return "card-title";
        }
    }

    private static class CardSubtitle extends HTMLHeading {

        public CardSubtitle(String text) {
            super(text, 6);
        }

        @Override
        protected String getClassNames() {
            return "card-subtitle mb-2 text-muted";
        }
    }

    private static class CardText extends HTMLParagraph {

        private CardText(HTMLComponent child) {
            add(child);
        }

        @Override
        protected String getClassNames() {
            return "card-text";
        }
    }

    private static class CardLink extends BSHyperlink {

        public CardLink(String text, String url) {
            super(text, url);
        }

        @Override
        protected String getClassNames() {
            return "card-link";
        }
    }

}
