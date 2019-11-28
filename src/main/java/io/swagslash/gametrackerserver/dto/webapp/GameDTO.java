package io.swagslash.gametrackerserver.dto.webapp;

/**
 * DTO for delivering data to Frontend
 */
public class GameDTO {

    private String name;
    private String imageId;

    public GameDTO(String name, String imageId) {
        this.name = name;
        this.imageId = imageId;
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
}
