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

package quicksilver.webapp.simpleui.html.components;

import quicksilver.webapp.simpleui.HtmlStream;

public class HTMLLineBreak extends HTMLComponent {

    private int numberOfLineBreaks;

    public HTMLLineBreak() {
        this(1);
    }

    public HTMLLineBreak(int numberOfLineBreaks) {
        this.numberOfLineBreaks = numberOfLineBreaks;
    }

    protected void defineAttributes() {

    }

    @Override
    public void render(HtmlStream stream) {
        for (int i = 0; i < numberOfLineBreaks; i++ ) {
            stream.writeln("<br>");
        }
    }

}
