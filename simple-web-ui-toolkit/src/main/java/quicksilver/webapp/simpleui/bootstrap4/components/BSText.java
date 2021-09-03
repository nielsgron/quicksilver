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

import quicksilver.webapp.simpleui.HtmlStream;
import quicksilver.webapp.simpleui.html.components.HTMLText;

/*
    Example :

    W3Schools :
    Bootstrap Docs :
 */

public class BSText extends HTMLText {

    private boolean bSmall = false;
    private boolean bBold = false;
    private boolean bItalic = false;

    public BSText(String txt) {
        super(txt);
    }

    public BSText(String txt, boolean bSmall, boolean bBold, boolean bItalic) {
        super(txt);
        this.bSmall = bSmall;
        this.bBold = bBold;
        this.bItalic = bItalic;
    }

    @Override
    public void render(HtmlStream stream) {

        if ( bSmall ) {
            stream.write("<small>");
        }
        if ( bBold ) {
            stream.write("<strong>");
        }
        if ( bItalic ) {
            stream.write("<em>");
        }

        stream.write(text);

        if ( bItalic ) {
            stream.write("</em>");
        }
        if ( bBold ) {
            stream.write("</strong>");
        }
        if ( bSmall ) {
            stream.write("</small>");
        }

    }


}
