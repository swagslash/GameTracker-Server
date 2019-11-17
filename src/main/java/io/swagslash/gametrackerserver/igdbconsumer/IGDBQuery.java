package io.swagslash.gametrackerserver.igdbconsumer;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a query for use for IGDBWrapper
 *
 * @Author: Christoph Wedenig (christoph@wedenig.org)
 */
public class IGDBQuery {
    private List<String> fields;
    private String search;

    public IGDBQuery() {
        fields = new ArrayList<>();
        search = "";
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
        if (!search.isBlank()) {
            builder.append("search ");
            builder.append('"' + search + '"');
            builder.append(';');
        }
        return builder.toString();
    }
}
