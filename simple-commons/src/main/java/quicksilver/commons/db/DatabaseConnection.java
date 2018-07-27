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

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.javalite.activejdbc.DB;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DatabaseConnection {

    private static String mysqlDriverClass = "com.mysql.jdbc.Driver";

    static {
        try {
            Class.forName(mysqlDriverClass);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    private DataSource  _dataSource;
    private DB          _DB;

    private String      _host;
    private String      _port;
    private String      _database;
    private String      _userName;
    private String      _password;

    public DatabaseConnection(String host, String port, String database, String userName, String password) {

        _host = host;
        _port = port;
        _database = database;
        _userName = userName;
        _password = password;

        // Lets get the datasource for the Hikari Connection Pool
        _dataSource = getDataSource();
        _DB = new DB("default");

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

    private String getURL() {
        return "jdbc:mysql://" + _host + ":" + _port + "/" + _database;
    }

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

    public int execute(String sql) {
        return _DB.exec(sql);
    }

    public <T> List<T> queryList(String sql, Class<T> beanType, Object[] params) throws SQLException {

        List<T> returnList = null;

        BeanListHandler<T> resultSetHandler = new BeanListHandler<T>(beanType, new BasicRowProcessor(new ModelBeanProcessor<T>(beanType)));

        returnList = new QueryRunner(_dataSource).query(_DB.getConnection(), sql, resultSetHandler, params);

        return returnList;

    }

}
