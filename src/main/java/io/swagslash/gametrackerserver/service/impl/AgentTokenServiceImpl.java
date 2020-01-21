package io.swagslash.gametrackerserver.service.impl;

import io.swagslash.gametrackerserver.exception.BadRequestException;
import io.swagslash.gametrackerserver.model.AgentToken;
import io.swagslash.gametrackerserver.model.User;
import io.swagslash.gametrackerserver.repository.AgentTokenRepository;
import io.swagslash.gametrackerserver.service.AgentTokenService;
import io.swagslash.gametrackerserver.service.UserService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AgentTokenServiceImpl implements AgentTokenService {

    AgentTokenRepository tokenRepository;

    UserService userService;

    public AgentTokenServiceImpl(AgentTokenRepository tokenRepository, UserService userService) {
        this.tokenRepository = tokenRepository;
        this.userService = userService;
    }

    @Override
    public boolean isValidAgentKey(String key) throws BadRequestException {
        AgentToken token = tokenRepository.findByToken(key);
        if(token == null) {
            return false;
        }

        return true;
    }

    @Override
    public String getAgentToken() throws BadRequestException {
        AgentToken token = new AgentToken();
        User user = userService.getCurrentUser();

        token.setUserId(user.getUserId());
        token.setToken(UUID.randomUUID().toString());
        tokenRepository.save(token);

        return token.getToken();
    }

    @Override
    public User getUserByToken(String token) {
       AgentToken userToken = tokenRepository.findByToken(token);

       return userToken.getUser();
    }
}
