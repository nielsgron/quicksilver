package tech.tablesaw.plotly.traces;

import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import tech.tablesaw.columns.Column;
import static tech.tablesaw.plotly.Utils.dataAsString;

public class TreemapTrace extends AbstractTrace {

    private final Object[] labels;
    private final Object[] parents;

    public TreemapTrace(TreemapBuilder builder) {
        super(builder);

        this.labels = builder.labels;
        this.parents = builder.parents;
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

        //these two don't make sense for treemap
        context.remove("xAxis");
        context.remove("yAxis");

        context.put("variableName", "trace" + i);
        context.put("labels", dataAsString(labels));
        context.put("parents", dataAsString(parents));
        return context;
    }

    public static TreemapBuilder builder(Object[] labels, Object[] parents) {
        return new TreemapBuilder(labels, parents);
    }

    public static class TreemapBuilder extends TraceBuilder {

        private static final String type = "treemap";
        final Object[] labels;
        final Object[] parents;

        TreemapBuilder(Object[] labels, Object[] parents) {
            this.labels = labels;
            this.parents = parents;
        }

        TreemapBuilder(Column<?> labels, Column<?> parents) {
            this.labels = columnToStringArray(labels);
            this.parents = columnToStringArray(parents);
        }

        public TreemapTrace build() {
            return new TreemapTrace(this);
        }

        @Override
        protected String getType() {
            return type;
        }
    }
}
