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
