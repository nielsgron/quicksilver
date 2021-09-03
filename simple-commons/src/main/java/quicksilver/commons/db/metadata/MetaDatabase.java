package quicksilver.commons.db.metadata;

import quicksilver.commons.db.DatabaseConnection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MetaDatabase {

    protected String name;
    protected HashMap<String, MetaTable> tableMap = new HashMap<String, MetaTable>();
    protected ArrayList<MetaTable> tableList = new ArrayList<MetaTable>();

    public MetaDatabase(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public MetaTable addTable(MetaTable table) {
        tableMap.put(table.getName(), table);
        tableList.add(table);
        return table;
    }

    public MetaTable getTable(String name) {
        return tableMap.get(name);
    }

    public List<MetaTable> getTables() {
        return tableList;
    }

    public void createTablesIfNotExist(DatabaseConnection connection) {

        List<MetaTable> tableList = getTables();
        int tableCount = tableList.size();

        for ( int i = 0; i < tableCount; i++ ) {
            MetaTable table = tableList.get(i);
            String ddl = table.getCREATE_DDL(true);

            System.out.println("Execute: " + ddl);
            try {
                connection.execute(ddl);
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }

    }

    public void createIndexesIfNotExist(DatabaseConnection connection, String tableName, boolean includePrimaryKey) {

        if ( tableName == null || tableName.trim().length() == 0 ) {
            tableName = "ALL";
        }

        List<MetaTable> tableList = getTables();
        int tableCount = tableList.size();

        for ( int i = 0; i < tableCount; i++ ) {
            MetaTable table = tableList.get(i);

            if ( table.getName().equals(tableName) || tableName.equals("ALL") ) {

                if ( includePrimaryKey ) {

                    MetaIndex index = table.getPrimaryKey();
                    if ( index != null ) {
                        String ddl = index.getCREATE_DDL(true);

                        System.out.println("Execute: " + ddl);
                        try {
                            connection.execute(ddl);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                List<MetaIndex> indexes = table.getIndexes();

                for ( int j = 0; j < indexes.size(); j++ ) {

                    MetaIndex index = indexes.get(j);
                    String ddl = index.getCREATE_DDL(true);

                    System.out.println("Execute: " + ddl);
                    try {
                        connection.execute(ddl);
                    } catch ( Exception e ) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void dropIndexesIfExist(DatabaseConnection connection, String tableName, boolean includePrimaryKey) {

        if ( tableName == null || tableName.trim().length() == 0 ) {
            tableName = "ALL";
        }

        List<MetaTable> tableList = getTables();
        int tableCount = tableList.size();

        for ( int i = 0; i < tableCount; i++ ) {
            MetaTable table = tableList.get(i);

            if ( table.getName().equals(tableName) || tableName.equals("ALL") ) {

                if ( includePrimaryKey ) {

                    MetaIndex index = table.getPrimaryKey();
                    if ( index != null ) {
                        String ddl = index.getDROP_DDL(true);

                        System.out.println("Execute: " + ddl);
                        try {
                            connection.execute(ddl);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                List<MetaIndex> indexes = table.getIndexes();

                for ( int j = 0; j < indexes.size(); j++ ) {

                    MetaIndex index = indexes.get(j);
                    String ddl = index.getDROP_DDL(true);

                    System.out.println("Execute: " + ddl);
                    try {
                        connection.execute(ddl);
                    } catch ( Exception e ) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void deleteRows(DatabaseConnection connection, String tableName) {

        if ( tableName == null || tableName.trim().length() == 0 ) {
            tableName = "ALL";
        }

        List<MetaTable> tableList = getTables();
        int tableCount = tableList.size();

        for ( int i = 0; i < tableCount; i++ ) {
            MetaTable table = tableList.get(i);

            if ( table.getName().equals(tableName) || tableName.equals("ALL") ) {

                String ddl = table.getDELETE_DDL();

                System.out.println("Execute: " + ddl);
                try {
                    connection.execute(ddl);
                } catch ( Exception e ) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getCREATE_DDL() {
        return "create database if not exists " + name + " character set = 'utf8' collate = 'utf8_general_ci'";
    }

}
