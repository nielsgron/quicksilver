/*
 * Copyright 2018-2019 Niels Gron and Contributors All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package quicksilver.webapp.simpleui.bootstrap4.charts;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import quicksilver.webapp.simpleui.html.components.HTMLText;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.api.TreemapPlot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.components.Margin;

public class TSTreeMapChartPanel extends TSFigurePanel {

    public enum ColumnRelation {
        FamilyTree,
        SubCategories,
    }

    static Layout defaultLayout( int width, int height, boolean enableLegend) {
        int marginLeft = 0; //40;
        int marginRight = 0; //5;
        int marginBottom = 0; //30;
        int marginTop = 0; //5;

        //default margins are huge for small dimensions. leave 10% each side but no more than 50px
        int margin = Math.min(50, Math.min(width, height) / 10);
        return Layout.builder("")
                .width(width)
                .height(height)
                .margin(Margin.builder()
                        .top(marginTop)
                        .bottom(marginBottom)
                        .left(marginLeft)
                        .right(marginRight)
                        .build())
                .showLegend(enableLegend)
                .build();
    }

    private TSTreeMapChartPanel(Builder builder) {
        this(builder.layout, builder.table, builder.divName,
                builder.familyTree ? ColumnRelation.FamilyTree : ColumnRelation.SubCategories,
                builder.columns, new HashMap<>(builder.attributes), new HashMap<>(builder.attributeDefaults));
    }

    public TSTreeMapChartPanel(Layout layout, Table table, String divName, String... columns) {
        this(layout, table, divName, ColumnRelation.SubCategories, columns, Collections.EMPTY_MAP,  Collections.EMPTY_MAP);
    }

    private TSTreeMapChartPanel(Layout layout, Table table, String divName, ColumnRelation relationship, String[] columns, Map<String, String> attColumns, Map<String, Object> attDefaults) {
        super(divName);

        Figure figure = null;
        try {
            figure = TreemapPlot.create(layout, table, relationship == ColumnRelation.FamilyTree, columns, attColumns, attDefaults);
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        HTMLText divS = new HTMLText(figure.divString(divName));
        HTMLText divJS = new HTMLText(figure.asJavascript(divName));

        this.add(divS);
        this.add(divJS);
    }

    public TSTreeMapChartPanel(Table table, String divName, int width, int height, boolean enableLegend, String... columns) {
        this(defaultLayout(width, height, enableLegend), table, divName, ColumnRelation.SubCategories, columns, Collections.EMPTY_MAP, Collections.EMPTY_MAP);
    }

    public static Layout.LayoutBuilder createLayoutBuilder(int width, int height, boolean enabledLegend) {
        return createLayoutBuilder(width, height, 0, 0, 0, 0, enabledLegend);
    }

    public static Builder builder(Table table, String divName, String... columns) {
        return new Builder(table, divName, columns);
    }

    public static class Builder {

        private final Table table;
        private final String divName;
        private final String[] columns;
        private Layout layout;
        private boolean familyTree;
        private final Map<String, String> attributes = new HashMap<>();
        private final Map<String, Object> attributeDefaults = new HashMap<>();

        private Builder(Table table, String divName, String[] columns) {
            this.table = table;
            this.divName = divName;
            this.columns = columns;
        }

        public Builder layout(Layout layout) {
            this.layout = layout;
            return this;
        }

        public Builder familyTree(boolean familyTree) {
            this.familyTree = familyTree;
            return this;
        }

        public Builder addAttribute(String attribute, String column, Object defaultValue) {
            attributes.put(attribute, column);
            attributeDefaults.put(attribute, defaultValue);
            return this;
        }

        public TSTreeMapChartPanel build() {
            return new TSTreeMapChartPanel(this);
        }

    }

}
