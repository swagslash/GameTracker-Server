package io.swagslash.gametrackerserver.service.impl;

import io.swagslash.gametrackerserver.dto.GameDTO;
import io.swagslash.gametrackerserver.model.Game;
import io.swagslash.gametrackerserver.repository.GameRepository;
import io.swagslash.gametrackerserver.service.GameService;
import io.swagslash.gametrackerserver.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    GameRepository gameRepository;

    UserService userService;

    public GameServiceImpl(GameRepository gameRepository, UserService userService) {
        this.gameRepository = gameRepository;
        this.userService = userService;
    }

    @Override
    public List<GameDTO> findAll() {
        List<GameDTO> dtos = new ArrayList<>();
        for (Game game : gameRepository.findAll()) {
                dtos.add(entityToDTO(game));
        }

        return dtos;
    }

    @Override
    public List<GameDTO> findAllByUser() {
        List<Game> games =  userService.getCurrentUser().getGames();
        List<GameDTO> dtos = new ArrayList<>();

        for (Game game : games) {
            dtos.add(entityToDTO(game));
        }

        return dtos;
    }

    private GameDTO entityToDTO(Game entity) {
        return new GameDTO(entity.getName(), entity.getImageId());
    }
}
