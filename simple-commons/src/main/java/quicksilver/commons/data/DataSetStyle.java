package quicksilver.commons.data;

public class DataSetStyle {

    private ColumnStyle[] columnStyles;
    private CellStyle[][] cellStyles;

    public DataSetStyle(int columns, int rows) {

        columnStyles = new ColumnStyle[columns];
        cellStyles = new CellStyle[columns][rows];

    }

    // Utility methods to get the ColumnStyle and CellStyle for a column/row coordinate
    private ColumnStyle getColumnStyle(int column) {
        if ( columnStyles[column] == null ) {
            columnStyles[column] = new ColumnStyle();
        }
        return columnStyles[column];
    }

    private CellStyle getCellStyle(int column, int row) {
        if ( cellStyles[column][row] == null ) {
            cellStyles[column][row] = new CellStyle();
        }
        return cellStyles[column][row];
    }

    // Class : ColumnStyle
    public static class ColumnStyle {

        private int width;

        public ColumnStyle() {

        }

        public void setColumnWidth(int width) {
            this.width = width;
        }
        public int getColumnWidth() {
            return width;
        }

    }

    // Class : CellStyle
    public static class CellStyle {

        public CellStyle() {

        }

    }

}
