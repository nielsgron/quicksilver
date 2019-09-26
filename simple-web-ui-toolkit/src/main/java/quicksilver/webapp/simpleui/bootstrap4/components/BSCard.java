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

package quicksilver.webapp.simpleui.bootstrap4.components;

import quicksilver.webapp.simpleui.HtmlStream;
import quicksilver.webapp.simpleui.html.components.HTMLComponent;
import quicksilver.webapp.simpleui.html.components.HTMLText;

/*
    Example :

    <div class="card" style="width: 18rem;">
      <img class="card-img-top" src=".../100px180/" alt="Card image cap">
      <div class="card-body">
        <h5 class="card-title">Card title</h5>
        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
        <a href="#" class="btn btn-primary">Go somewhere</a>
      </div>
    </div>

    W3Schools : https://www.w3schools.com/bootstrap4/bootstrap_cards.asp
    Bootstrap Docs : https://getbootstrap.com/docs/4.1/components/card/
 */

public class BSCard extends BSComponent {

    private final HTMLComponent body;
    private HeaderBody header;
    private final String style;

    public BSCard(HTMLComponent body) {
        this(body, null);
    }

    public BSCard(HTMLComponent body, String header) {
        this(body, header, null);
    }

    public BSCard(HTMLComponent body, String header, String style) {
        this.body = body;
        this.style = style;
        if ( header != null ) {
            this.header = new HeaderBody(header);
        }
    }

    /**
     * @param title
     * @param subTitle
     * @param text
     * @param links    pairs of link / link text, eg. ["#", "anchor text", "http://example.com", "The example site"]
     */
    public BSCard(String title, String subTitle, HTMLComponent text, String... links) {
        this(null, null, title, subTitle, text, links);
    }

    protected BSCard(String imageURL, String imageAlt, String title, String subTitle, HTMLComponent text, String... links) {
        this(new TitleBody(imageURL, imageAlt, title, subTitle, text, links));
    }

    public BSCard(String title, String subTitle, String text, String... links) {
        this(title, subTitle, new HTMLText(text), links);
    }

    public BSCard(String imageURL, String imageAlt, HTMLComponent text) {
        this(imageURL, imageAlt, null, null, text);
    }

    private BSCard(HTMLComponent body, HeaderBody header, String style) {
        this.body = body;
        this.style = style;
        this.header = header;
    }

    protected void defineAttributes() {

    }

    @Override
    public void render(HtmlStream stream) {
        stream.write("<div class=\"card\"");
        if (style != null) {
            stream.write(" style=\"");
            stream.write(style);
            stream.write("\"");
        }
        stream.writeln(">");
        if ( header != null ) {
            header.render(stream);
        }
        stream.writeln("<div class=\"card-body\">");
        body.render(stream);
        stream.writeln("</div>");
        stream.write("</div>");
    }

    public BSCard style(String s) {
        return new BSCard(body, header, s);
    }


    public BSCard listGroup(String... list) {
        return new BSCard(new MultiBody(body, new ListBody(list)));
    }

    public BSCard header(String header) {
        return new BSCard(new MultiBody(new HeaderBody(header), body));
    }

    public BSCard buttons(BSButton... buttons) {
        return new BSCard(new MultiBody(body, new MultiBody(buttons)));
    }

    private static class HeaderBody extends HTMLComponent {
        private final String header;

        public HeaderBody(String header) {
            this.header = header;
        }

        protected void defineAttributes() {

        }

        @Override
        public void render(HtmlStream stream) {
            stream.writeln("<div class=\"card-header\">");
            //TODO: escape HTML
            stream.writeln(header);
            stream.writeln("</div>");
        }
    }

    private static class MultiBody extends HTMLComponent {
        private final HTMLComponent[] children;

        MultiBody(HTMLComponent... children) {
            this.children = children;
        }

        protected void defineAttributes() {

        }

        @Override
        public void render(HtmlStream stream) {
            for (HTMLComponent child : children) {
                if (child == null) {
                    continue;
                }
                child.render(stream);
            }
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

    private static class TitleBody extends HTMLComponent {
        private final String title;
        private final String subTitle;
        private final HTMLComponent text;
        private final String[] links;
        private final String imageURL;
        private final String imageAlt;

        public TitleBody(String imageURL, String imageAlt, String title, String subTitle, HTMLComponent text, String... links) {
            this.imageURL = imageURL;
            this.imageAlt = imageAlt;
            this.title = title;
            this.subTitle = subTitle;
            this.text = text;
            this.links = links;
        }

        protected void defineAttributes() {

        }

        @Override
        public void render(HtmlStream stream) {
            if (imageURL != null) {
                stream.write("<img class=\"card-img-top\" src=\"");
                //TODO: escape quotes?
                stream.write(imageURL);
                stream.write("\"");
                if (imageAlt != null) {
                    stream.write(" alt=\"");
                    stream.write(imageAlt);
                    stream.write("\"");
                }
                stream.writeln(">");
            }
            if (title != null) {
                stream.write("<h5 class=\"card-title\">");
                //TODO: escape HTML?
                stream.write(title);
                stream.writeln("</h5>");
            }

            if (subTitle != null) {
                stream.write("<h6 class=\"card-subtitle mb-2 text-muted\">");
                //TODO: escape HTML?
                stream.write(subTitle);
                stream.writeln("</h6>");
            }
            stream.write("<p class=\"card-text\">");
            text.render(stream);
            stream.writeln("</p>");
            for (int i = 0; i < links.length; i += 2) {
                stream.write("<a href=\"");
                //TODO: escape HTML?
                stream.write(links[i]);
                stream.write("\" class=\"card-link\">");
                stream.write(links[i + 1]);
                stream.writeln("</a>");

            }
        }
    }
}
