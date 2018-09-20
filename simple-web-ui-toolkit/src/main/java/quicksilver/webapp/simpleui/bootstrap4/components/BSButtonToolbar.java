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

package quicksilver.webapp.simpleui.bootstrap4.components;

/*
    Example :

    <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
      <div class="btn-group" role="group" aria-label="First group">
        <button type="button" class="btn btn-secondary">1</button>
      </div>
      <div class="btn-group" role="group" aria-label="Second group">
        <button type="button" class="btn btn-secondary">5</button>
      </div>
    </div>

    W3Schools : https://www.w3schools.com/bootstrap4/bootstrap_button_groups.asp
    Bootstrap Docs : https://getbootstrap.com/docs/4.1/components/button-group/
 */

public class BSButtonToolbar extends BSComponentContainer {

    public BSButtonToolbar() {
        defineAttributes();
    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Button Toolbar");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "div");

        addTagAttribute("class", "btn-toolbar");
        addTagAttribute("role", "toolbar");

    }

    public void addAsGroup(BSButton ...buttons) {

        BSButtonGroup g = new BSButtonGroup();
        for (BSButton b: buttons) {
            g.add(b);
        }
        add(g);

    }

}
