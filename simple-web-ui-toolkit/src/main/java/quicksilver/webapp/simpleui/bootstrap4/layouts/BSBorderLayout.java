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
import quicksilver.webapp.simpleui.html.components.HTMLComponent;
import quicksilver.webapp.simpleui.bootstrap4.components.BSContainer;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSRow;

import java.util.HashMap;

public class BSBorderLayout implements BSLayoutManager {

    public static final String NORTH = "north";
    public static final String SOUTH = "south";
    public static final String EAST = "east";
    public static final String WEST = "west";
    public static final String CENTER = "center";

    private BSContainer childrenContainer = new BSContainer(false,0,0);

    private HashMap<String, Integer> quadrantColumnWidth = new HashMap<String, Integer>();

    public BSBorderLayout() {

        childrenContainer.add(new BSRow(1));
        childrenContainer.add(new BSRow(3));
        childrenContainer.add(new BSRow(1));

    }

    public void render(HtmlStream stream) {

        BSContainer container = new BSContainer(false,0,0);

        // NORTH
        if ( getNorthPanel(false) != null ) {
            ((BSRow)(container.add(new BSRow(1)))).getColumn(0).add(getNorthPanel(false));
            container.getRow(container.getChildrenCount()-1 ).getColumn(0).setColumnWeight(getQuadrantColumnWidth(NORTH, 12));
        }

        // CENTER
        BSPanel west = getWestPanel(false);
        BSPanel center = getCenterPanel(false);
        BSPanel east = getEastPanel(false);

        int columnCount = 0;
        if ( west != null ) {
            columnCount++;
        }
        if ( center != null ) {
            columnCount++;
        }
        if ( east != null ) {
            columnCount++;
        }

        if ( columnCount > 0 ) {
            BSRow row = ((BSRow)(container.add(new BSRow(columnCount))));

            int columnPos = 0;
            if ( west != null ) {
                row.getColumn(columnPos).add(west);
                if ( columnCount == 1 ) {
                    row.getColumn(columnPos).setColumnWeight(getQuadrantColumnWidth(WEST, 12));
                } else {
                    if ( getCenterPanel(false) == null ) {
                        row.getColumn(columnPos).setColumnWeight(getQuadrantColumnWidth(WEST, 6));
                    } else {
                        row.getColumn(columnPos).setColumnWeight(getQuadrantColumnWidth(WEST, 2));
                    }
                }
                columnPos++;
            }
            if ( center != null ) {
                row.getColumn(columnPos).add(center);
                if ( columnCount == 1 ) {
                    row.getColumn(columnPos).setColumnWeight(getQuadrantColumnWidth(CENTER, 12));
                } else if (columnCount == 2) {
                    row.getColumn(columnPos).setColumnWeight(getQuadrantColumnWidth(CENTER, 10));
                } else {
                    row.getColumn(columnPos).setColumnWeight(getQuadrantColumnWidth(CENTER, 8));
                }
                columnPos++;
            }
            if ( east != null ) {
                row.getColumn(columnPos).add(east);
                if ( columnCount == 1 ) {
                    row.getColumn(columnPos).setColumnWeight(getQuadrantColumnWidth(EAST, 12));
                } else {
                    if ( getCenterPanel(false) == null ) {
                        row.getColumn(columnPos).setColumnWeight(getQuadrantColumnWidth(EAST, 6));
                    } else {
                        row.getColumn(columnPos).setColumnWeight(getQuadrantColumnWidth(EAST, 2));
                    }
                }
                columnPos++;
            }

        }

        // SOUTH
        if ( getSouthPanel(false) != null ) {
            ((BSRow)(container.add(new BSRow(1)))).getColumn(0).add(getSouthPanel(false));
            container.getRow(container.getChildrenCount()-1 ).getColumn(0).setColumnWeight(getQuadrantColumnWidth(SOUTH, 12));
        }

        container.render(stream);
    }

    public void setQuadrantColumnWidth(String quadrantName, int columnWidth) {
        quadrantColumnWidth.put(quadrantName, columnWidth);
    }

    public int getQuadrantColumnWidth(String quadrantName, int defaultValue) {
        Integer qValue = quadrantColumnWidth.get(quadrantName);
        if ( qValue == null ) {
            return defaultValue;
        } else {
            return qValue.intValue();
        }
    }

    public HTMLComponent add(HTMLComponent component) {
        return add(component, CENTER);
    }

    public HTMLComponent add(HTMLComponent component, Object constraint) {

        if ( constraint == null ) {
            return component;
        }

        if ( NORTH == constraint ) {
            getNorthPanel(true).add(component);
        } else if ( SOUTH == constraint ) {
            getSouthPanel(true).add(component);
        } else if ( EAST == constraint ) {
            getEastPanel(true).add(component);
        } else if ( WEST == constraint ) {
            getWestPanel(true).add(component);
        } else { // CENTER
            getCenterPanel(true).add(component);
        }

        return component;
    }

    private BSPanel getNorthPanel(boolean createIfDoesNotExist) {
        BSPanel panel = (BSPanel)childrenContainer.getRow(0).getColumn(0).get(0);
        if ( panel == null && createIfDoesNotExist ) {
            panel = new BSPanel();
            childrenContainer.getRow(0).getColumn(0).add(panel);
        }
        return panel;
    }

    private BSPanel getSouthPanel(boolean createIfDoesNotExist) {
        BSPanel panel = (BSPanel)childrenContainer.getRow(2).getColumn(0).get(0);
        if ( panel == null && createIfDoesNotExist ) {
            panel = new BSPanel();
            childrenContainer.getRow(2).getColumn(0).add(panel);
        }
        return panel;
    }

    private BSPanel getWestPanel(boolean createIfDoesNotExist) {
        BSPanel panel = (BSPanel)childrenContainer.getRow(1).getColumn(0).get(0);
        if ( panel == null && createIfDoesNotExist ) {
            panel = new BSPanel();
            childrenContainer.getRow(1).getColumn(0).add(panel);
        }
        return panel;
    }

    private BSPanel getEastPanel(boolean createIfDoesNotExist) {
        BSPanel panel = (BSPanel)childrenContainer.getRow(1).getColumn(2).get(0);
        if ( panel == null && createIfDoesNotExist ) {
            panel = new BSPanel();
            childrenContainer.getRow(1).getColumn(2).add(panel);
        }
        return panel;
    }

    private BSPanel getCenterPanel(boolean createIfDoesNotExist) {
        BSPanel panel = (BSPanel)childrenContainer.getRow(1).getColumn(1).get(0);
        if ( panel == null && createIfDoesNotExist ) {
            panel = new BSPanel();
            childrenContainer.getRow(1).getColumn(1).add(panel);
        }
        return panel;
    }

    public static BSPanel createBorderPanel(HTMLComponent left, HTMLComponent right, HTMLComponent top, HTMLComponent bottom, HTMLComponent center) {

        // Final Panel
        BSPanel panel = new BSPanel();
        panel.setLayout(new BSBorderLayout());

        if ( left != null ) {
            panel.add(left, BSBorderLayout.WEST);
        }
        if ( right != null ) {
            panel.add(right, BSBorderLayout.EAST);
        }
        if ( top != null ) {
            panel.add(top, BSBorderLayout.NORTH);
        }
        if ( bottom != null ) {
            panel.add(bottom, BSBorderLayout.SOUTH);
        }
        if ( center != null ) {
            panel.add(center, BSBorderLayout.CENTER);
        }

        return panel;

    }

}
