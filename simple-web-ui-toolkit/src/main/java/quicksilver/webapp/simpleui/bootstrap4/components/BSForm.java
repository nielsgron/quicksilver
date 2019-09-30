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

import quicksilver.webapp.simpleui.html.components.HTMLComponent;

/*
    Example :
        <form action="/action_page.jsp">
          <div class="form-group">
            <label for="email">Email address:</label>
            <input type="email" class="form-control" id="email">
          </div>
          <div class="form-group">
            <label for="pwd">Password:</label>
            <input type="password" class="form-control" id="pwd">
          </div>
          <div class="form-group form-check">
            <label class="form-check-label">
              <input class="form-check-input" type="checkbox"> Remember me
            </label>
          </div>
          <button type="submit" class="btn btn-primary">Create an account</button>
        </form>

    W3Schools : https://www.w3schools.com/bootstrap4/bootstrap_forms.asp
    Bootstrap Docs : https://getbootstrap.com/docs/4.1/components/forms/
 */

public class BSForm extends BSComponentContainer {

    private boolean prop_isInline = false;
    private boolean prop_isGET = true;
    private String prop_action;

    public BSForm(String action, boolean isGET) {
        this(false, action, isGET);
    }

    public BSForm(boolean bInline, String action, boolean isGET) {
        prop_isInline = bInline;
        prop_action = action;
        prop_isGET = isGET;

    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Form");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "form");

        if ( prop_isInline ) {
            addTagAttribute("class", "form-inline my-2 my-md-0");
        } else {
            // no class
        }

        addTagAttribute("action", prop_action);

        if ( prop_isGET ) {
            //no need, it's the default
        } else {
            addTagAttribute("method", "post");
        }

    }

    public void addAsGroup(HTMLComponent... c) {

        BSFormGroup group = new BSFormGroup();
        for ( int i = 0; i < c.length; i++ ) {
            group.add(c[i]);
        }

        add(group);
    }

    public void addAsCheckGroup(HTMLComponent... c) {

        BSFormCheck group = new BSFormCheck();
        for ( int i = 0; i < c.length; i++ ) {
            group.add(c[i]);
        }

        add(group);
    }
}
