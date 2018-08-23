package quicksilver.commons.data;

import tech.tablesaw.columns.Column;

public class TSDataSetMeta extends BasicDataSetMeta {

    private Column[] columns;

    public TSDataSetMeta(Column[] columns, String[] colNames, Class[] colTypes) {
        super(colNames, colTypes);
        this.columns = columns;
    }

    public Column[] getColumns() {
        return columns;
    }

}
