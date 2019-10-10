package quicksilver.commons.datafeed;

import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.Test;
import tech.tablesaw.api.Table;

public class DataFeedJSONTest {

    @Test
    public void arrayOfArrays() throws IOException {
        DataFeedJSON d = new DataFeedJSON(getClass().getResource("arrays.json").toString());
        d.request();
        Table t = d.getDataTable();
        assertTrue(t.rowCount() > 0);
        assertEquals("1", t.getString(0, "a"));
        assertEquals("2", t.getString(0, "b"));
        assertEquals("3", t.getString(0, "c"));
        assertEquals(3, t.columnCount());
        System.out.println(t.structure());
        System.out.println(t.print());
    }

    @Test
    public void object() throws IOException {
        DataFeedJSON d = new DataFeedJSON(getClass().getResource("object.json").toString());
        d.request();
        Table t = d.getDataTable();
        assertTrue(t.rowCount() > 0);
        assertEquals("1", t.getString(0, "a"));
        assertEquals("2", t.getString(0, "b"));
        assertEquals("3", t.getString(0, "c"));
        assertEquals(3, t.columnCount());
        System.out.println(t.structure());
        System.out.println(t.print());
    }
}
