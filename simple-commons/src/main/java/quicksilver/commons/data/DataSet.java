package quicksilver.commons.data;

public interface DataSet {

    // Methods for Getting Column Names and Types
    public int getColumnCount();
    public String getColumnName(int colidx);
    public Class getColumnType(int colidx);
    public int getColumnIndex(String colName);

    // Methods for Adding and Getting Rows
    public void addRow(Object[] values);

    // Methods for Getting Values
    public Object getValue(int colidx, int rowidx);
    public Object getValue(String colName, int rowidx);

    // Method for Row Count
    public int getRowCount();

    // Methods for Removing Rows
    public void removeRow(int rowidx);
    public void removeAllRows();

    // Method to Sort Rows
    public void sort(String... columnNames);

}
