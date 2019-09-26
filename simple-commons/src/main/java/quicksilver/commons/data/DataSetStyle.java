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
