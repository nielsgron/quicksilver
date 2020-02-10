package tech.tablesaw.plotly.components.change;

import java.util.HashMap;
import java.util.Map;
import tech.tablesaw.plotly.components.Component;
import tech.tablesaw.plotly.components.Line;

public abstract class Change extends Component {

  // private static final ChangeLine DEFAULT_CHANGE_LINE = new LineBuilder().build();

  private final ChangeLine changeLine;
  private final String fillColor;
  private final Line line;

  @Override
  public String asJavascript() {
    return asJavascript("change_template.html");
  }

  Change(ChangeBuilder builder) {
    this.changeLine = builder.changeLine;
    this.fillColor = builder.fillColor;
    this.line = builder.line;
  }

  @Override
  protected Map<String, Object> getContext() {
    Map<String, Object> context = new HashMap<>();
    if (changeLine != null) context.put("changeLine", changeLine);
    if (fillColor != null) context.put("fillColor", fillColor);
    if (line != null) context.put("line", line.asJavascript());
    return context;
  }

  public static class ChangeBuilder {

    protected String fillColor;
    protected Line line;
    protected ChangeLine changeLine;

    public ChangeBuilder fillColor(String color) {
      this.fillColor = color;
      return this;
    }

    public ChangeBuilder line(Line line) {
      this.line = line;
      return this;
    }

    public ChangeBuilder changeLine(ChangeLine line) {
      this.changeLine = line;
      return this;
    }
  }
}
