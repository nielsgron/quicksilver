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

/**
 * @see <a href='https://getbootstrap.com/docs/4.1/components/forms/#checkboxes-and-radios'>Bootstrap Docs</a>
 */
public class BSInputRadio extends BSInput {

    private boolean checked;

    public BSInputRadio(String placeholder, String aria_label, String aria_describedby, String id) {
        super("radio", placeholder, aria_label, aria_describedby, id);
    }

    @Override
    protected void defineAttributes() {
        super.defineAttributes();

        if (checked) {
            addTagAttribute("checked", null);
        }
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public BSInputRadio checked(boolean checked) {
        setChecked(checked);
        return this;
    }

    @Override
    protected String getClassNames() {
        return "form-check-input";
    }

}
