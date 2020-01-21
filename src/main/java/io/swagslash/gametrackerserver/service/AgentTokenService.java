package io.swagslash.gametrackerserver.service;

import io.swagslash.gametrackerserver.exception.BadRequestException;
import io.swagslash.gametrackerserver.model.User;

import java.util.List;

public interface AgentTokenService {

    boolean isValidAgentKey(String key);

    String getAgentToken();

    User getUserByToken(String token);

    List<String> findAllByUser();

    void removeToken(String token);
}
