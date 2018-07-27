/*
 * Copyright 2018 Niels Gron and Contributors All Rights Reserved.
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

import java.util.HashMap;

public class DataSetMeta {

    private String[] columnNames;
    private Class[] columnTypes;
    private HashMap<String, Integer> columnNameIndexes = new HashMap<String, Integer>();

    public DataSetMeta(String[] colNames, Class[] colTypes) {
        columnNames = colNames;
        columnTypes = colTypes;

        for ( int i = 0; i < columnNames.length; i++ ) {
            columnNameIndexes.put(columnNames[i], i);
        }
    }

    public String getColumnName(int idx) {
        if ( idx < 0 || idx >= columnNames.length ) {
            return null;
        }
        return columnNames[idx];
    }

    public Class getColumnType(int idx) {
        if ( idx < 0 || idx >= columnTypes.length ) {
            return null;
        }
        return columnTypes[idx];
    }

    public int getColumnIndex(String name) {
        Integer idx = columnNameIndexes.get(name);
        if ( idx != null ) {
            return idx;
        } else {
            return -1;
        }
    }

    public int getColumnCount() {
        return columnNames.length;
    }

}
