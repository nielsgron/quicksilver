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
    return asJSON();
  }

  Change(ChangeBuilder builder) {
    this.changeLine = builder.changeLine;
    this.fillColor = builder.fillColor;
    this.line = builder.line;
  }

  @Override
  protected Map<String, Object> getJSONContext() {
    Map<String, Object> context = new HashMap<>();
    if (changeLine != null) context.put("line", changeLine.getJSONContext());
    //ChangeLine does not have color, but Line does.
    if (line != null) context.put("line", line.getJSONContext());
    if (fillColor != null) context.put("fillcolor", fillColor);
    return context;
  }

  @Override
  protected Map<String, Object> getContext() {
    return getJSONContext();
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
