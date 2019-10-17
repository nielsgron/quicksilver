package quicksilver.commons.datafeed;

import java.io.IOException;
import org.apache.commons.io.IOUtils;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static spark.Spark.get;
import tech.tablesaw.api.Table;

public class DataFeedXLSXTest {

    @Before
    public void setUp() {
        get("/abc.xlsx", (req, resp) -> {
            return IOUtils.toByteArray(getClass().getResourceAsStream("abc.xlsx"));
        });
    }

    @Test
    public void xlsx() throws IOException {
        DataFeedXLSX d = new DataFeedXLSX("http://127.0.0.1:4567/abc.xlsx");
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
