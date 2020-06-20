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

import org.checkerframework.checker.nullness.qual.Nullable;
import quicksilver.webapp.simpleui.html.components.HTMLDiv;
import quicksilver.webapp.simpleui.html.components.HTMLText;

/**
 * @see <a href='https://getbootstrap.com/docs/4.1/components/progress/'>Bootstrap Docs</a>
 */
public class BSProgress extends BSComponentContainer {

    //TODO: Move this enum elsewhere
    public enum Background {
        primary("bg-primary"),
        secondary("bg-secondary"),
        success("bg-success"),
        danger("bg-danger"),
        warning("bg-warning"),
        info("bg-info"),
        light("bg-light"),
        dark("bg-dark"),
        white("bg-white")
        ;

        private final String name;

        Background(String name) {
            this.name = name;
        }
    }


    private final int percentage;
    private final String label;
    //TODO: height?
    @Nullable
    private Background background;
    private boolean striped;
    private boolean animated;


    public BSProgress(int percentage) {
        this(percentage, "");
    }

    public BSProgress(int percentage, String label) {
        this(percentage, label, null, false, false);
    }

    private BSProgress(int percentage, String label, Background background, boolean striped, boolean animated) {
        if(percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Wrong percentage value");
        }
        if(label == null){
            throw new NullPointerException();
        }
        this.percentage = percentage;
        this.label = label;
        this.background = background;
        this.striped = striped;
        this.animated = animated;
        add(new Inner());
    }

    public BSProgress background(@Nullable Background background) {
        setBackground(background);
        return this;
    }

    public BSProgress striped() {
        setStriped(true);
        return this;
    }

    public void setStriped(boolean striped) {
        this.striped = striped;
    }

    public boolean isStriped() {
        return striped;
    }

    public void setBackground(Background background) {
        this.background = background;
    }

    public Background getBackground() {
        return background;
    }

    public void setAnimated(boolean animated) {
        this.animated = animated;
    }

    public boolean isAnimated() {
        return animated;
    }

    public BSProgress animated() {
        setAnimated(true);
        return this;
    }

    @Override
    protected void defineAttributes() {
        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Progress");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "div");

        addTagAttribute("class", getClassNames());
    }

    @Override
    protected String getClassNames() {
        return "progress";
    }

    class Inner extends HTMLDiv {

        Inner() {
            add(new HTMLText(label));
        }

        @Override
        protected void defineAttributes() {
            super.defineAttributes();
            addTagAttribute("role", "progressbar");
            addTagAttribute("aria-valuenow", String.valueOf(percentage));
            addTagAttribute("aria-valuemin", "0");
            addTagAttribute("aria-valuemax", "100");
        }

        @Override
        protected String getStyle() {
            return "width: " + String.valueOf(percentage) + "%";
        }

        @Override
        protected String getClassNames() {
            StringBuilder sb = new StringBuilder();

            sb.append("progress-bar");
            if (background != null) {
                sb.append(" ")
                        .append(background.name);
            }
            if (striped) {
                sb.append(" progress-bar-striped");
                if (animated) {
                    sb.append(" progress-bar-animated");
                }
            }

            return sb.toString();
        }
    }
}
