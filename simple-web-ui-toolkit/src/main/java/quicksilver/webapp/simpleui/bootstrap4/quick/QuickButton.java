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

package quicksilver.webapp.simpleui.bootstrap4.quick;

import quicksilver.webapp.simpleui.bootstrap4.components.BSButton;

public class QuickButton extends BSButton {

    public QuickButton(String text) {

        super(text, Type.SECONDARY, false, null, false, false, false);
        setSize(Size.SMALL);

    }

    public QuickButton(String text, String hyperLink) {

        super(text, Type.SECONDARY, false, hyperLink, false, false, false);
        setSize(Size.SMALL);

    }

    public QuickButton(String text, String hyperLink, boolean isActive) {

        super(text, Type.SECONDARY, false, hyperLink, false, false, isActive);
        setSize(Size.SMALL);

    }

    protected String getStyle() {
        StringBuilder s = new StringBuilder();

        s.append("color: black;");

        if ( isActive() ) {
            s.append("background-color: #d9d9d9;");
        } else {
            s.append("background-color: #f2f2f2;");
        }

        //s.append("border-color: #d9d9d9;");
        s.append("border-color: #cccccc;");

        return s.toString();
    }

}
