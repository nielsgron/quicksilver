package tech.tablesaw.plotly.components.change;

import tech.tablesaw.plotly.components.Line;

public class Decreasing extends Change {

  private Decreasing(DecreasingBuilder builder) {
    super(builder);
  }

  public static DecreasingBuilder builder() {
    return new DecreasingBuilder();
  }

  public static class DecreasingBuilder extends ChangeBuilder {

    @Override
    public DecreasingBuilder fillColor(String color) {
      this.fillColor = color;
      return this;
    }

    @Override
    public DecreasingBuilder line(Line line) {
      this.line = line;
      return this;
    }

    @Override
    public DecreasingBuilder changeLine(ChangeLine line) {
      this.changeLine = line;
      return this;
    }

    public Decreasing build() {
      return new Decreasing(this);
    }
  }
}
