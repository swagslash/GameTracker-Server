package io.swagslash.gametrackerserver.util;

import io.swagslash.gametrackerserver.dto.webapp.TagDTO;
import io.swagslash.gametrackerserver.model.Tag;

import java.util.ArrayList;
import java.util.List;

public class EntityDTOConverter {

    private EntityDTOConverter() {}

    public static List<TagDTO> tagListToDTO (List<Tag> tags) {
        List<TagDTO> dtos = new ArrayList<>();
        for (Tag tag : tags) {
            dtos.add(new TagDTO(tag.getName(), tag.getSlug()));
        }

        return dtos;
    }
}
