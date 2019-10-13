package quicksilver.commons.datafeed.impl;

import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import tech.tablesaw.api.Table;

public class DataFeedTwitterImplTest {

    static class CachedFeed extends DataFeedTwitter {

        @Override
        public void request() throws IOException {
            byte[] buff = new byte[10 * 1024 * 1024];
            int len = IOUtils.read(DataFeedTwitterImplTest.class.getResourceAsStream("twitter.json"), buff, 0, buff.length);
            dataPayload = new byte[len];
            System.arraycopy(buff, 0, dataPayload, 0, len);

            buildDataSet();
        }

    }

    @Test
    public void testCached() throws IOException {
        CachedFeed dataFeed = new CachedFeed();

        dataFeed.request();
        Table dataTable = dataFeed.getDataTable();
        System.out.print(dataTable.structure());
        System.out.print(dataTable.print());
        
        Assert.assertTrue(dataTable.rowCount() > 0);
    }

}
