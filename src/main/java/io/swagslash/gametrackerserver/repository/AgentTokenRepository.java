package io.swagslash.gametrackerserver.repository;

import io.swagslash.gametrackerserver.model.AgentToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AgentTokenRepository extends JpaRepository<AgentToken, UUID> {

    AgentToken findByToken(String token);

    List<AgentToken> findAllByUser(UUID userId);
}
