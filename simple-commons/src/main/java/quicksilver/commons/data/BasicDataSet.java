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

package quicksilver.commons.data;

import java.util.ArrayList;
import java.util.Comparator;

public class BasicDataSet implements DataSet {

    private BasicDataSetMeta metaData;
    private ArrayList<BasicDataSetRow> rows = new ArrayList<BasicDataSetRow>();
    private BasicDataSetRowComparator comparator = new BasicDataSetRowComparator();

    public BasicDataSet(BasicDataSetMeta meta) {
        metaData = meta;
    }

    // Methods for Getting Column Names and Types
    @Override
    public int getColumnCount() {
        return metaData.getColumnCount();
    }

    @Override
    public String getColumnName(int colidx) {
        return metaData.getColumnName(colidx);
    }

    @Override
    public Class getColumnType(int colidx) {
        return metaData.getColumnType(colidx);
    }

    @Override
    public int getColumnIndex(String colName) {
        return metaData.getColumnIndex(colName);
    }

    // Methods for Adding and Getting Rows
    @Override
    public void addRow(Object[] values) {
        BasicDataSetRow row = new BasicDataSetRow(values);
        rows.add(row);
    }

    // Methods for Getting Values
    @Override
    public Object getValue(int colidx, int rowidx) {
        BasicDataSetRow row = rows.get(rowidx);
        if ( row != null ) {
            return row.get(colidx);
        }
        return null;
    }

    @Override
    public Object getValue(String colName, int rowidx) {
        BasicDataSetRow row = rows.get(rowidx);
        if ( row != null ) {
            int colidx = metaData.getColumnIndex(colName);
            if ( colidx != -1 ) {
                return row.get(colidx);
            }
            return null;
        }
        return null;
    }

    // Method for Row Count
    @Override
    public int getRowCount() {
        return rows.size();
    }

    // Methods for Removing Rows
    @Override
    public void removeRow(int rowidx) {
        rows.remove(rowidx);
    }

    @Override
    public void removeAllRows() {
        rows.clear();
    }

    // Method to Sort Rows
    @Override
    public void sort(String... columnNames) {
        rows.sort(comparator);
    }

    public String toHtmlString(String title, int border) {

        StringBuilder html = new StringBuilder();

        html.append("<table border=" + border + ">");

        if ( title != null && title.trim().length() > 0 ) {
            html.append("<tr bgcolor='lightblue'>");
            html.append("<td colspan=" + metaData.getColumnCount() + ">");
            html.append(title);
            html.append("</td>");
            html.append("</tr>");
        }

        html.append("<tr bgcolor='lightgray' border=" + border + " >");
        for ( int j = 0; j < metaData.getColumnCount(); j++ ) {
            // Add Column
            html.append("<td>");
            html.append(metaData.getColumnName(j));
            html.append("</td>");
        }
        html.append("</tr>");

        for ( int i = 0; i < rows.size(); i++ ) {
            BasicDataSetRow row = rows.get(i);
            // Add Row
            html.append("<tr>");

            for ( int j = 0; j < metaData.getColumnCount(); j++ ) {
                // Add Column
                html.append("<td>");
                html.append(row.get(j));
                html.append("</td>");
            }

            // End Row
            html.append("</tr>");

        }

        html.append("</table>");

        return html.toString();
    }

    private class BasicDataSetRowComparator implements Comparator<BasicDataSetRow> {

        @Override
        public int compare(BasicDataSetRow o1, BasicDataSetRow o2) {
            return 0;
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }

    private class BasicDataSetRow {

        private Object[] data;

        public BasicDataSetRow(Object[] values) {
            data = values;
        }

        public Object get(int idx) {
            if ( idx < 0 || idx >= data.length ) {
                return null;
            }
            return data[idx];
        }

        public Object set(int idx, Object val) {
            if ( idx < 0 || idx >= data.length ) {
                return null;
            }
            data[idx] = val;
            return val;
        }

        public int getColumnCount() {
            return data.length;
        }

    }

}
