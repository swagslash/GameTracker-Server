package io.swagslash.gametrackerserver.model.ms;

import io.swagslash.gametrackerserver.enums.TagTypeEnum;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
public class TagMS implements Serializable {

    @Id
    @GeneratedValue
    private Long tagId;

    @NotBlank
    private String name;

    @NotBlank
    private String slug;

    @NotNull
    @Enumerated
    private TagTypeEnum type;

    public Long getTagId() {
        return tagId;
    }

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

    public TagTypeEnum getType() {
        return type;
    }

    public void setType(TagTypeEnum type) {
        this.type = type;
    }
}
