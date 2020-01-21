package io.swagslash.gametrackerserver.endpoint.webapp.controller;

import io.swagslash.gametrackerserver.dto.webapp.GameDTO;
import io.swagslash.gametrackerserver.service.AgentTokenService;
import io.swagslash.gametrackerserver.service.GameService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/webapp")
public class WebappController {

    private GameService gameService;

    private AgentTokenService tokenService;

    public WebappController(GameService gameService, AgentTokenService tokenService) {
        this.gameService = gameService;
        this.tokenService = tokenService;
    }

    @PostMapping("/games/user")
    @PreAuthorize("hasRole('USER')")
    public List<GameDTO> findAllByUser() {
        return gameService.findAllByUser();
    }

    @PostMapping("/games/search")
    @PreAuthorize("hasRole('USER')")
    public List<GameDTO> findBySearch(@RequestBody String term) {
        return gameService.findBySearch(term);
    }

    @PostMapping("/games/add")
    @PreAuthorize("hasRole('USER')")
    public List<GameDTO> markGamesAsOwned(@RequestBody List<String> games) {
        gameService.markGamesAsOwned(games);

        return gameService.findAllByUser();
    }

    @PostMapping("/games/compare")
    @PreAuthorize("hasRole('USER')")
    public List<GameDTO> compare(@RequestBody List<String> usernames) {
        return gameService.compare(usernames);
    }

    @PostMapping("/token/user")
    @PreAuthorize("hasRole('USER')")
    public List<String> getTokensByUser () {
        return tokenService.findAllByUser();
    }

    @PostMapping("/token/add")
    @PreAuthorize("hasRole('USER')")
    public String addAgentToken() {
        return tokenService.getAgentToken();
    }

    @PostMapping("/token/remove")
    @PreAuthorize("hasRole('USER')")
    public String removeAgentToken(@RequestBody String token) {
        return tokenService.getAgentToken();
    }
}
