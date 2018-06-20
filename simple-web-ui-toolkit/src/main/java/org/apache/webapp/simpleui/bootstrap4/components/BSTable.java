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

package org.apache.webapp.simpleui.bootstrap4.components;

import org.apache.simplecommons.data.DataSet;
import org.apache.simplecommons.data.DataSetRow;
import org.apache.webapp.simpleui.HtmlStream;

public class BSTable extends BSComponentContainer {

    private String title;
    private DataSet dataSet;
    private TableCellRenderer[] renderers;

    public BSTable(DataSet ds, String t) {
        dataSet = ds;
        title = t;
        renderers = new TableCellRenderer[dataSet.getColumnCount()];
    }

    public void setTableCellRenderer(TableCellRenderer renderer, int column) {
        renderers[column] = renderer;
    }

    public void render(HtmlStream stream) {

        StringBuilder html = new StringBuilder();

        html.append("<table class='table table-sm'>");

        if ( title != null && title.trim().length() > 0 ) {
            html.append("<caption>");
            html.append(title);
            html.append("</caption>");
        }

        html.append("<thead>");
        html.append("<tr>");
        for ( int j = 0; j < dataSet.getColumnCount(); j++ ) {
            // Add Column
            html.append("<th scope='col'>");
            html.append(dataSet.getColumnName(j));
            html.append("</th>");
        }
        html.append("</tr>");
        html.append("</thead>");

        html.append("<tbody>");
        for ( int i = 0; i < dataSet.getRowCount(); i++ ) {
            DataSetRow row = dataSet.getRow(i);
            // Add Row
            html.append("<tr>");

            for ( int j = 0; j < dataSet.getColumnCount(); j++ ) {
                // Add Column
                html.append("<td>");
                if ( renderers[j] == null ) {
                    html.append(row.get(j));
                } else {
                    html.append(renderers[j].render(row.get(j)));
                }
                html.append("</td>");
            }

            // End Row
            html.append("</tr>");

        }
        html.append("</tbody>");

        html.append("</table>");

        stream.write(html.toString());

    }

    public static class HTMLTableCellRenderer implements TableCellRenderer {

        @Override
        public String render(Object value) {
            if ( value == null ) {
                return null;
            }
            StringBuilder sb = new StringBuilder();

            sb.append("<a href=\"");
            sb.append(getHREF(value));
            sb.append("\">");
            sb.append(value.toString());
            sb.append("</a>");

            return sb.toString();
        }

        protected String getHREF(Object value) {
            return "/" + value.toString();
        }

    }

    public interface TableCellRenderer {

        String render(Object value);

    }

}

