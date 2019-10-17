package quicksilver.commons.datafeed;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static spark.Spark.*;
import tech.tablesaw.api.Table;

public class DataFeedCSVTest {
    
    @Before
    public void setUp() {
        get("/basic.csv", (req, resp) -> {
            return IOUtils.toString(getClass().getResourceAsStream("basic.csv"), StandardCharsets.UTF_8);
        });
    }

    @Test
    public void basicCSV() throws IOException {
        DataFeedCSV d = new DataFeedCSV("http://127.0.0.1:4567/basic.csv");
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
    public void bushTest() throws IOException {
        DataFeedCSV dataFeed = new DataFeedCSV("https://raw.githubusercontent.com/jtablesaw/tablesaw/master/data/bush.csv");
        dataFeed.request();
        Table dataTable = dataFeed.getDataTable();
        System.out.println(dataTable.structure());
        System.out.println(dataTable.print());
    }

}
