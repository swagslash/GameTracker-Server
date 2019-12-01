package io.swagslash.gametrackerserver.endpoint.agent.controller;

import io.swagslash.gametrackerserver.exception.BadRequestException;
import io.swagslash.gametrackerserver.dto.agent.AgentGame;
import io.swagslash.gametrackerserver.service.AuthenticationService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/agent")
public class AgentController {

    AuthenticationService authenticationService;

    public AgentController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/{key}")
    public String indexGames(@PathVariable String key, @RequestBody ArrayList<AgentGame> games) {
        if(!authenticationService.isValidAgentKey(key)){
            throw new BadRequestException("Invalid key");
        }
        //TODO: Implement Manageing Games
        return "success";
    }
}
