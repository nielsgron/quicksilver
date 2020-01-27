package tech.tablesaw.charts.impl.calheatmap;

import tech.tablesaw.plotly.components.Layout;

import java.time.LocalDate;

public class CalHeatmapLayout extends Layout {

    protected String domain = "month";
    protected String subDomain = "x_day";
    protected int cellSize = 20;
    protected int domainGutter = 10;
    protected String subDomainTextFormat = "%d";
    protected int range = 1;
    protected LocalDate highlight;

    public CalHeatmapLayout(Layout.LayoutBuilder layoutBuilder) {
        super(layoutBuilder);

        if ( layoutBuilder instanceof CalHeatmapLayout.LayoutBuilder ) {

            CalHeatmapLayout.LayoutBuilder calheatmapLayoutBuilder = (CalHeatmapLayout.LayoutBuilder)layoutBuilder;

            this.domain = calheatmapLayoutBuilder.domain;
            this.subDomain = calheatmapLayoutBuilder.subDomain;
            this.cellSize = calheatmapLayoutBuilder.cellSize;
            this.domainGutter = calheatmapLayoutBuilder.domainGutter;
            this.subDomainTextFormat = calheatmapLayoutBuilder.subDomainTextFormat;
            this.range = calheatmapLayoutBuilder.range;
            this.highlight = calheatmapLayoutBuilder.highlight;

        }

    }

    public String getDomain() { return domain; }
    public String getSubDomain() { return subDomain; }
    public int getCellSize() { return cellSize; }
    public int getDomainGutter() { return domainGutter; }
    public String getSubDomainTextFormat() { return subDomainTextFormat; }
    public int getRange() { return range; }
    public LocalDate getHighlight() { return highlight; }

    public static class LayoutBuilder extends Layout.LayoutBuilder {

        protected String domain = "month";
        protected String subDomain = "x_day";
        protected int cellSize = 20;
        protected int domainGutter = 10;
        protected String subDomainTextFormat = "%d";
        protected int range = 1;
        protected LocalDate highlight;

        public LayoutBuilder() {
            super();

        }

        public LayoutBuilder domain(String domain) {
            this.domain = domain;
            return this;
        }

        public LayoutBuilder subDomain(String subDomain) {
            this.subDomain = subDomain;
            return this;
        }

        public LayoutBuilder cellSize(int cellSize) {
            this.cellSize = cellSize;
            return this;
        }

        public LayoutBuilder domainGutter(int domainGutter) {
            this.domainGutter = domainGutter;
            return this;
        }

        public LayoutBuilder subDomainTextFormat(String subDomainTextFormat) {
            this.subDomainTextFormat = subDomainTextFormat;
            return this;
        }

        public LayoutBuilder range(int range) {
            this.range = range;
            return this;
        }

        public LayoutBuilder highlight(LocalDate highlight) {
            this.highlight = highlight;
            return this;
        }


        public Layout build() {
            return new CalHeatmapLayout(this);
        }

    }

}
