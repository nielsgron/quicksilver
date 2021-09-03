package quicksilver.commons.db.metadata;

public class MetaIndex {

    protected String tableName;
    protected String indexName;
    protected String indexColumnNames;
    protected boolean isPrimary;
    protected boolean isUnique;

    public MetaIndex(String tableName, String indexName, String indexColumnNames, boolean isPrimary, boolean isUnique) {
        this.tableName = tableName;
        this.indexName = indexName;
        this.indexColumnNames = indexColumnNames;
        this.isPrimary = isPrimary;
        this.isUnique = isUnique;
    }

    public String getTableName() {
        return tableName;
    }

    public String getIndexName() {
        return indexName;
    }

    public String getIndexColumnNames() {
        return indexColumnNames;
    }

    public String[] getIndexColumns() {
        return indexColumnNames.split(",");
    }

    public String getCREATE_DDL(boolean ifNotExists) {

        StringBuilder ddl = new StringBuilder();

        ddl.append("CREATE ");
        if ( isPrimary ) {
            ddl.append("PRIMARY KEY ");
        } else {
            if ( isUnique ) {
                ddl.append("UNIQUE ");
            }
            ddl.append("INDEX ");
            if ( ifNotExists ) {
                ddl.append("IF NOT EXISTS ");
            }
            ddl.append(indexName);
            ddl.append(" ");
        }

        ddl.append("ON ");
        ddl.append(tableName);
        ddl.append(" ( ");
        ddl.append(indexColumnNames);
        ddl.append(" ) ");

        return ddl.toString();

    }

    public String getDROP_DDL(boolean ifExists) {

        StringBuilder ddl = new StringBuilder();

        if ( isPrimary ) {
            ddl.append("ALTER TABLE ");
            if ( ifExists ) {
                ddl.append("IF EXISTS ");
            }
            ddl.append(tableName);
            ddl.append(" DROP PRIMARY KEY");

        } else {
            ddl.append("DROP INDEX ");
            if ( ifExists ) {
                ddl.append("IF EXISTS ");
            }
            ddl.append(indexName);
        }

        return ddl.toString();
    }

}
