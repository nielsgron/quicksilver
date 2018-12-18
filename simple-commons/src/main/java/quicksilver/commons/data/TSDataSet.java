package quicksilver.commons.data;

import tech.tablesaw.api.*;
import tech.tablesaw.columns.Column;

public class TSDataSet implements DataSet {

    private Table table;
    private TSDataSetMeta metaData;

    public TSDataSet(TSDataSetMeta metaData) {
        this.metaData = metaData;

        this.table = Table.create("name");
        this.table.addColumns(metaData.getColumns());
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
                col.appendCell("[NULL]");
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
        // TODO : Can't find a way to remove a row in TableSaw
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
