package tech.tablesaw.plotly.traces;

import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.columns.Column;
import static tech.tablesaw.plotly.Utils.dataAsString;

public class SunburstTrace extends AbstractTrace {

    private final Object[] labels;
    private final Object[] parents;
    private final double[] values;

    public SunburstTrace(SunburstBuilder builder) {
        super(builder);

        this.labels = builder.labels;
        this.parents = builder.parents;
        this.values = builder.values;
    }

    @Override
    public String asJavascript(int i) {
        Writer writer = new StringWriter();
        PebbleTemplate compiledTemplate;

        try {
            compiledTemplate = engine.getTemplate("trace_template2.html");
            compiledTemplate.evaluate(writer, getContext(i));
        } catch (PebbleException | IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    private Map<String, Object> getContext(int i) {

        Map<String, Object> context = super.getContext();

        //these two don't make sense for sunburst
        context.remove("xAxis");
        context.remove("yAxis");

        context.put("variableName", "trace" + i);
        //not sure how to get the template to write this array properly, re-add it as a single string
        Object contextIds = context.get("ids");
        if(contextIds != null) {
            context.put("ids", dataAsString((Object[]) contextIds));
        }
        context.put("labels", dataAsString(labels));
        context.put("parents", dataAsString(parents));
        context.put("values", dataAsString(values));
        return context;
    }

    public static SunburstBuilder builder(String[] ids, Object[] labels, Object[] parents, double[] values) {
        return new SunburstBuilder(ids, labels, parents, values);
    }

    public static class SunburstBuilder extends TraceBuilder {

        private static final String type = "sunburst";
        final Object[] labels;
        final Object[] parents;
        final double[] values;

        SunburstBuilder(String[] ids, Object[] labels, Object[] parents, double[] values) {
            this.ids = ids;
            this.labels = labels;
            this.parents = parents;
            this.values = values;
        }

        SunburstBuilder(Column<?> labels, Column<?> parents, DoubleColumn values) {
            this.ids = null;
            this.labels = columnToStringArray(labels);
            this.parents = columnToStringArray(parents);
            this.values = values.asDoubleArray();
        }

        public SunburstTrace build() {
            return new SunburstTrace(this);
        }

        @Override
        protected String getType() {
            return type;
        }
    }
}
