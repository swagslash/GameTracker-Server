package io.swagslash.gametrackerserver.igdbconsumer;

import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a query for use for IGDBWrapper
 * Result is in apicalypse query language https://apicalypse.io
 *
 * @Author: Christoph Wedenig (christoph@wedenig.org)
 */
public class IGDBQuery {
    private List<String> fields;
    private String where;
    private String search;
    private String sort;
    private boolean desc = true;

    public IGDBQuery() {
        fields = new ArrayList<>();
        where = "";
        search = "";
        sort = "";
    }

    public List<String> getFields() {
        return fields;
    }

    public void addField(String field) {
        this.fields.add(field);
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public void setSort(String sort, boolean desc) {
        this.sort = sort;
        this.desc = desc;
    }

    public String getSort() {
        return sort;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String fieldQuery() {
        String chosenFields = fields.isEmpty() ? "*" : Strings.join(fields, ',');
        return "fields " + chosenFields + ";";
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(fieldQuery());
        if (!where.isBlank()) {
            builder.append("where ").append(where.trim()).append(";");
        }
        if (!search.isBlank()) {
            builder.append("search \"").append(search.trim()).append("\";");
        }
        if (!sort.isBlank()) {
            builder.append("sort ").append(sort.trim()).append(desc ? " desc" : " asc").append(";");
        }
        return builder.toString();
    }
}
