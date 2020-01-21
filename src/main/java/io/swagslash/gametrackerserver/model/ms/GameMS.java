package io.swagslash.gametrackerserver.model.ms;

import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@MappedSuperclass
public class GameMS implements Serializable {

    @Id
    @GeneratedValue
    private Long gameId;

    @Unique
    private int dbGameId;

    @NotBlank
    private String name;

    private String imageId;

    public Long getGameId() {
        return gameId;
    }

    public int getDbGameId() {
        return dbGameId;
    }

    public void setDbGameId(int dbGameId) {
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
