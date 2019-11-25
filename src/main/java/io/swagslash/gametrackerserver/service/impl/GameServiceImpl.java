package io.swagslash.gametrackerserver.service.impl;

import io.swagslash.gametrackerserver.config.AppProperties;
import io.swagslash.gametrackerserver.dto.GameDTO;
import io.swagslash.gametrackerserver.igdbconsumer.IGDBQuery;
import io.swagslash.gametrackerserver.igdbconsumer.IGDBWrapper;
import io.swagslash.gametrackerserver.igdbconsumer.model.IGDBGame;
import io.swagslash.gametrackerserver.model.Game;
import io.swagslash.gametrackerserver.repository.GameRepository;
import io.swagslash.gametrackerserver.service.GameService;
import io.swagslash.gametrackerserver.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    AppProperties appProperties;

    GameRepository gameRepository;

    UserService userService;

    public GameServiceImpl(AppProperties appProperties, GameRepository gameRepository, UserService userService) {
        this.appProperties = appProperties;
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

    @Override
    public List<GameDTO> findBySearch(String term) {
        IGDBWrapper igdbWrapper = new IGDBWrapper(appProperties.getIgdb().getKey());
        List<IGDBGame> games = igdbWrapper.searchGames(term);
        List<GameDTO> dtos = new ArrayList<>();

        for (IGDBGame game : games) {
            dtos.add(igdbGameToDTO(game));
        }

        return dtos;
    }

    private GameDTO entityToDTO(Game entity) {
        return new GameDTO(entity.getName(), entity.getImageId());
    }

    private GameDTO igdbGameToDTO(IGDBGame game) {
        return new GameDTO(game.getName(), String.valueOf(game.getId()));
    }
}
