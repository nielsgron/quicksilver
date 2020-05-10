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

package quicksilver.commons.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.javalite.activejdbc.DB;
import org.javalite.activejdbc.DBException;
import org.javalite.activejdbc.Model;
import org.javalite.common.Util;
import tech.tablesaw.api.*;
import tech.tablesaw.columns.Column;
import tech.tablesaw.columns.booleans.BooleanColumnType;
import tech.tablesaw.columns.dates.DateColumnType;
import tech.tablesaw.columns.numbers.*;
import tech.tablesaw.columns.strings.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public abstract class DatabaseConnection {

    protected DataSource  _dataSource;
    protected DB          _DB;

    protected String      _host;
    protected String      _port;
    protected String      _database;
    protected String      _userName;
    protected String      _password;

    public DatabaseConnection(String host, String port, String database, String userName, String password) {

        _host = host;
        _port = port;
        _database = database;
        _userName = userName;
        _password = password;

        // Lets get the datasource for the Hikari Connection Pool
        _dataSource = getDataSource();
        _DB = new DB(_database);

    }

    public synchronized void connect() throws SQLException {

        // This is how you make connections for ActiveJDBC library.
        // http://javalite.io/database_connection_management
        // Base.open(mysqlDriverClass, getURL(), _userName, _password);
        if ( !_DB.hasConnection() ) {
            _DB.open(_dataSource);
        }

    }

    public void disconnect() {
        if ( _DB != null ) {
            _DB.close();
        }
        //Base.close();
    }

    public Connection getConnection() {
        return _DB.getConnection();
    }

    protected abstract String getURL();

    public Connection testDirectJDBCConnection() throws SQLException {
        return DriverManager.getConnection(getURL(), _userName, _password);
    }

    private DataSource getDataSource() {

        // We will use the Hikari Connection Pool

        // https://github.com/brettwooldridge/HikariCP#initialization
        // https://github.com/brettwooldridge/HikariCP/wiki/MySQL-Configuration

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(getURL());
        config.setUsername(_userName);
        config.setPassword(_password);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        return new HikariDataSource(config);

    }

    public int execute(String sql) throws Exception {

        DatabaseOperation op = new DatabaseOperation(this) {
            @Override
            public void executeOperation(Connection connection) {
                recordCount = execute(connection, sql);
            }
        };

        op.run();

        return op.recordCount;
    }

    public int execute(Connection connection, String sql) {
        int recordCount = 0;
        Statement s = null;
        try {
            s = connection.createStatement();
            recordCount = s.executeUpdate(sql);
        } catch (Exception e) {
            throw new DBException(sql, (Object[])null, e);
        } finally {
            Util.closeQuietly(s);
        }
        return recordCount;
    }

    public <T> List<T> queryList(String sql, Class<T> beanType, Object[] params) throws SQLException {

        List<T> returnList = null;

        BeanListHandler<T> resultSetHandler = new BeanListHandler<T>(beanType, new BasicRowProcessor(new ModelBeanProcessor<T>(beanType)));

        returnList = new QueryRunner(_dataSource).query(_DB.getConnection(), sql, resultSetHandler, params);

        return returnList;

    }

    public void createTable(Table table, boolean ifNotExists) {

        StringBuilder createTableString = new StringBuilder();

        createTableString.append("CREATE TABLE ");
        if ( ifNotExists ) {
            createTableString.append("IF NOT EXISTS ");
        }
        createTableString.append(table.name() + " (");

        List<String> columnNames = table.columnNames();
        for ( int columnIndex = 0; columnIndex < columnNames.size(); columnIndex++  ) {

            Column column = table.column(columnIndex);
            ColumnType columnType = column.type();

            if ( columnIndex > 0 ) {
                createTableString.append(", ");
            }
            createTableString.append(column.name() + " ");

            if ( columnType instanceof StringColumn ) {
                createTableString.append("VARCHAR(255) ");
            }

        }

        createTableString.append(table.name() + " )");

    }

    public void insertInto(Table table) {
        insertInto(table, false, null);
    }

    public void insertInto(Table table, boolean merge, String keyColumns) {

        try {
            new DatabaseOperation(this) {
                @Override
                public void executeOperation(Connection connection) {
                    // TODO: BulkImport
                    BulkImport bulkImport = DatabaseConnection.this.createBulkImport(connection, null, 5000, true, keyColumns);

                    List<String> columnNames = table.columnNames();
                    String[] columnNamesArray = columnNames.toArray(new String[0]);

                    // Start Bulk Import
                    try {
                        bulkImport.startBulkImport(table.name(), columnNamesArray);
                    } catch (SQLException e ) {
                        e.printStackTrace();
                    }

                    for ( int rowIndex = 0; rowIndex < table.rowCount(); rowIndex++ ) {

                        for ( int columnIndex = 0; columnIndex < columnNames.size(); columnIndex++  ) {

                            boolean isNull = false;
                            Column column = table.column(columnIndex);
                            ColumnType columnType = column.type();
                            String stringValue = column.getString(rowIndex);
                            if ( stringValue == null || stringValue.trim().length() < 1 ) {
                                isNull = true;
                            }

                            if ( columnType instanceof StringColumnType) {
                                if ( isNull ) {
                                    bulkImport.setString(column.name(), null);
                                } else {
                                    bulkImport.setString(column.name(), column.getString(rowIndex));
                                }
                            }
                            else if ( columnType instanceof TextColumnType) {
                                if ( isNull ) {
                                    bulkImport.setString(column.name(), null);
                                } else {
                                    bulkImport.setString(column.name(), column.getString(rowIndex));
                                }
                            }
                            else if ( columnType instanceof BooleanColumnType) {
                                if ( isNull ) {
                                    bulkImport.setBoolean(column.name(), null);
                                } else {
                                    BooleanColumn boolColumn = (BooleanColumn) column;
                                    bulkImport.setBoolean(column.name(), boolColumn.get(rowIndex));
                                }
                            }
                            else if ( columnType instanceof ShortColumnType) {
                                if ( isNull ) {
                                    bulkImport.setShort(column.name(), null);
                                } else {
                                    ShortColumn shortColumn = (ShortColumn) column;
                                    bulkImport.setShort(column.name(), shortColumn.get(rowIndex));
                                }
                            }
                            else if ( columnType instanceof IntColumnType) {
                                if ( isNull ) {
                                    bulkImport.setInt(column.name(), null);
                                } else {
                                    IntColumn intColumn = (IntColumn) column;
                                    bulkImport.setInt(column.name(), intColumn.get(rowIndex));
                                }
                            }
                            else if ( columnType instanceof LongColumnType) {
                                if ( isNull ) {
                                    bulkImport.setLong(column.name(), null);
                                } else {
                                    LongColumn longColumn = (LongColumn) column;
                                    bulkImport.setLong(column.name(), longColumn.get(rowIndex));
                                }
                            }
                            else if ( columnType instanceof DoubleColumnType) {
                                if ( isNull ) {
                                    bulkImport.setDouble(column.name(), null);
                                } else {
                                    DoubleColumn doubleColumn = (DoubleColumn) column;
                                    bulkImport.setDouble(column.name(), doubleColumn.get(rowIndex));
                                }
                            }
                            else if ( columnType instanceof FloatColumnType) {
                                if ( isNull ) {
                                    bulkImport.setFloat(column.name(), null);
                                } else {
                                    FloatColumn floatColumn = (FloatColumn) column;
                                    bulkImport.setFloat(column.name(), floatColumn.get(rowIndex));
                                }
                            }
                            else if ( columnType instanceof DateColumnType) {
                                if ( isNull ) {
                                    bulkImport.setDate(column.name(), null);
                                } else {
                                    LocalDate localDate = ((DateColumn) column).get(rowIndex);
                                    java.util.Date date = java.util.Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                                    bulkImport.setDate(column.name(), new java.sql.Date(date.getTime()));
                                }
                            } else {
                                System.out.println("Unknown column type : " + columnType.getClass().getName());
                                if ( isNull ) {
                                    bulkImport.setString(column.name(), null);
                                } else {
                                    bulkImport.setString(column.name(), column.getString(rowIndex));
                                }
                            }

                        }

                        try {
                            bulkImport.saveIt();
                        } catch ( Exception e ) {
                            e.printStackTrace();
                        }

                    }

                    // End Bulk Import
                    try {
                        bulkImport.endBulkImport();
                    } catch (SQLException e ) {
                        e.printStackTrace();
                    }

                }
            }.run();
        } catch ( Exception e ) {
            e.printStackTrace();
        }

    }

    public abstract BulkImport createBulkImport(Connection connection, Model dbObject, int batchSize);

    public abstract BulkImport createBulkImport(Connection connection, Model dbObject, int batchSize, boolean merge, String keyColumns);

}
