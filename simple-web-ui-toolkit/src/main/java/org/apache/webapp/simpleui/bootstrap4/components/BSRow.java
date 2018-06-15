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

package org.apache.webapp.simpleui.bootstrap4.components;

import org.apache.webapp.simpleui.HtmlStream;

public class BSRow extends BSComponentContainer {

    public BSRow(int columns) {
        initColumns(columns);
    }

    private void initColumns(int columns) {
        for ( int i = 0; i < columns; i++ ) {
            add(new BSColumn());
        }
    }

    public void render(HtmlStream stream) {

        stream.writeln("<div class=\"row\">");
        // Render BSColumn children
        super.render(stream);
        stream.writeln("</div>");

    }

    // Override add() method so that we only add BSColumn components
    public BSComponent add(BSComponent component) {
        if ( component instanceof BSColumn ) {
            super.add(component);
        }
        return component;
    }

    public BSColumn getColumn(int index) {
        return (BSColumn)super.get(index);
    }

}
