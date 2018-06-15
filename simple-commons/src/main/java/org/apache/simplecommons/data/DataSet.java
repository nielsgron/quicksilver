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

package org.apache.simplecommons.data;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.simplecommons.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataSet {

    private DataSetMeta metaData;
    private ArrayList<DataSetRow> rows = new ArrayList<DataSetRow>();
    private DataSetRowComparator comparator = new DataSetRowComparator();

    public DataSet(DataSetMeta meta) {
        metaData = meta;
    }

    // Methods for Getting Column Names and Types
    public int getColumnCount() {
        return metaData.getColumnCount();
    }

    public String getColumnName(int colidx) {
        return metaData.getColumnName(colidx);
    }

    public Class getColumnType(int colidx) {
        return metaData.getColumnType(colidx);
    }

    public int getColumnIndex(String colName) {
        return metaData.getColumnIndex(colName);
    }

    // Methods for Adding and Getting Rows
    public void addRow(Object[] values) {
        DataSetRow row = new DataSetRow(values);
        addRow(row);
    }

    public void addRow(DataSetRow row) {
        rows.add(row);
    }

    public DataSetRow getRow(int idx) {
        return rows.get(idx);
    }

    // Methods for Getting Values
    public Object getValue(int colidx, int rowidx) {
        DataSetRow row = getRow(rowidx);
        if ( row != null ) {
            return row.get(colidx);
        }
        return null;
    }

    public Object getValue(String colName, int rowidx) {
        DataSetRow row = getRow(rowidx);
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
    public int getRowCount() {
        return rows.size();
    }

    // Methods for Removing Rows
    public void removeRow(int rowidx) {
        rows.remove(rowidx);
    }

    public void removeAllRows() {
        rows.clear();
    }

    // Method to Sort Rows
    public void sort() {
        rows.sort(comparator);
    }

    public static DataSet createDataSetFromQuery(Connection dbConnection, String query, Object... params ) {

        ResultSetHandler<DataSet> h = new ResultSetHandler<DataSet>() {
            public DataSet handle(ResultSet rs) throws SQLException {
                return createDataSetFromResultSet(rs);
            }
        };

        QueryRunner run = new QueryRunner();
        DataSet result;
        try {
            result = run.query(dbConnection, query, h, params);
        } catch ( Exception e ) {
            result = null;
        }
        return result;
    }

    public static DataSet createDataSetFromResultSet( ResultSet rs ) throws SQLException {

        if ( rs == null ) {
            return null;
        }

        ResultSetMetaData metadata = rs.getMetaData();
        int columnCount = metadata.getColumnCount();

        // Get the column names and types
        String[] colNames = new String[columnCount];
        Class[] colTypes = new Class[columnCount];

        try {
            for (int i = 0; i < columnCount; i++) {
                colNames[i] = metadata.getColumnLabel(i + 1);
                colTypes[i] = Class.forName(metadata.getColumnClassName(i + 1));
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        DataSetMeta meta = new DataSetMeta(colNames, colTypes);
        DataSet dataSet = new DataSet(meta);

        while ( rs.next() ) {
            // Get the row values
            Object[] row = new Object[columnCount];
            for (int i = 0; i < columnCount; i++) {
                row[i] = rs.getObject(i + 1);
            }
            dataSet.addRow(row);
        }

        return dataSet;
    }

    public String toHtmlString() {
        return toHtmlString("", 1);
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
            DataSetRow row = rows.get(i);
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

    public String toHtmlBootstrap(String title, int border) {

        StringBuilder html = new StringBuilder();

        html.append("<table class='table table-sm'>");

        if ( title != null && title.trim().length() > 0 ) {
            html.append("<caption>");
            html.append(title);
            html.append("</caption>");
        }

        html.append("<thead>");
        html.append("<tr>");
        for ( int j = 0; j < metaData.getColumnCount(); j++ ) {
            // Add Column
            html.append("<th scope='col'>");
            html.append(metaData.getColumnName(j));
            html.append("</th>");
        }
        html.append("</tr>");
        html.append("</thead>");

        html.append("<tbody>");
        for ( int i = 0; i < rows.size(); i++ ) {
            DataSetRow row = rows.get(i);
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
        html.append("</tbody>");

        html.append("</table>");

        return html.toString();

    }

}
