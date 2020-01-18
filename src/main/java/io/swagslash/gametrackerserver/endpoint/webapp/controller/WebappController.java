package io.swagslash.gametrackerserver.endpoint.webapp.controller;

import io.swagslash.gametrackerserver.dto.webapp.GameDTO;
import io.swagslash.gametrackerserver.service.GameService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/webapp")
public class WebappController {

    private GameService gameService;

    public WebappController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games/user")
    @PreAuthorize("hasRole('USER')")
    public List<GameDTO> findAllByUser() {
        return gameService.findAllByUser();
    }

    @PostMapping("/games/search")
    @PreAuthorize("hasRole('USER')")
    public List<GameDTO> findBySearch(@RequestParam String term) {
        return gameService.findBySearch(term);
    }

    @PostMapping("/games/mark")
    @PreAuthorize("hasRole('USER')")
    public void markGamesAsOwned(@RequestBody List<GameDTO> games) {
        gameService.markGamesAsOwned(games);
    }
}
