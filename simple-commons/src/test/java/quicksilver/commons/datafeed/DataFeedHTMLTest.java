package quicksilver.commons.datafeed;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static spark.Spark.get;
import tech.tablesaw.api.Table;

public class DataFeedHTMLTest {

    @Before
    public void setUp() {
        get("/table.html", (req, resp) -> {
            return IOUtils.toString(getClass().getResourceAsStream("table.html"), StandardCharsets.UTF_8);
        });
    }

    @Test
    public void basic() throws IOException {
        DataFeedHTML d = new DataFeedHTML("http://127.0.0.1:4567/table.html");
        d.request();
        Table t = d.getDataTable();
        assertTrue(t.rowCount() > 0);
        System.out.println(t.structure());
        System.out.println(t.print());
    }

    /*
    @Test
    public void btcPrice() throws IOException {
        DataFeedHTML d = new DataFeedHTML("https://coinmarketcap.com/currencies/bitcoin/historical-data/#0");
        d.request();
        Table t = d.getDataTable();
        System.out.println(t.structure());
        System.out.println(t.print());
    }
     */
}
