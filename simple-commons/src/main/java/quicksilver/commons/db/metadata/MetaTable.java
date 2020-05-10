package quicksilver.commons.db.metadata;

import tech.tablesaw.api.*;
import tech.tablesaw.columns.Column;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MetaTable {

    protected String name;
    protected HashMap<String, MetaColumn> columnMap = new HashMap<String, MetaColumn>();
    protected ArrayList<MetaColumn> columnList = new ArrayList<MetaColumn>();

    protected HashMap<String, MetaIndex> indexMap = new HashMap<String, MetaIndex>();
    protected ArrayList<MetaIndex> indexList = new ArrayList<MetaIndex>();

    public MetaTable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public MetaColumn addColumn(MetaColumn column) {

        columnMap.put(column.getName(), column);
        columnList.add(column);

        return column;
    }

    public MetaColumn getColumn(String name) {
        return columnMap.get(name);
    }

    public List getColumn() {
        return columnList;
    }

    public MetaIndex addIndex(MetaIndex index) {

        indexMap.put(index.getName(), index);
        indexList.add(index);

        return index;
    }

    public MetaIndex getIndex(String name) {
        return indexMap.get(name);
    }

    public List getIndexes() {
        return columnList;
    }

    private void createTableFromTSTable(Table table) {

        this.name = table.name();

        // Add all the columns
        List<String> columnNames = table.columnNames();

        for ( int columnIndex = 0; columnIndex < columnNames.size(); columnIndex++  ) {

            Column column = table.column(columnIndex);
            ColumnType columnType = column.type();

            MetaColumn metaColumn;
            if ( columnType == null ) {
                metaColumn = this.addColumn(new MetaColumn(column.name()));
            }
            // Numbers
            else if ( columnType instanceof ShortColumn ) {
                metaColumn = this.addColumn(new MetaColumn(column.name()));
            }
            else if ( columnType instanceof IntColumn ) {
                metaColumn = this.addColumn(new MetaColumn(column.name()));
            }
            else if ( columnType instanceof LongColumn ) {
                metaColumn = this.addColumn(new MetaColumn(column.name()));
            }
            else if ( columnType instanceof DoubleColumn ) {
                metaColumn = this.addColumn(new MetaColumn(column.name()));
            }
            else if ( columnType instanceof FloatColumn ) {
                metaColumn = this.addColumn(new MetaColumn(column.name()));
            }
            // String
            else if ( columnType instanceof StringColumn ) {
                metaColumn = this.addColumn(new MetaColumn(column.name()));
            }
            else if ( columnType instanceof TextColumn) {
                metaColumn = this.addColumn(new MetaColumn(column.name()));
            }
            // Boolean
            else if ( columnType instanceof BooleanColumn) {
                metaColumn = this.addColumn(new MetaColumn(column.name()));
            }
            // Date & Time
            else if ( columnType instanceof DateColumn) {
                metaColumn = this.addColumn(new MetaColumn(column.name()));
            }
            else if ( columnType instanceof DateTimeColumn) {
                metaColumn = this.addColumn(new MetaColumn(column.name()));
            }
            else if ( columnType instanceof TimeColumn) {
                metaColumn = this.addColumn(new MetaColumn(column.name()));
            }

            else {
                metaColumn = this.addColumn(new MetaColumn(column.name()));
            }

        }

    }

    public String getCREATE_DDL(boolean ifNotExists) {

        StringBuilder ddl = new StringBuilder();

        ddl.append("CREATE TABLE ");
        if ( ifNotExists ) {
            ddl.append("IF NOT EXISTS ");
        }
        ddl.append(name + " (");

        for ( int columnIndex = 0; columnIndex < columnList.size(); columnIndex++  ) {

            MetaColumn column = columnList.get(columnIndex);
            String columnType = column.getTypeDefinition();

            if ( columnIndex > 0 ) {
                ddl.append(", ");
            }
            ddl.append(column.getName() + " ");
            ddl.append(columnType);

        }

        ddl.append(" )");

        return ddl.toString();

    }

    public String getINSERT_DML(Table table) {

        StringBuilder dml = new StringBuilder();

        dml.append("INSERT INTO ");


        return dml.toString();

    }

}
