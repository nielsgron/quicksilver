package tech.tablesaw.plotly.api;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import tech.tablesaw.aggregate.AggregateFunction;
import tech.tablesaw.aggregate.AggregateFunctions;
import tech.tablesaw.api.ColumnType;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.columns.Column;
import tech.tablesaw.columns.strings.StringColumnType;

public class TableExtract {

    static String[] _cols(String n, String[] list) {
        String[] copy = Arrays.copyOf(list, list.length + 1);
        copy[copy.length - 1] = n;
        return copy;
    }

    static String[] _cols(String[] n, String[] list) {
        String[] copy = Arrays.copyOf(list, list.length + n.length);
        System.arraycopy(n, 0, copy, list.length, n.length);
        return copy;
    }

    // SUM [abc] -> abc
    // Xyz -> Xyz
    public static String measure(String m) {
        Matcher matcher = Pattern.compile("([^\\[]+) \\[([^\\]]+)\\]").matcher(m);
        if (matcher.matches()) {
            String group = matcher.group(2);
            return group;
        } else {
            return m;
        }
    }

    static String[] _measures(String[] m) {
        return Stream.of(m)
                .map(TableExtract::measure)
                .toArray(String[]::new);
    }

    public static AggregateFunction agg(String m) {
        Matcher matcher = Pattern.compile("([^\\[]+) \\[([^\\]]+)\\]").matcher(m);
        if (matcher.matches()) {
            String n = matcher.group(1);

            if ("ZERO".equals(n)) {
                return zero;
            }

            for (Field f : AggregateFunctions.class.getDeclaredFields()) {
                if (Modifier.isStatic(f.getModifiers()) && Modifier.isPublic(f.getModifiers())) {
                    if (AggregateFunction.class.isAssignableFrom(f.getType())) {
                        try {
                            AggregateFunction func = (AggregateFunction) f.get(null);
                            if (func.functionName().toUpperCase().equals(n.toUpperCase())) {
                                return func;
                            }
                        } catch (IllegalArgumentException | IllegalAccessException ex) {
                            //ignore
                        }
                    }
                }
            }
        }
        return null;
    }

    static AggregateFunction[] _agg(String[] m) {
        return Stream.of(m)
                .map(TableExtract::agg)
                .filter(x -> x != null)
                .toArray(AggregateFunction[]::new);
    }

    static int indexOf(String n, String[] list) {
        return Arrays.asList(list).indexOf(n);
    }

    public static Table aggregate(Table treemapTable, String[] hierarchy, String[] extraCols) {

        Table view = treemapTable.select(_cols(hierarchy, _measures(extraCols))).copy();

        //create IDs for each column in the hierarchy
        Map<String, StringColumn> idColumns = new HashMap<>();
        for (int i = 0; i < hierarchy.length; i++) {
            String col = hierarchy[i];

            final int index = i;
            StringColumn idColumn = view.stringColumn(col).map((t) -> {
                return index + "-" + t;
            });
            idColumn.setName("ids");

            idColumns.put(col, idColumn);
        }

        Table previous = null;

        //all views appended into this one
        // structure: ids, Label, Parent(with IDS), Change, ChangeAsNumber, MarketCap
        Table all = null;

        //create views
        for (int i = 0; i < hierarchy.length; i++) {
            String col = hierarchy[i];

            if (previous == null) {
                String parentCol = hierarchy[i + 1];

                Table currentColumnView = view.select(_cols(col, _measures(extraCols)));
                currentColumnView.addColumns(idColumns.get(col),
                        idColumns.get(parentCol).copy().setName("Parent"));
                currentColumnView.stringColumn(col).setName("Label");
                // --> [ids, Label (ie. Ticker), Parent (ie. Industry ids), Change, ChangeAsNumber, MarketCap]

                previous = currentColumnView;

                //init all
                all = currentColumnView.emptyCopy().append(currentColumnView);

                continue;
            }

            //aggregate Industry MarketCap and ChangeAsNumber
            Table aggregationOnPrevious = null;

            if (extraCols.length > 0) {
                aggregationOnPrevious = previous.summarize(Arrays.asList(_measures(extraCols)),
                        _agg(extraCols)).by("Parent");
                aggregationOnPrevious.column("Parent").setName("ids");
                aggregationOnPrevious.columns().forEach(c -> {
                    //find SUM[x] and rename to 'x'
                    //MEAN [ChangeAsNumber] -> ChangeAsNumber
                    int match = indexOf(measure(c.name()), _measures(extraCols));
                    if (match != -1) {
                        c.setName(measure(extraCols[match]));
                    }
                });
                aggregationOnPrevious = aggregationOnPrevious.select(_cols("ids", _measures(extraCols)));
                // --> [ids (ie. Industry ids), ChangeAsNumber, MarketCap]
            }

            //System.out.println(industryAggregation);
            Table currentColumnView = view.select(col);
            currentColumnView.stringColumn(col).setName("Label");
            currentColumnView.addColumns(idColumns.get(col));
            if (i != hierarchy.length - 1) {
                String parentCol = hierarchy[i + 1];

                currentColumnView.addColumns(idColumns.get(parentCol).copy().setName("Parent"));
            } else {
                //last one has no parent
                currentColumnView.addColumns(view.stringColumn("Label").map((t) -> {
                    //no parent
                    return "";
                }).setName("Parent"));
            }
            // [ids (. ie Industry ids), Label (ie. Industry), Parent (ie. Sector ids)]

            Table viewWithAggregations
                    = extraCols.length > 0 ? currentColumnView.joinOn("ids").inner(aggregationOnPrevious) : currentColumnView;
            // [ids (. ie Industry ids), Label (ie. Industry), Parent (ie. Sector ids), ChangeAsNumber, MarketCap]
            //System.out.println(industrySectorView);

            previous = viewWithAggregations;

            all = all.append(viewWithAggregations);
        }

        //System.out.println(all.toString());
        return all;
    }

    static AggregateFunction<DoubleColumn, Double> zero = new AggregateFunction<DoubleColumn, Double>("Zero") {
        @Override
        public Double summarize(DoubleColumn column) {
            return 0d;
        }

        @Override
        public boolean isCompatibleColumn(ColumnType type) {
            return type == ColumnType.DOUBLE;
        }

        @Override
        public ColumnType returnType() {
            return ColumnType.DOUBLE;
        }
    };

    static AggregateFunction<StringColumn, String> firstString = new AggregateFunction<StringColumn, String>("First") {
        @Override
        public String summarize(StringColumn v) {
            return v.isEmpty() ? StringColumnType.missingValueIndicator() : v.get(0);
        }

        @Override
        public boolean isCompatibleColumn(ColumnType type) {
            return type == returnType();
        }

        @Override
        public ColumnType returnType() {
            return ColumnType.STRING;
        }
    };

    public static Table unique(Table treemapTable, String idCol) {
        List<Column<?>> columns = treemapTable.columns().stream().filter(c -> !idCol.equals(c.name())).collect(Collectors.toList());
        List<String> columnNames = columns.stream().map(Column::name).collect(Collectors.toList());
        //System.out.println(columnNames);
        //TODO: what about date columns?
        treemapTable = treemapTable.summarize(columnNames, AggregateFunctions.first, firstString).by(idCol);
        treemapTable.columns().forEach(c -> {
            c.setName(measure(c.name()));
        });

        return treemapTable;
    }

}
