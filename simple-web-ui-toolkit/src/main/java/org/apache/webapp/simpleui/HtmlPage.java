/*
 * Copyright 2018 Niels Gron All Rights Reserved.
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

package org.apache.webapp.simpleui;

public class HtmlPage {

    public HtmlPage() {

    }

    public String getTitle() {
        return "";
    }

    public void render(HtmlStream stream) {
        renderHTMLOpen(stream);

        renderHEADOpen(stream);
        renderHEAD(stream);
        renderHEADClose(stream);

        renderBODYOpen(stream);
        renderBODY(stream);
        renderBODYClose(stream);

        renderHTMLClose(stream);
    }

    public void renderHTMLOpen(HtmlStream stream) {
        stream.writeln("<html>");
    }
    public void renderHTMLClose(HtmlStream stream) {
        stream.writeln("</html>");
    }

    public void renderHEADOpen(HtmlStream stream) {
        stream.writeln("<head>");
    }
    public void renderHEADClose(HtmlStream stream) {
        stream.writeln("</head>");
    }
    public void renderHEAD(HtmlStream stream) {
        //stream.write("");
    }

    public void renderBODYOpen(HtmlStream stream) {
        stream.writeln("<body>");
    }
    public void renderBODYClose(HtmlStream stream) {
        stream.writeln("</body>");
    }
    public void renderBODY(HtmlStream stream) {
        stream.writeln("");
    }

}
