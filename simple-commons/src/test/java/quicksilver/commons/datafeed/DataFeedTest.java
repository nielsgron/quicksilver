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

        //XXX: OkHttp's path defaults to /, there's no way for it to be empty...
        assertEquals("http://example.com/", f.buildRequest().toString());

        f.addParameter("k", "hello world");
        
        f.setBaseURLString("http://example.com/search");

        assertEquals("http://example.com/search?k=hello%20world", f.buildRequest().toString());

        f.addParameter("v", "<&");
        assertEquals("http://example.com/search?k=hello%20world&v=%3C%26", f.buildRequest().toString());
    }

}
