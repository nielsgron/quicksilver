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

import java.util.ArrayList;
import java.util.List;

/**
 * @see <a href='https://getbootstrap.com/docs/4.1/content/images/'>Bootstrap
 * Docs</a>
 */
public class BSImage extends BSComponent {

    private final String url;
    private final String alt;

    private boolean fluid = false;
    private boolean thumbnail = false;
    private boolean rounded = false;

    public BSImage(String url, String alt) {
        this.url = url;
        this.alt = alt;
    }

    @Override
    protected void defineAttributes() {
        putComponentAttribute(COMPONENT_ATTRIB_NAME, "BSImage");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.FALSE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "img");

//        addTagAttribute("width", "16");
//        addTagAttribute("height", "16");
        addTagAttribute("src", url);
        addTagAttribute("alt", alt);
        if(getClassNames() != null) {
            addTagAttribute("class", getClassNames());
        }
    }

    public void setFluid(boolean fluid) {
        this.fluid = fluid;
    }

    public void setRounded(boolean rounded) {
        this.rounded = rounded;
    }

    public void setThumbnail(boolean thumbnail) {
        this.thumbnail = thumbnail;
    }

    public BSImage fluid(boolean fluid) {
        this.fluid = fluid;
        return this;
    }

    public BSImage rounded(boolean rounded) {
        this.rounded = rounded;
        return this;
    }

    public BSImage thumbnail(boolean thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    @Override
    protected String getClassNames() {
        List<String> cNames = new ArrayList<>(3);

        if (fluid) {
            cNames.add("img-fluid");
        }
        if (thumbnail) {
            cNames.add("img-thumbnail");
        }
        if (rounded) {
            cNames.add("rounded");
        }

        return cNames.isEmpty() ? null : String.join(" ", cNames);
    }

}
