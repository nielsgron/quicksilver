package quicksilver.commons.datafeed;

import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.Test;
import tech.tablesaw.api.Table;

public class DataFeedXLSXTest {

    @Test
    public void xlsx() throws IOException {
        DataFeedXLSX d = new DataFeedXLSX(getClass().getResource("abc.xlsx").toString());
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
