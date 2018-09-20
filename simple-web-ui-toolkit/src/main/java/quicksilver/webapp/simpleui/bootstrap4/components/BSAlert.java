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
    Example : <div class="alert alert-danger alert-dismissible fade show">

    W3Schools : https://www.w3schools.com/bootstrap4/bootstrap_alerts.asp
    Bootstrap Docs : https://getbootstrap.com/docs/4.1/components/alerts/
 */

public class BSAlert extends BSComponentContainer {

    public BSAlert() {
        this(BSComponent.Type.PRIMARY);
    }

    public BSAlert(BSComponent.Type cType) {
        setType(cType);
        defineAttributes();
    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Alert");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "div");

        addTagAttribute("class", getClassNames());
        addTagAttribute("role", "alert");

    }

    protected String getClassNames() {
        StringBuilder cNames = new StringBuilder();

        cNames.append("alert");
        cNames.append(" alert-").append(getType().getTypeName());

        return cNames.toString();
    }

}
