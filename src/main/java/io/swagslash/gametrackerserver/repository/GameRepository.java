package io.swagslash.gametrackerserver.repository;

import io.swagslash.gametrackerserver.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface GameRepository extends JpaRepository<Game, UUID> {

    @Query("select g from Game g where g.dbGameId = ?1")
    public Game findByIgdbId (int igdbId);
}
