package io.swagslash.gametrackerserver.igdbconsumer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;

import java.util.Arrays;

/**
 * Minimalistic model of a Game
 * from IGDB
 *
 * @Author: Christoph Wedenig (christoph@wedenig.org)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IGDBGame {
    private int id;
    private String name;
    private int cover;
    private int[] genres;
    private int[] game_modes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCover() {
        return cover;
    }

    public void setCover(int cover) {
        this.cover = cover;
    }

    public int[] getGame_modes() {
        return game_modes;
    }

    public void setGame_modes(int[] game_modes) {
        this.game_modes = game_modes;
    }

    public int[] getGenres() {
        return genres;
    }

    public void setGenres(int[] genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "IGDBGame{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cover=" + cover +
                ", genres=" + Arrays.toString(genres) +
                ", game_modes=" + Arrays.toString(game_modes) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IGDBGame igdbGame = (IGDBGame) o;
        return id == igdbGame.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
