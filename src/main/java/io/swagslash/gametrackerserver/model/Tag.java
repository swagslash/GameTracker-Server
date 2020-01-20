package io.swagslash.gametrackerserver.model;

import io.swagslash.gametrackerserver.model.ms.TagMS;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Combines IGDBGameMode and IGDBGenre for better handling
 */
@Entity
@Table(name = "Tag")
public class Tag extends TagMS {

    @ManyToMany(mappedBy = "tags",
            cascade = CascadeType.ALL)
    private List<Game> games;

    public Tag() {
        games = new ArrayList<>();
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
