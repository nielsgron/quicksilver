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

}
