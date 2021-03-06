package io.swagslash.gametrackerserver.dto.webapp;


import java.util.ArrayList;
import java.util.List;

/**
 * DTO for delivering data to Frontend
 */
public class GameDTO {

    private String dbGameId;

    private String name;

    private String imageId;

    private List<TagDTO> genres;

    private List<TagDTO> gamemodes;

    public GameDTO(String name, String dbGameId) {
        this.name = name;
        this.dbGameId = dbGameId;
        this.imageId = "";
        this.gamemodes = new ArrayList<>();
        this.genres = new ArrayList<>();
    }

    public GameDTO(String name, String dbGameId, String imageId, List<TagDTO> genres, List<TagDTO> gamemodes) {
        this.name = name;
        this.dbGameId = dbGameId;
        this.imageId = imageId;
        this.genres = genres;
        this.gamemodes = gamemodes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDbGameId() {
        return dbGameId;
    }

    public void setDbGameId(String dbGameId) {
        this.dbGameId = dbGameId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public List<TagDTO> getGenres() {
        return genres;
    }

    public void setGenres(List<TagDTO> genres) {
        this.genres = genres;
    }

    public List<TagDTO> getGamemodes() {
        return gamemodes;
    }

    public void setGamemodes(List<TagDTO> gamemodes) {
        this.gamemodes = gamemodes;
    }
}
