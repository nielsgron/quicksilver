package quicksilver.commons.datafeed;

import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.Test;
import tech.tablesaw.api.Table;

public class DataFeedCSVTest {

    @Test
    public void basicCSV() throws IOException {
        DataFeedCSV d = new DataFeedCSV(getClass().getResource("basic.csv").toString());
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
