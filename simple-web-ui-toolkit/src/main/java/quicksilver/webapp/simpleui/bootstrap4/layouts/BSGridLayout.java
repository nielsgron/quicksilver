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

package quicksilver.webapp.simpleui.bootstrap4.layouts;

import quicksilver.webapp.simpleui.HtmlStream;
import quicksilver.webapp.simpleui.bootstrap4.components.BSContainer;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSRow;
import quicksilver.webapp.simpleui.html.components.HTMLComponent;

public class BSGridLayout implements BSLayoutManager  {

    private int rows;
    private int columns;
    private int currentLayoutPosition = 0;

    private BSContainer childrenContainer = new BSContainer(false,0,0);

    public BSGridLayout(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        for ( int i = 0; i < rows; i++ ) {
            childrenContainer.add(new BSRow(columns));
        }

    }

    @Override
    public HTMLComponent add(HTMLComponent component) {
        add(component, null);
        return component;
    }

    @Override
    public HTMLComponent add(HTMLComponent component, Object constraint) {
        int currentRow = currentLayoutPosition / columns;
        int currentColumn = currentLayoutPosition - (currentRow * columns);

        BSPanel panel = getCellPanel(currentRow, currentColumn);
        panel.add(component);

        currentLayoutPosition++;

        return component;
    }

    @Override
    public void render(HtmlStream stream) {
        childrenContainer.render(stream);
    }

    private BSPanel getCellPanel(int row, int column) {

        BSPanel panel = (BSPanel)childrenContainer.getRow(row).getColumn(column).get(0);
        if ( panel == null ) {
            panel = new BSPanel();
            childrenContainer.getRow(row).getColumn(column).add(panel);
            childrenContainer.getRow(row).getColumn(column).setColumnWeight( 12/columns);
        }
        return panel;
    }

}
