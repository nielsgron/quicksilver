package quicksilver.commons.datafeed;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DataFeedTest {

    @Test
    public void testURL() {
        DataFeed f = new DataFeed("http://example.com") {
            @Override
            protected void buildDataSet() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        f.addParameter("k", "hello world");

        assertEquals("http://example.com?k=hello%20world", f.buildRequest().toString());

        f.addParameter("v", "<&");
        assertEquals("http://example.com?k=hello%20world&v=%3C%26", f.buildRequest().toString());
    }

}
