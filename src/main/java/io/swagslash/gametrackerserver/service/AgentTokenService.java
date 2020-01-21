package io.swagslash.gametrackerserver.service;

import io.swagslash.gametrackerserver.exception.BadRequestException;
import io.swagslash.gametrackerserver.model.User;

public interface AgentTokenService {

    boolean isValidAgentKey(String key) throws BadRequestException;

    String getAgentToken() throws BadRequestException;

    User getUserByToken(String token);
}
