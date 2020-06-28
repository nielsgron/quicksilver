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

import quicksilver.webapp.simpleui.HtmlStream;
import quicksilver.webapp.simpleui.html.components.HTMLComponent;
import quicksilver.webapp.simpleui.html.components.HTMLComponentContainer;
import quicksilver.webapp.simpleui.html.components.HTMLDiv;
import quicksilver.webapp.simpleui.html.components.HTMLText;

/*
    Example :

    <div id="carouselExampleSlidesOnly" class="carousel slide" data-ride="carousel">
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img class="d-block w-100" src=".../800x400?auto=yes&bg=777&fg=555&text=First slide" alt="First slide">
        </div>
        <div class="carousel-item">
          <img class="d-block w-100" src=".../800x400?auto=yes&bg=666&fg=444&text=Second slide" alt="Second slide">
        </div>
      </div>
    </div>

 */
/**
 * @see <a href='https://www.w3schools.com/bootstrap4/bootstrap_carousel.asp'>W3Schools</a>
 * @see <a href='https://getbootstrap.com/docs/4.1/components/carousel/'>Bootstrap Docs</a>
 */
public class BSCarousel extends BSComponentContainer {

    private final Inner inner;
    private boolean crossfade = false;

    public BSCarousel() {
        this.inner = new Inner();
        super.add(inner);
    }

    @Override
    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Carousel");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "div");

        addTagAttribute("class", getClassNames());
        addTagAttribute("data-ride", "carousel");

    }

    public Item add(Item item) {
        return (Item) inner.add(item);
    }

    /**
     * @param component any component
     * @return the Item created as a wrapper. The 1st component will create an
     * active item.
     */
    @Override
    public Item add(HTMLComponent component) {
        boolean activateFirstOne = inner.getChildrenCount() == 0;
        return (Item) add(new Item(component).active(activateFirstOne));
    }

    /**
     * Adds a component ignoring the constraint. See
     * {@link #add(quicksilver.webapp.simpleui.html.components.HTMLComponent)}.
     *
     * @param component
     * @param constraint ignored
     * @return the wrapped item
     */
    @Override
    public Item add(HTMLComponent component, Object constraint) {
        //TODO: log dropped constraint?
        return add(component);
    }

    public BSCarousel withControls() {
        String parentId = getId();
        //maybe it's worth the trouble to make these proper components... but they are soo static!
        super.add(new HTMLText("<a class=\"carousel-control-prev\" href=\"#" + parentId + "\" role=\"button\" data-slide=\"prev\">\n"
                + "    <span class=\"carousel-control-prev-icon\" aria-hidden=\"true\"></span>\n"
                + "    <span class=\"sr-only\">Previous</span>\n"
                + "  </a>\n"
                + "  <a class=\"carousel-control-next\" href=\"#" + parentId + "\" role=\"button\" data-slide=\"next\">\n"
                + "    <span class=\"carousel-control-next-icon\" aria-hidden=\"true\"></span>\n"
                + "    <span class=\"sr-only\">Next</span>\n"
                + "  </a>"));

        return this;
    }

    public BSCarousel withIndicators() {
        Indicators p = new Indicators();
        for (int i = 0; i < inner.getChildrenCount(); i++) {
            HTMLComponent child = inner.children.get(i);
            if (child instanceof Item) {
                p.add(new Indicator(getId(), i, ((Item) child).isActive()));
            } else {
                //TODO: Shouldn't happen: log?
            }
        }
        super.add(p);

        return this;
    }

    public BSCarousel crossfade(boolean crossfade) {
        setCrossfade(crossfade);
        return this;
    }

    public void setCrossfade(boolean crossfade) {
        this.crossfade = crossfade;
    }

    public boolean isCrossfade() {
        return crossfade;
    }

    @Override
    protected String getClassNames() {
        StringBuilder cNames = new StringBuilder();

        cNames.append("carousel slide");
        if (crossfade) {
            cNames.append(" carousel-fade");
        }
        //cNames.append(" alert-").append(getType().getTypeName());

        return cNames.toString();
    }

    static class Indicators extends HTMLComponentContainer {

        @Override
        protected void defineAttributes() {
            putComponentAttribute(COMPONENT_ATTRIB_NAME, "Carousel Indicators");
            putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
            putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "ol");
            addTagAttribute("class", getClassNames());
        }

        @Override
        protected String getClassNames() {
            return "carousel-indicators";
        }
    }

    static class Indicator extends BSComponentContainer {

        private final boolean active;
        private final String parentId;
        private final int index;

        Indicator(String parentId, int index, boolean active) {
            this.parentId = parentId;
            this.index = index;
            this.active = active;
        }

        @Override
        protected void defineAttributes() {
            putComponentAttribute(COMPONENT_ATTRIB_NAME, "Carousel Indicator");
            putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
            putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "li");

            addTagAttribute("data-target", "#" + parentId);
            addTagAttribute("data-slide-to", String.valueOf(index));
            if (getClassNames() != null) {
                addTagAttribute("class", getClassNames());
            }
        }

        @Override
        protected String getClassNames() {
            if (active) {
                return "active";
            }
            return null;
        }
    }

    static class Inner extends BSComponentContainer {

        @Override
        protected void defineAttributes() {
            putComponentAttribute(COMPONENT_ATTRIB_NAME, "Carousel Inner");
            putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
            putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "div");

            addTagAttribute("class", getClassNames());
        }

        @Override
        protected String getClassNames() {
            return "carousel-inner";
        }

        @Override
        public void renderBody(HtmlStream stream) {
            long active = children.stream()
                    .filter(c -> c instanceof Item)
                    .map(c -> (Item) c)
                    .filter(Item::isActive)
                    .count();
            if (active != 1) {
                throw new IllegalStateException("Carousel needs 1 active item, found " + active);
            }
            super.renderBody(stream);
        }

    }

    public static class Item extends BSComponentContainer {

        private boolean active = false;

        public Item(HTMLComponent c) {
            add(c);
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public boolean isActive() {
            return active;
        }

        public Item active(boolean active) {
            setActive(active);
            return this;
        }

        public Item withCaption(HTMLComponent... captions) {
            //d-block is not really mandatory
            HTMLComponentContainer caption = new HTMLDiv("carousel-caption d-block");
            for (HTMLComponent c : captions) {
                caption.add(c);
            }
            add(caption);

            return this;
        }

        @Override
        protected void defineAttributes() {
            putComponentAttribute(COMPONENT_ATTRIB_NAME, "Carousel Item");
            putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
            putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "div");

            addTagAttribute("class", getClassNames());
        }

        @Override
        protected String getClassNames() {
            if (active) {
                return "carousel-item active";
            } else {
                return "carousel-item";
            }
        }
    }
}
