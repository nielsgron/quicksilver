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

package quicksilver.webapp.simpleui.bootstrap4.components;

import quicksilver.commons.data.DataSet;
import quicksilver.webapp.simpleui.HtmlStream;

import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/*
    Example :

    W3Schools : https://www.w3schools.com/bootstrap4/bootstrap_tables.asp
    Bootstrap Docs : https://getbootstrap.com/docs/4.1/content/tables/
 */

public class BSTable extends BSComponentContainer {

    private String title;
    private DataSet dataSet;
    private TableCellRenderer[] renderers;
    private boolean showCaption = true;
    private boolean showHeader = true;
    private int rowLimit = -1;

    public BSTable(DataSet ds, String t) {
        this(ds,t,false,true);
    }

    public BSTable(DataSet ds, String t, boolean showCaption, boolean showHeader) {
        this.showCaption = showCaption;
        this.showHeader = showHeader;
        this.dataSet = ds;
        this.title = t;
        this.renderers = new TableCellRenderer[dataSet.getColumnCount()];

    }

    protected void defineAttributes() {

        putComponentAttribute(COMPONENT_ATTRIB_NAME, "Table");
        putComponentAttribute(COMPONENT_ATTRIB_TAG_CLOSE, Boolean.TRUE);
        putComponentAttribute(COMPONENT_ATTRIB_TAG_NAME, "table");

        addTagAttribute("class", "table table-sm");
        addTagAttribute("style", getStyle());

    }

    public void setTableCellRenderer(TableCellRenderer renderer, int column) {
        renderers[column] = renderer;
    }

