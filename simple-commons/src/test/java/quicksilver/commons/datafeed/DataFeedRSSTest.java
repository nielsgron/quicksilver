package quicksilver.commons.datafeed;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static spark.Spark.get;
import tech.tablesaw.api.Table;

public class DataFeedRSSTest {

    @Before
    public void setUp() {
        get("/simple-rss.xml", (req, resp) -> {
            return IOUtils.toString(getClass().getResourceAsStream("simple-rss.xml"), StandardCharsets.UTF_8);
        });
    }

    @Test
    public void rss() throws IOException {
        DataFeedRSS d = new DataFeedRSS("http://127.0.0.1:4567/simple-rss.xml");
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
