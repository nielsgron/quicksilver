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

package quicksilver.commons.data;

import tech.tablesaw.api.*;
import tech.tablesaw.columns.Column;
import tech.tablesaw.columns.booleans.BooleanColumnType;
import tech.tablesaw.columns.dates.DateColumnType;
import tech.tablesaw.columns.datetimes.DateTimeColumnType;
import tech.tablesaw.columns.numbers.*;
import tech.tablesaw.columns.strings.StringColumnType;
import tech.tablesaw.columns.times.TimeColumnType;

import java.sql.Time;

public class TSDataSet implements DataSet {

    private Table table;
    private TSDataSetMeta metaData;

    public TSDataSet(Table table) {
        this.table = table;

        ColumnType[] types = table.columnTypes();
        Class[] classTypes = new Class[types.length];
        for ( int i = 0; i < types.length; i++ ) {
            classTypes[i] = getClassByColumnType(types[i]);
        }

        this.metaData = new TSDataSetMeta(table.columnArray(), (String[])table.columnNames().toArray(new String[table.columnNames().size()]), classTypes);
    }

    public TSDataSet(TSDataSetMeta metaData) {
        this.metaData = metaData;

        this.table = Table.create("name");
        this.table.addColumns(metaData.getColumns());
    }

    private Class getClassByColumnType(ColumnType type) {

        if ( type instanceof StringColumnType ) {
            return String.class;
        } else if ( type instanceof BooleanColumnType ) {
            return Boolean.class;
        } else if ( type instanceof DoubleColumnType) {
            return Double.class;
        } else if ( type instanceof FloatColumnType) {
            return Float.class;
        } else if ( type instanceof LongColumnType) {
            return Long.class;
        } else if ( type instanceof IntColumnType) {
            return Integer.class;
        } else if ( type instanceof ShortColumnType) {
            return Short.class;
        } else if ( type instanceof DateColumnType) {
            return java.sql.Timestamp.class;
        } else if ( type instanceof DateTimeColumnType) {
            return java.sql.Timestamp.class;
        } else if ( type instanceof TimeColumnType) {
            return Time.class;
        } else {
            return String.class;
        }

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

    @Override
    public void addRow(Object[] row) {
        for ( int i = 0; i < row.length; i++ ) {
            Object val = row[i];
            Column col = table.column(i);
            if ( val == null ) {
                //col.appendCell("[NULL]");
                col.appendCell(null);
            } else {
                //col.appendCell(val.toString());
                col.appendObj(val);
            }
        }
    }

    @Override
    public Object getValue(int colidx, int rowidx) {

        Column column = table.column(colidx);
        return column.get(rowidx);

    }

    @Override
    public Object getValue(String colName, int rowidx) {
        int colidx = getColumnIndex(colName);
        return getValue(colidx, rowidx);
    }

    @Override
    public int getRowCount() {
        return table.rowCount();
    }

    @Override
    public void removeRow(int rowidx) {
	table.dropRows(rowidx);
    }

    @Override
    public void removeAllRows() {
        table.clear();
    }

    @Override
    public void sort(String... columnNames) {
        // To sort a column descending, preface the name with a minus ‘-‘
        // e.g. || table.sortOn("-col1","col2", "col3");
        table = table.sortOn(columnNames);
    }

    public Table getTSTable() {
        return table;
    }


}
