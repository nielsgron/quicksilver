package quicksilver.commons.datafeed;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static spark.Spark.get;
import tech.tablesaw.api.Table;

public class DataFeedJSONTest {

    @Before
    public void setUp() {
        get("/arrays.json", (req, resp) -> {
            return IOUtils.toString(getClass().getResourceAsStream("arrays.json"), StandardCharsets.UTF_8);
        });
        get("/object.json", (req, resp) -> {
            return IOUtils.toString(getClass().getResourceAsStream("object.json"), StandardCharsets.UTF_8);
        });
    }

    @Test
    public void arrayOfArrays() throws IOException {
        DataFeedJSON d = new DataFeedJSON("http://127.0.0.1:4567/arrays.json");
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
        DataFeedJSON d = new DataFeedJSON("http://127.0.0.1:4567/object.json");
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
