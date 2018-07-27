package quicksilver.webapp.simpleui.bootstrap4.components;

import quicksilver.webapp.simpleui.HtmlStream;

public class BSProgress extends BSComponent {

    //TODO: Move this enum elsewhere
    public enum Background {
        primary("bg-primary"),
        secondary("bg-secondary"),
        success("bg-success"),
        danger("bg-danger"),
        warning("bg-warning"),
        info("bg-info"),
        light("bg-light"),
        dark("bg-dark"),
        white("bg-white")
        ;

        private final String name;

        Background(String name) {
            this.name = name;
        }
    }


    private final int percentage;
    private final String label;
    //TODO: height?
    //@Nullable
    private final Background background;
    private final boolean striped;
    private final boolean animated;


    public BSProgress(int percentage) {
        this(percentage, "");
    }

    public BSProgress(int percentage, String label) {
        this(percentage, label, null, false, false);
    }

    private BSProgress(int percentage, String label, Background background, boolean striped, boolean animated) {
        if(percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Wrong percentage value");
        }
        if(label == null){
            throw new NullPointerException();
        }
        this.percentage = percentage;
        this.label = label;
        this.background = background;
        this.striped = striped;
        this.animated = animated;
    }

    public BSProgress background(Background background) {
        return new BSProgress(this.percentage, this.label, background, this.striped, this.animated);
    }

    public BSProgress striped() {
        return new BSProgress(this.percentage, this.label, this.background, true, this.animated);
    }

    public BSProgress animated() {
        return new BSProgress(this.percentage, this.label, this.background, this.striped, true);
    }

    @Override
    public void render(HtmlStream stream) {
        stream.writeln("<div class=\"progress\">");
        stream.write(
                "  <div class=\"progress-bar");
        if(background != null) {
            stream.write(" ");
            stream.write(background.name);
        }
        if(striped) {
            stream.write("  progress-bar-striped");
            if(animated) {
                stream.write(" progress-bar-animated");
            }
        }
        stream.write("\" role=\"progressbar\" style=\"width: ");
        stream.write(String.valueOf(percentage));
        stream.write("%\" aria-valuenow=\"");
        stream.write(String.valueOf(percentage));
        stream.write("\" aria-valuemin=\"0\" aria-valuemax=\"100\">");
        stream.write(label);
        stream.writeln("</div>");
        stream.writeln("</div>");
    }
}
