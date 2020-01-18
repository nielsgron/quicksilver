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

    W3Schools : https://www.w3schools.com/bootstrap4/bootstrap_forms_inputs.asp
    Bootstrap Docs : ??? https://getbootstrap.com/docs/4.1/components/forms/
 */

public abstract class BSInput extends BSComponent {

    private String input_type;
    private String input_placeholder;
    private String input_aria_label;
    private String input_aria_describedby;
    private String input_id;

    public BSInput(String type) {
        this(type, null);
    }

    public BSInput(String type, String placeholder) {
        this(type, placeholder, null, null, null);
    }

    public BSInput(String type, String placeholder, String aria_label, String aria_describedby, String id) {
        input_type = type;
        input_placeholder = placeholder;
        input_aria_label = aria_label;
        input_aria_describedby = aria_describedby;
        input_id = id;

    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Input");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.FALSE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "input");

        addTagAttribute("class", getClassNames());
        addTagAttribute("type", input_type);

        if ( input_placeholder != null && input_placeholder.length() > 0 ) {
            addTagAttribute("placeholder", input_placeholder);
        }
        if ( input_aria_label != null && input_aria_label.length() > 0 ) {
            addTagAttribute("aria-label", input_aria_label);
        }
        if ( input_aria_describedby != null && input_aria_describedby.length() > 0 ) {
            addTagAttribute("aria-describedby", input_aria_describedby);
        }
        if(input_id != null && input_id.length() > 0) {
            addTagAttribute("id", input_id);
            //TODO: perhaps allow user to set another name?
            addTagAttribute("name", input_id);
        }

    }

    public void setValue(String v) {
        addTagAttribute("value", v);
    }

    protected String getClassNames() {
        return "form-control";
    }

}
