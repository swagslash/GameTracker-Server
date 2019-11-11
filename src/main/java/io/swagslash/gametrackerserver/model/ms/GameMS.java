package io.swagslash.gametrackerserver.model.ms;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
public class GameMS implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID gameId;

    @NotBlank
    private UUID dbGameId;

    @NotBlank
    private String name;

    @NotBlank
    private String imageId;

    public UUID getGameId() {
        return gameId;
    }

    public UUID getDbGameId() {
        return dbGameId;
    }

    public void setDbGameId(UUID dbGameId) {
        this.dbGameId = dbGameId;
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
