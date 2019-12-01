package io.swagslash.gametrackerserver.dto.webapp;


import java.util.ArrayList;
import java.util.List;

/**
 * DTO for delivering data to Frontend
 */
public class GameDTO {

    private String name;

    private String imageId;

    private List<GameDTO> genres;

    private List<GameDTO> gamemodes;

    public GameDTO(String name, String imageId) {
        this.name = name;
        this.imageId = imageId;
        this.gamemodes = new ArrayList<>();
        this.genres = new ArrayList<>();
    }

    public GameDTO(String name, String imageId, List<GameDTO> genres, List<GameDTO> gamemodes) {
        this.name = name;
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

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public List<GameDTO> getGenres() {
        return genres;
    }

    public void setGenres(List<GameDTO> genres) {
        this.genres = genres;
    }

    public List<GameDTO> getGamemodes() {
        return gamemodes;
    }

    public void setGamemodes(List<GameDTO> gamemodes) {
        this.gamemodes = gamemodes;
    }
}