    public void setRowLimit(int rowLimit) {
        this.rowLimit = rowLimit;
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    protected String getStyle() {
        return "margin-bottom: 0rem;";
    }

    public void renderBody(HtmlStream stream) {

        StringBuilder html = new StringBuilder();

        if ( showCaption ) {
            if (title != null && title.trim().length() > 0) {
                html.append("<caption>");
                html.append(title);
                html.append("</caption>");
            }
        }

        if ( showHeader ) {
            html.append("<thead>");
            html.append("<tr>");
            for (int j = 0; j < dataSet.getColumnCount(); j++) {
                // Add Column
                String align = "left";
                Class valClass = dataSet.getColumnType(j);
                if (renderers[j] != null) {
                    align = renderers[j].titleAlignment(valClass);
                } else if (Number.class.isAssignableFrom(valClass)) {
                    align = "right";
                }

                html.append("<th scope=\"col\" style=\"text-align: " + align + ";\">");
                html.append(dataSet.getColumnName(j));
                html.append("</th>");
            }
            html.append("</tr>");
            html.append("</thead>");
        }

        html.append("<tbody>");

        int maxRows = dataSet.getRowCount();
        if ( rowLimit > 0 && rowLimit < maxRows ) {
            maxRows = rowLimit;
        }

        for ( int i = 0; i < maxRows; i++ ) {
            // Add Row
            html.append("<tr>");

            for ( int j = 0; j < dataSet.getColumnCount(); j++ ) {
                // Add Column
                Object val = dataSet.getValue(j, i);
                if ( renderers[j] == null ) {
                    String align = "left";
                    if ( val instanceof Number ) {
                        align = "right";
                    } else if ( val instanceof java.util.Date ) {
                        align = "right";
                    }

                    html.append("<td style=\"text-align: " + align + ";\">");
                    html.append(val);

                } else {
                    String align = renderers[j].textAlignment(val);
                    html.append("<td style=\"text-align: " + align + ";\">");
                    html.append(renderers[j].render(i,j,val));

                }
                html.append("</td>");

            }

            // End Row
            html.append("</tr>");

        }
        html.append("</tbody>");

        stream.write(html.toString());

    }

    public static class HTMLTableCellRenderer extends DefaultTableCellRenderer {

        private String targetWindow = "_blank";
        private boolean blankTarget = false;

        public HTMLTableCellRenderer() {

        }

        public HTMLTableCellRenderer(boolean bTarget) {
            blankTarget = bTarget;
        }

        public HTMLTableCellRenderer(boolean bTarget, String tWindow) {
            blankTarget = bTarget;
            targetWindow = tWindow;
        }

        public String encode(String value) {
            try {
                return java.net.URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
            } catch ( Exception e ) {
                return value;
            }
        }

        @Override
        public String render(int row, int column, Object value) {
            if ( value == null ) {
                return null;
            }
            StringBuilder sb = new StringBuilder();

            sb.append("<a href=\"");
            sb.append(getHREF(value));
            sb.append("\"");
            if ( blankTarget ) {
                sb.append(" target=\"" + targetWindow + "\" ");
            }
            sb.append(">");
            sb.append(value.toString());
            sb.append("</a>");

            return sb.toString();
        }

        protected String getHREF(Object value) {
            return "/" + value.toString();
        }

    }

    public static class PercentTableCellRenderer extends NumberTableCellRenderer {

        private boolean multiplyValueBy100 = true;

        public PercentTableCellRenderer() {
            super(2, true, false, true);
        }

        public PercentTableCellRenderer(int numberOfDigits, boolean multiplyValueBy100) {
            super(numberOfDigits, true, false, true);
            this.multiplyValueBy100 = multiplyValueBy100;
        }

        @Override
        public String render(int row, int column, Object value) {
            if ( value == null ) {
                return null;
            }

            if ( this.multiplyValueBy100) {
                if ( value instanceof Double ) {
                    Double val = (Double)value;
                    val = val * 100;
                    value = val;
                } else if ( value instanceof Long ) {
                    Long val = (Long)value;
                    val = val * 100;
                    value = val;
                } else if ( value instanceof Integer ) {
                    Integer val = (Integer)value;
                    val = val * 100;
                    value = val;
                } else if ( value instanceof Float ) {
                    Float val = (Float)value;
                    val = val * 100;
                    value = val;
                }
            }

            return super.render(row, column, value);
        }

    }

    public static class NumberTableCellRenderer extends DefaultTableCellRenderer {

        private NumberFormat DECIMAL_FORMAT;

        private int numberOfDigits = 0;
        private boolean includeThousandSeparator = true;
        private boolean includeCurrency = false;
        private boolean includePercent = false;

        public NumberTableCellRenderer() {

        }

        public NumberTableCellRenderer(int numberOfDigits, boolean includeThousandSeparator, boolean includeCurrency, boolean includePercent) {
            this.numberOfDigits = numberOfDigits;
            this.includeThousandSeparator = includeThousandSeparator;
            this.includePercent = includePercent;

            StringBuilder pattern = new StringBuilder();
            if ( includeCurrency ) {
                pattern.append("$");
            }

            if ( includeThousandSeparator ) {
                pattern.append("###,##0");
            } else {
                pattern.append("##0");
            }

            if ( numberOfDigits < 0 ) {
                pattern.append(".########################################");
            } else if ( numberOfDigits == 0 ) {
                // no digits
            } else {
                pattern.append(".");
                for ( int i = 0; i < numberOfDigits; i++ ) {
                    pattern.append("0");
                }
            }

            DECIMAL_FORMAT = new DecimalFormat(pattern.toString());
        }

        @Override
        public String textAlignment(Object value) {
            return "right";
        }

        @Override
        public String render(int row, int column, Object value) {
            if ( value == null ) {
                return null;
            }
            StringBuilder sb = new StringBuilder();

            try {
                sb.append(DECIMAL_FORMAT.format(value));
                if (includePercent) {
                    sb.append("%");
                }
            } catch ( Exception e ) {
                sb.append("[Err:" + value.toString() + "]");
            }

            return sb.toString();
        }

        protected String getHREF(Object value) {
            return "/" + value.toString();
        }

    }

    public static abstract class DefaultTableCellRenderer implements TableCellRenderer {

        public String titleAlignment(Class valClass) {
            String align = "left";
            if ( Number.class.isAssignableFrom(valClass) ) {
                align = "right";
            }
            return align;
        }

        public String textAlignment(Object value) {
            String align = "left";
            if ( value instanceof Number ) {
                align = "right";
            } else if ( value instanceof java.util.Date ) {
                align = "right";
            }
            return align;
        }
        public abstract String render(int row, int column, Object value);

    }

    public interface TableCellRenderer {

        String titleAlignment(Class valClass);
        String textAlignment(Object value);
        String render(int row, int column, Object value);

    }

}

