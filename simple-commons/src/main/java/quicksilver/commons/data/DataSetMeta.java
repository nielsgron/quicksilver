package quicksilver.commons.data;

public interface DataSetMeta {

    public String getColumnName(int idx);
    public Class getColumnType(int idx);
    public int getColumnIndex(String name);
    public int getColumnCount();

}
