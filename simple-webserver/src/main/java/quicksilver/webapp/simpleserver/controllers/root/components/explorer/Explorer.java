package quicksilver.webapp.simpleserver.controllers.root.components.explorer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import quicksilver.commons.data.TSDataSet;
import quicksilver.commons.data.TSDataSetFactory;
import quicksilver.webapp.simpleserver.controllers.root.components.AbstractComponentsPage;
import quicksilver.webapp.simpleui.bootstrap4.charts.TSFigurePanel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSBadge;
import quicksilver.webapp.simpleui.bootstrap4.components.BSCard;
import quicksilver.webapp.simpleui.bootstrap4.components.BSComponent;
import quicksilver.webapp.simpleui.bootstrap4.components.BSForm;
import quicksilver.webapp.simpleui.bootstrap4.components.BSFormButton;
import quicksilver.webapp.simpleui.bootstrap4.components.BSFormRow;
import quicksilver.webapp.simpleui.bootstrap4.components.BSInputSelect;
import quicksilver.webapp.simpleui.bootstrap4.components.BSInputText;
import quicksilver.webapp.simpleui.bootstrap4.components.BSPanel;
import quicksilver.webapp.simpleui.bootstrap4.components.BSText;
import spark.QueryParamsMap;
import tech.tablesaw.api.NumericColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.charts.ChartBuilder;
import tech.tablesaw.columns.Column;
import tech.tablesaw.plotly.Utils;
import tech.tablesaw.plotly.components.Layout;

public class Explorer extends AbstractComponentsPage {

    private final QueryParamsMap query;
    private BSPanel panel;

    public Explorer(QueryParamsMap query) {
        this.query = query;
        getComponentNavTab().setActiveItem("Data Explorer");
        //continue creating panel (workaround)
        createContentPanelCenter();
    }

    public String getTitle() {
        return "Data Explorer";
    }

