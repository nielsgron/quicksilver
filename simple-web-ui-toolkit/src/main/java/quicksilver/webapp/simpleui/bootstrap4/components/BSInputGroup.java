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

    W3Schools : https://www.w3schools.com/bootstrap4/bootstrap_forms_input_group.asp
    Bootstrap Docs : ??? https://getbootstrap.com/docs/4.1/components/forms/
 */

import quicksilver.webapp.simpleui.html.components.HTMLDiv;

public class BSInputGroup extends HTMLDiv {

    private boolean isAppend = false;
    private boolean isPrepend = false;
    private boolean isText = false;

    public BSInputGroup() {

    }

    public BSInputGroup(boolean isAppend, boolean isPrepend, boolean isText) {
        this.isAppend = isAppend;
        this.isPrepend = isPrepend;
        this.isText = isText;
    }

    protected String getClassNames() {
        StringBuilder cNames = new StringBuilder();

        cNames.append("input-group");

        if ( isAppend ) {
            cNames.append("-append");
        } else if ( isPrepend ) {
            cNames.append("-prepend");
        } else if ( isText ) {
            cNames.append("-text");
        }

        return cNames.toString();
    }

}
