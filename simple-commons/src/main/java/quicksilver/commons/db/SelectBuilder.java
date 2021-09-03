package quicksilver.commons.db;

import spark.utils.StringUtils;

public class SelectBuilder {

    String columnNames = "*";
    String tableNames = "";
    String whereClause = null;
    String groupBy = null;
    String orderBy = null;
    String limit = null;

    public SelectBuilder() {

    }

    public SelectBuilder select() {

        return this;
    }

    public SelectBuilder select(String columnNames) {
        this.columnNames = columnNames;
        return this;
    }

    public SelectBuilder from(String tableNames) {
        this.tableNames = tableNames;
        return this;
    }

    public SelectBuilder where(String whereClause) {
        this.whereClause = whereClause;
        return this;
    }

    public SelectBuilder whereAppend(String whereClause) {
        if ( StringUtils.isBlank(this.whereClause) ) {
            this.whereClause = whereClause;
        } else {
            this.whereClause = this.whereClause + " " + whereClause;
        }
        return this;
    }

    public SelectBuilder whereColumnByValue(String columnName, String columnRowValue) {
        if ( !StringUtils.isBlank(columnName) && !StringUtils.isBlank(columnRowValue) ) {
            this.whereClause = columnName + " = '" + columnRowValue + "'";
        }
        return this;
    }

    public SelectBuilder whereOrByColumnAndValue(String columnName, String[] columnRowValues) {

        if ( columnRowValues != null ) {
            StringBuilder whereBuilder = new StringBuilder();
            for (int i = 0; i < columnRowValues.length; i++) {
                if ( !StringUtils.isBlank(columnRowValues[i]) ) {
                    if ( whereBuilder.length() > 0 ) {
                        whereBuilder.append(" OR ");
                    }
                    whereBuilder.append(columnName + " = '" + columnRowValues[i] + "'");
                }
            }
            whereClause = whereBuilder.toString();
        }

        return this;
    }

    public SelectBuilder groupBy(String groupBy) {
        this.groupBy = groupBy;
        return this;
    }

    public SelectBuilder orderBy(String orderBy, boolean ascending) {
        if ( ascending ) {
            this.orderBy = orderBy + " ASC";
        } else {
            this.orderBy = orderBy + " DESC";
        }
        return this;
    }

    public SelectBuilder orderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public SelectBuilder limit(String limit) {
        this.limit = limit;
        return this;
    }

    public String build() {
        return toString();
    }
    public String toString() {

        StringBuilder query = new StringBuilder();

        query.append("SELECT ").append(columnNames);
        query.append(" FROM ").append(tableNames);
        if ( !StringUtils.isBlank(whereClause) ) {
            query.append(" WHERE ").append(whereClause);
        }
        if ( !StringUtils.isBlank(groupBy ) ) {
            query.append(" GROUP BY ").append(groupBy);
        }
        if ( !StringUtils.isBlank(orderBy) ) {
            query.append(" ORDER BY ").append(orderBy);
        }
        if ( !StringUtils.isBlank(limit) ) {
            query.append(" LIMIT ").append(limit);
        }

        return query.toString();
    }

}