    protected BSPanel createContentPanelCenter() {
        if (panel == null) {
            panel = new BSPanel();
            //1st call, from constructor?!
            //TODO: EMI: This is a hack, fix the underlying problem on how this method gets called from the constructor
            return panel;
        }

        List<Method> datasetSampleMethods = new ArrayList<>();
        for (Method m : TSDataSetFactory.class.getDeclaredMethods()) {
            if (Modifier.isStatic(m.getModifiers()) && m.getName().startsWith("createSample") && m.getParameterCount() == 0) {
                datasetSampleMethods.add(m);
            }
        }
        datasetSampleMethods.sort((o1, o2) -> {
            return o1.getName().compareTo(o2.getName());
        });

        String[] datasets = datasetSampleMethods.stream()
                .map(m -> m.getName().substring("createSample".length()))
                .toArray(String[]::new);

        BSForm form = new BSForm("/components/explorer", true);
        panel.add(form);
        {
            BSFormRow row = new BSFormRow(2);
            row.getColumn(0).add(new BSText("<b>Dataset</b>:"));
            row.getColumn(1).add(new BSInputSelect("dataset", false, datasets));
            //TODO: restore dataset selection
            form.add(row);
        }

        Method sampleMethod = datasetSampleMethods.get(0);
        if (query != null && query.hasKey("dataset")) {
            String dataset = query.get("dataset").value();
            for (int i = 0; i < datasets.length; i++) {
                if (datasets[i].equals(dataset)) {
                    //found it
                    sampleMethod = datasetSampleMethods.get(i);
                }
            }
        }
        Table table;
        try {
            table = ((TSDataSet) sampleMethod.invoke(null)).getTSTable();
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            //shouldn't happen, but add a fallback
            table = TSDataSetFactory.createSampleCountryEconomicData().getTSTable();
        }
        {
            BSFormRow row = new BSFormRow(table.columnCount() + 1);
            row.getColumn(0).add(new BSText("<b>Dataset structure</b>:"));
            int i = 1;
            for (Column c : table.columns()) {
                BSBadge badge;
                if (c instanceof NumericColumn) {
                    badge = new BSBadge(c.name(), BSComponent.Type.SECONDARY);
                } else {
                    badge = new BSBadge(c.name(), BSComponent.Type.PRIMARY);
                }
                row.getColumn(i++).add(badge);
            }
            form.add(row);
        }
        {
            BSFormRow row = new BSFormRow(4);
            row.getColumn(0).add(new BSText("<b>Columns</b>:"));
            BSInputText colsInput;
            row.getColumn(1).add(colsInput = new BSInputText("Columns", "Columns", "", "columns"));

            if (query != null && query.hasKey("columns")) {
                colsInput.setValue(query.get("columns").value());
            }

            row.getColumn(2).add(new BSText("<b>Rows</b>:"));
            BSInputText rowsInput;
            row.getColumn(3).add(rowsInput = new BSInputText("Rows", "Rows", "", "rows"));

            if (query != null && query.hasKey("rows")) {
                rowsInput.setValue(query.get("rows").value());
            }

            form.add(row);
        }
        {
            BSFormRow row = new BSFormRow(4);
            row.getColumn(0).add(new BSText("<b>Color</b>:"));
            BSInputText colorInput;
            row.getColumn(1).add(colorInput = new BSInputText("Color", "Color", "", "color"));

            if (query != null && query.hasKey("color")) {
                colorInput.setValue(query.get("color").value());
            }

            row.getColumn(2).add(new BSText("<b>Size</b>:"));
            BSInputText sizeInput;
            row.getColumn(3).add(sizeInput = new BSInputText("Size", "Size", "", "size"));

            if (query != null && query.hasKey("size")) {
                sizeInput.setValue(query.get("size").value());
            }

            form.add(row);
        }
        {
            BSFormRow row = new BSFormRow(4);
            row.getColumn(0).add(new BSText("<b>Trace colors</b>:"));
            BSInputText tracecolors;
            row.getColumn(1).add(tracecolors = new BSInputText("", "TraceColor", "", "tracecolors"));

            if (query != null && query.hasKey("tracecolors")) {
                tracecolors.setValue(query.get("tracecolors").value());
            }

            form.add(row);
        }
        {
            BSFormRow row = new BSFormRow(4);
            row.getColumn(0).add(new BSText("<b>Chart</b>:"));
            //TODO: restore chart type selection
            row.getColumn(1).add(new BSInputSelect("chartType", false,
                    Stream.of(ChartBuilder.CHART_TYPE.values()).map(t -> t.name()).toArray(String[]::new)));

            row.getColumn(2).add(new BSText("<b>Options</b>:"));
            BSInputText optionsInput;
            row.getColumn(3).add(optionsInput = new BSInputText("Options", "Options", "", "options"));

            if (query != null && query.hasKey("options")) {
                optionsInput.setValue(query.get("options").value());
            }

            form.add(row);
        }
        {
            BSFormRow row = new BSFormRow(4);
            row.getColumn(0).add(new BSText("<b>Axes</b>:"));
            BSInputSelect axes;
            row.getColumn(1).add(axes = new BSInputSelect("axes", false,
                    Stream.of(ChartBuilder.Axes.values()).map(t -> t.name()).toArray(String[]::new)));

            if (query != null && query.hasKey("axes")) {
                //TODO: restore axes type selection
                //axes.setValue(query.get("axes").value());
            }

            form.add(row);
        }        {
            form.add(new BSFormButton("Submit"));
        }

        ChartBuilder.CHART_TYPE chartType = ChartBuilder.CHART_TYPE.VERTICAL_BAR;
        if (query != null && query.hasKey("chartType")) {
            String chart = query.get("chartType").value();
            chartType = ChartBuilder.CHART_TYPE.valueOf(chart);
        }

        ChartBuilder chartBuilder = ChartBuilder.createBuilder()
                .dataTable(table);

        StringBuilder generatedCode = new StringBuilder();
        generatedCode.append("ChartBuilder chartBuilder = ChartBuilder.createBuilder()\n"
                + "  .dataTable(table)\n"
                + "  .chartType(ChartBuilder.CHART_TYPE." + chartType.name()
                );

        List<Object> chartTypeOptions = new ArrayList<>();
        if (query != null && query.hasKey("options")) {
            String options = query.get("options").value().trim();
            if (!options.isEmpty()) {
                for (String name : options.split(" ")) {
                    name = name.trim();
                    if (name.isEmpty()) {
                        continue;
                    }
                    try {
                        Layout.BarMode b = Layout.BarMode.valueOf(name.toUpperCase());
                        chartTypeOptions.add(b);
                        
                        generatedCode.append(", Layout.BarMode.").append(b.name());
                    } catch (IllegalArgumentException iae) {
                        //ignore, continue
                    }
                }
            }
        }
        generatedCode.append(")\n");

        chartBuilder.chartType(chartType, chartTypeOptions.toArray())
                .layout(500, 200, false);

        generatedCode.append("  .layout(500, 200, false);\n");

        if (query != null && query.hasKey("axes")) {
            String axes = query.get("axes").value().trim();
            if (!axes.isEmpty()) {
                try {
                    ChartBuilder.Axes axesType = ChartBuilder.Axes.valueOf(axes);
                    chartBuilder.axesType(axesType);

                    //is it the default axes type?
                    if (axesType != ChartBuilder.DEFAULT_AXES) {
                        generatedCode.append("  .axesType(ChartBuilder.Axes.").append(axesType.name()).append(")\n");
                    }
                } catch (IllegalArgumentException iae) {
                    //ignore, continue
                }
            }
        }

        if (query != null && query.hasKey("columns")) {
            String[] cols
                    = query.get("columns").value().trim().split(" ");
            chartBuilder.columnsForViewColumns(cols);

            generatedCode.append("  .columnsForViewColumns(");
            generatedCode.append(dataAsString(cols));
            generatedCode.append(")\n");
        }
        if (query != null && query.hasKey("rows")) {
            String[] rows
                    = query.get("rows").value().trim().split(" ");
            chartBuilder.columnsForViewRows(rows);

            generatedCode.append("  .columnsForViewRows(");
            generatedCode.append(dataAsString(rows));
            generatedCode.append(")\n");
        }
        if (query != null && query.hasKey("color")) {
            String color
                    = query.get("color").value();
            if (!color.isEmpty()) {
                chartBuilder.columnForColor(color);

                generatedCode.append("  .columnForColor(");
                generatedCode.append(dataAsString(new String[]{color}));
                generatedCode.append(")\n");
            }
        }
        if (query != null && query.hasKey("size")) {
            String size
                    = query.get("size").value();
            if (!size.isEmpty()) {
                chartBuilder.columnForSize(size);

                generatedCode.append("  .columnForSize(");
                generatedCode.append(dataAsString(new String[]{size}));
                generatedCode.append(")\n");
            }
        }
        if (query != null && query.hasKey("tracecolors")) {
            String tracecolors
                    = query.get("tracecolors").value();
            if (!tracecolors.isEmpty()) {
                String[] colors = Stream.of(tracecolors.split(" "))
                        .filter(x -> !x.isEmpty())
                        .toArray(String[]::new);
                chartBuilder.setTraceColors(colors);

                generatedCode.append("  .setTraceColors(");
                generatedCode.append(dataAsString(colors));
                generatedCode.append(")\n");
            }
        }
        panel.add(
                new BSCard(new TSFigurePanel(chartBuilder.divName("chart").build(), "chart"),
                        "Chart")
        );

        panel.add(new BSText("<br/>"));
        panel.add(new BSText("<pre>" + generatedCode + "</pre>"));

        return panel;
    }

    static String dataAsString(String[] x) {
        String das = Utils.dataAsString(x);
        das = das.substring(1); //remove first '['
        das = das.substring(0, das.length() - 1); //remove last '['
        return das;
    }
}
