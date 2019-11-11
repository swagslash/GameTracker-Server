package io.swagslash.gametrackerserver.dao;

import io.swagslash.gametrackerserver.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TagRepository extends JpaRepository<Tag, UUID> {
}
