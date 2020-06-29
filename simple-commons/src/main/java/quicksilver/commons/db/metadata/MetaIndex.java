package quicksilver.commons.db.metadata;

public class MetaIndex {

    protected String name;
    protected String indexColumnNames;

    public MetaIndex(String name, String indexColumnNames) {
        this.name = name;
        this.indexColumnNames = indexColumnNames;
    }

    public String getName() {
        return name;
    }

    public String getIndexColumnNames() {
        return indexColumnNames;
    }

    public String[] getIndexColumns() {
        return indexColumnNames.split(",");
    }
}
