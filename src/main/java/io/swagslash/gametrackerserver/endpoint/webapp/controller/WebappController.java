package io.swagslash.gametrackerserver.endpoint.webapp.controller;

import io.swagslash.gametrackerserver.dto.GameDTO;
import io.swagslash.gametrackerserver.service.GameService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/webapp")
public class WebappController {

    private GameService gameService;

    public WebappController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games")
    @PreAuthorize("hasRole('USER')")
    public List<GameDTO> findAll() {
       return gameService.findAll();
    }

    @GetMapping("/games/user")
    @PreAuthorize("hasRole('USER')")
    public List<GameDTO> findAllByUser() {
        return gameService.findAllByUser();
    }

}
