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
    private final Map<String, Object[]> extra;

    public TreemapTrace(TreemapBuilder builder) {
        super(builder);

        this.labels = builder.labels;
        this.parents = builder.parents;
        this.extra = builder.extra;
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
        //not sure how to get the template to write this array properly, re-add it as a single string
        Object contextIds = context.get("ids");
        if(contextIds != null) {
            context.put("ids", dataAsString((Object[]) contextIds));
        }
        context.put("labels", dataAsString(labels));
        context.put("parents", dataAsString(parents));
        if (extra != null) {
            extra.forEach((k, array) -> {
                if (!k.contains(".")) {
                    context.put(k, dataAsString(array));
                }
            });
            extra.forEach((k, array) -> {
                if (k.equals("marker.colors")) {
                    //Marker.color generates color: not colors: so we manually generate the JS
                    context.put("marker", "{\ncolors : " + dataAsString(array) + "\n}");
//                    context.put("marker", Marker.builder()
//                            .color(
//                                    Stream.of((Object[]) array).map(x -> String.valueOf(x)).toArray(String[]::new))
//                            .build());
                }
            });
        }
        return context;
    }

    public static TreemapBuilder builder(String[] ids, Object[] labels, Object[] parents, Map<String, Object[]> extra) {
        return new TreemapBuilder(ids, labels, parents, extra);
    }

    public static class TreemapBuilder extends TraceBuilder {

        private static final String type = "treemap";
        final Object[] labels;
        final Object[] parents;
        final Map<String, Object[]> extra;

        TreemapBuilder(String[] ids, Object[] labels, Object[] parents, Map<String, Object[]> extra) {
            this.ids = ids;
            this.labels = labels;
            this.parents = parents;
            this.extra = extra;
        }

        TreemapBuilder(Column<?> labels, Column<?> parents) {
            this.labels = columnToStringArray(labels);
            this.parents = columnToStringArray(parents);
            this.extra = null;
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
