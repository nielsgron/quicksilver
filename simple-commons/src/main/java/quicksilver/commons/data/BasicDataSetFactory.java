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

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class BasicDataSetFactory {

    public static BasicDataSet createDataSetFromQuery(Connection dbConnection, String query, Object... params ) {

        ResultSetHandler<BasicDataSet> h = new ResultSetHandler<BasicDataSet>() {
            public BasicDataSet handle(ResultSet rs) throws SQLException {
                return createDataSetFromResultSet(rs);
            }
        };

        QueryRunner run = new QueryRunner();
        BasicDataSet result;
        try {
            result = run.query(dbConnection, query, h, params);
        } catch ( Exception e ) {
            result = null;
            e.printStackTrace();
        }
        return result;
    }

    public static BasicDataSet createDataSetFromResultSet(ResultSet rs ) throws SQLException {

        if ( rs == null ) {
            return null;
        }

        ResultSetMetaData metadata = rs.getMetaData();
        int columnCount = metadata.getColumnCount();

        // Get the column names and types
        String[] colNames = new String[columnCount];
        Class[] colTypes = new Class[columnCount];

        try {
            for (int i = 0; i < columnCount; i++) {
                colNames[i] = metadata.getColumnLabel(i + 1);
                colTypes[i] = Class.forName(metadata.getColumnClassName(i + 1));
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        BasicDataSetMeta meta = new BasicDataSetMeta(colNames, colTypes);
        BasicDataSet dataSet = new BasicDataSet(meta);

        while ( rs.next() ) {
            // Get the row values
            Object[] row = new Object[columnCount];
            for (int i = 0; i < columnCount; i++) {
                row[i] = rs.getObject(i + 1);
            }
            dataSet.addRow(row);
        }

        return dataSet;
    }

}
