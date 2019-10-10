package quicksilver.commons.datafeed;

import java.io.IOException;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import tech.tablesaw.api.Table;

public class DataFeedHTMLTest {

    @Test
    public void basic() throws IOException {
        DataFeedHTML d = new DataFeedHTML(getClass().getResource("table.html").toExternalForm());
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
