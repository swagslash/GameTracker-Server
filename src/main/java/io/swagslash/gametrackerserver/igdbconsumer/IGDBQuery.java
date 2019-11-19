package io.swagslash.gametrackerserver.igdbconsumer;

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

    public void setFields(List<String> fields) {
        this.fields = fields;
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("fields ");
        if (fields.isEmpty()) {
            builder.append("*");
        } else {
            for (int i = 0; i < fields.size(); i++) {
                if (i == fields.size() - 1) {
                    builder.append(fields.get(i) + "");
                } else {
                    builder.append(fields.get(i) + ", ");
                }
            }
        }
        builder.append(";");
        if (!where.isBlank()) {
            builder.append("where ").append(where.trim()).append(";");
        }
        if (!search.isBlank()) {
            builder.append("search \"").append(search.trim()).append("\";");
        }
        if (!sort.isBlank()) {
            builder.append("sort ").append(sort.trim()).append(desc ? "desc" : "asc").append(";");
        }
        return builder.toString();
    }
}
