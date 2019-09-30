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

/*
    Example :

      <label class="sr-only" for="inlineFormInputGroup">Username</label>
      <div class="input-group mb-2">
        <div class="input-group-prepend">
          <div class="input-group-text">@</div>
        </div>
        <input type="text" class="form-control" id="inlineFormInputGroup" placeholder="Username">
      </div>

    W3Schools : https://www.w3schools.com/bootstrap4/bootstrap_forms_inputs.asp
    Bootstrap Docs : https://getbootstrap.com/docs/4.1/components/forms/#help-text
 */

public class BSInputHelpText extends BSComponentContainer {

    private String text;
    private String id;
    private boolean isInline = false;

    public BSInputHelpText(String text, String id) {
        this(text, id, false);
    }

    public BSInputHelpText(String text, String id, boolean isInline) {
        this.text = text;
        this.id = id;
        this.isInline = isInline;
        add(new BSText(text));
    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Help Text");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "small");

        if (id != null) {
            addTagAttribute("id", id);
        }

        addTagAttribute("class", getClassNames());
    }

    protected String getClassNames() {
        if ( isInline ) {
            return "text-muted";
        } else {
            return "form-text text-muted";
        }
    }

}
