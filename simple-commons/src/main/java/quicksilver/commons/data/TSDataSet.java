package quicksilver.commons.data;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import tech.tablesaw.api.*;
import tech.tablesaw.columns.Column;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class TSDataSet extends Table implements DataSet {

    private TSDataSetMeta metaData;

    public TSDataSet(TSDataSetMeta metaData) {
        super("name");
        this.addColumns(metaData.getColumns());
        this.metaData = metaData;
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
            Column col = this.column(i);
            if ( val == null ) {
                col.appendCell("[NULL]");
            } else {
                col.appendCell(val.toString());
            }
        }
    }

    @Override
    public Object getValue(int colidx, int rowidx) {
        Object val = null;
        Column column = this.column(colidx);

        ColumnType type = column.type();
        switch(type) {
            case NUMBER:
                NumberColumn numberColumn = (NumberColumn)column;
                val = numberColumn.get(rowidx);
                break;
            case BOOLEAN:
                BooleanColumn booleanColumn = (BooleanColumn)column;
                val = booleanColumn.get(rowidx);
                break;
            case LOCAL_DATE:
                DateColumn localDateColumn = (DateColumn)column;
                val = localDateColumn.get(rowidx);
                break;
            case LOCAL_TIME:
                TimeColumn timeColumn = (TimeColumn)column;
                val = timeColumn.get(rowidx);
                break;
            case LOCAL_DATE_TIME:
                DateTimeColumn localDateTimeColumn = (DateTimeColumn)column;
                val = localDateTimeColumn.get(rowidx);
                break;
            case STRING:
                StringColumn stringColumn = (StringColumn)column;
                val = stringColumn.get(rowidx);
                break;
            default:
                throw new IllegalStateException("Unhandled column type updating columns");
        }

        return val;
    }

    @Override
    public Object getValue(String colName, int rowidx) {
        int colidx = getColumnIndex(colName);
        return getValue(colidx, rowidx);
    }

    @Override
    public int getRowCount() {
        return this.rowCount();
    }

    @Override
    public void removeRow(int rowidx) {
        // TODO : Can't find a way to remove a row in TableSaw
    }

    @Override
    public void removeAllRows() {
        this.clear();
    }

    @Override
    public void sort(String... columnNames) {
        // To sort a column descending, preface the name with a minus ‘-‘
        // e.g. || table.sortOn("-col1","col2", "col3");
        this.sortOn(columnNames);
    }

}
