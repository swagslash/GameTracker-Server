package io.swagslash.gametrackerserver.igdbconsumer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Minimalistic model of a Game
 * from IGDB
 *
 * @Author: Christoph Wedenig (christoph@wedenig.org)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Game {
    private int id;
    private String name;
    private Integer cover;
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

    @Override
    public String toString() {
        return "{ ID: " + id + "; Name: " + name + "; Cover: " + cover + " }";
    }
}
