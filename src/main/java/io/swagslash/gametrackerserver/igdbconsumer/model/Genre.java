package io.swagslash.gametrackerserver.igdbconsumer.model;

public class Genre {
    String name;
    String slug;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Override
    public String toString() {
        return "{ Genre: " + this.name + " }";
    }
}
