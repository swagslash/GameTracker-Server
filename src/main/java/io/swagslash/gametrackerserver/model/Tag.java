package io.swagslash.gametrackerserver.model;

import io.swagslash.gametrackerserver.model.ms.TagMS;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Tag")
public class Tag extends TagMS {

    @ManyToMany
    @JoinTable(
            name = "game_tag",
            joinColumns = @JoinColumn(name = "dbTagId"),
            inverseJoinColumns = @JoinColumn(name = "dbGameId")
    )
    List<Game> games;

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
