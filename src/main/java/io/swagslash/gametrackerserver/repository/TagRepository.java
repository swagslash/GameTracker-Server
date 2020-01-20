package io.swagslash.gametrackerserver.repository;

import io.swagslash.gametrackerserver.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
