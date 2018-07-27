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

package quicksilver.commons.db;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbutils.BeanProcessor;

public class ModelBeanProcessor<T> extends BeanProcessor {

    private final Map<String, String> columnToFieldOverrides;

    public ModelBeanProcessor(Class<T> type) {
        super();
        this.columnToFieldOverrides = getMappingFromAnnotations(type);
    }

    private Map<String, String> getMappingFromAnnotations(Class<T> type) {

        final Map<String, String> columnToPropertyOverrides = new HashMap<String, String>();

        for (Field field : type.getDeclaredFields()) {
//            if (field.isAnnotationPresent(Column.class)) {
//                columnToPropertyOverrides.put(field.getAnnotation(Column.class).value(), field.getName());
//            }
        }

        return columnToPropertyOverrides;
    }

    @Override
    protected int[] mapColumnsToProperties(final ResultSetMetaData rsmd, final PropertyDescriptor[] props) throws SQLException {

        final int cols = rsmd.getColumnCount();
        final int[] columnToProperty = new int[cols + 1];
        Arrays.fill(columnToProperty, PROPERTY_NOT_FOUND);

        for (int col = 1; col <= cols; col++) {
            String columnName = rsmd.getColumnLabel(col);

            if (null == columnName || 0 == columnName.length()) {
                columnName = rsmd.getColumnName(col);
            }

            String overrideName = columnToFieldOverrides.get(columnName);
            if (overrideName == null) {
                overrideName = columnName;
            }

            final String generousColumnName = columnName.replace("_", "");

            for (int i = 0; i < props.length; i++) {
                final String propName = props[i].getName();

                if (columnName.equalsIgnoreCase(propName) || generousColumnName.equalsIgnoreCase(propName) || overrideName.equalsIgnoreCase(propName)) {
                    columnToProperty[col] = i;
                    break;
                }
            }
        }

        return columnToProperty;
    }

}