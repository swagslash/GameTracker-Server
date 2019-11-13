package io.swagslash.gametrackerserver.repository;

import io.swagslash.gametrackerserver.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GameRepository extends JpaRepository<Game, UUID> {
}
