package quicksilver.commons.datafeed;

import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import tech.tablesaw.api.Table;

public class DataFeedRSSTest {

    @Test
    public void rss() throws IOException {
        DataFeedRSS d = new DataFeedRSS(getClass().getResource("simple-rss.xml").toString());
        d.request();
        Table t = d.getDataTable();
        assertTrue(t.rowCount() > 0);
        assertEquals("First item", t.getString(0, "title"));
        assertEquals("http://example.com/1", t.getString(0, "link"));
        //nulls
        assertEquals("", t.getString(1, "description"));
        assertEquals("", t.getString(2, "title"));
        assertEquals(7, t.columnCount());
        System.out.println(t.structure());
        System.out.println(t.print());
    }

}
