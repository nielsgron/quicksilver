package tech.tablesaw.plotly.traces;

import org.junit.Assert;
import org.junit.Test;

public class TreemapTraceTest {

    @Test
    public void testMe() {
        TreemapTrace trace = TreemapTrace.builder(null, new String[]{"a", "b", "c"}, new String[]{"b", "", ""}).build();

        String js = trace.asJavascript(0);

        Assert.assertTrue(js, js.contains("labels"));
    }

}
