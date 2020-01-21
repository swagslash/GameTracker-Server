package io.swagslash.gametrackerserver.endpoint.agent.controller;

import io.swagslash.gametrackerserver.dto.agent.AgentGame;
import io.swagslash.gametrackerserver.exception.BadRequestException;
import io.swagslash.gametrackerserver.service.AgentTokenService;
import io.swagslash.gametrackerserver.service.AuthenticationService;
import io.swagslash.gametrackerserver.service.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/agent")
public class AgentController {

    AuthenticationService authenticationService;

    AgentTokenService tokenService;

    GameService gameService;

    public AgentController(AuthenticationService authenticationService, GameService gameService, AgentTokenService tokenService) {
        this.authenticationService = authenticationService;
        this.gameService = gameService;
        this.tokenService = tokenService;
    }

    @PostMapping("/{key}")
    public String indexGames(@PathVariable String key, @RequestBody ArrayList<AgentGame> games) {
        if(!tokenService.isValidAgentKey(key)){
            throw new BadRequestException("Invalid key");
        }
        gameService.markGamesAsOwnedAgent(games, key);

        return "success";
    }
}
