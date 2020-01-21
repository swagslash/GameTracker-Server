package io.swagslash.gametrackerserver.service.impl;

import io.swagslash.gametrackerserver.config.AppProperties;
import io.swagslash.gametrackerserver.dto.agent.AgentGame;
import io.swagslash.gametrackerserver.dto.webapp.GameDTO;
import io.swagslash.gametrackerserver.dto.webapp.TagDTO;
import io.swagslash.gametrackerserver.enums.TagTypeEnum;
import io.swagslash.gametrackerserver.igdbconsumer.IGDBApi;
import io.swagslash.gametrackerserver.igdbconsumer.IGDBWrapper;
import io.swagslash.gametrackerserver.igdbconsumer.model.IGDBGame;
import io.swagslash.gametrackerserver.igdbconsumer.model.IGDBGameMode;
import io.swagslash.gametrackerserver.igdbconsumer.model.IGDBGenre;
import io.swagslash.gametrackerserver.model.Game;
import io.swagslash.gametrackerserver.model.Tag;
import io.swagslash.gametrackerserver.model.User;
import io.swagslash.gametrackerserver.repository.GameRepository;
import io.swagslash.gametrackerserver.repository.TagRepository;
import io.swagslash.gametrackerserver.service.AgentTokenService;
import io.swagslash.gametrackerserver.service.GameService;
import io.swagslash.gametrackerserver.service.UserService;
import io.swagslash.gametrackerserver.util.EntityDTOConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    AppProperties appProperties;

    GameRepository gameRepository;

    UserService userService;

    TagRepository tagRepository;

    AgentTokenService tokenService;

    IGDBApi igdb;

    public GameServiceImpl(AppProperties appProperties, GameRepository gameRepository, UserService userService, TagRepository tagRepository, AgentTokenService tokenService) {
        this.appProperties = appProperties;
        this.gameRepository = gameRepository;
        this.userService = userService;
        this.tagRepository = tagRepository;
        this.tokenService = tokenService;
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
        igdb = new IGDBWrapper(appProperties.getIgdb().getKey());
        List<IGDBGame> games;
        List<GameDTO> dtos = new ArrayList<>();

        try {
            games = new ArrayList<>(igdb.searchGames(term));
        } catch (Exception e) {
            games = new ArrayList<>();
        }

        List<IGDBGame> toRemove = new ArrayList<>();
        for (IGDBGame game : games) {
            Game g = gameRepository.findByIgdbId(game.getId());
            if(g != null) {
                dtos.add(entityToDTO(g));
                toRemove.add(game);
            }
        }

        games.removeAll(toRemove);

        for (IGDBGame game : games) {
            GameDTO g = igdbGameToDTO(game, igdb);
            dtos.add(g);
            gameRepository.save(dtoToEntity(g, game));
        }

        return dtos;
    }

    @Override
    public void markGamesAsOwned(List<String> games) {
        User user = userService.getCurrentUser();
        for (String game : games) {
            if(gameRepository.findByIgdbId(Integer.valueOf(game)) != null){
                user.getGames().add(gameRepository.findByIgdbId(Integer.valueOf(game)));
            }
        }
    }

    @Override
    public void markGamesAsOwnedAgent(List<AgentGame> games, String key) {
        //TODO: Implement Marking for Agent
    }

    @Override
    public List<GameDTO> compare(List<String> username) {
        return new ArrayList<>();
    }

    private GameDTO entityToDTO(Game entity) {
        return new GameDTO(entity.getName(),
                entity.getImageId(),
                EntityDTOConverter.tagListToDTO(entity.getGenres()),
                EntityDTOConverter.tagListToDTO(entity.getGameModes())
        );
    }

    private GameDTO igdbGameToDTO(IGDBGame game, IGDBApi igdb) {
        GameDTO dto = new GameDTO(game.getName());
        try {
            dto.setImageId((igdb.getCover(game)).getImage_id());
            for (IGDBGameMode gameMode : igdb.getGameModes(game)) {
                dto.getGamemodes().add(new TagDTO(gameMode.getName(), gameMode.getSlug()));
            }
            for (IGDBGenre genre : igdb.getGenres(game)) {
                dto.getGenres().add(new TagDTO(genre.getName(), genre.getSlug()));
            }
        } catch (Exception e) {

        }
         
        return dto;
    }

    private Game dtoToEntity (GameDTO dto, IGDBGame igdbGame) {
        Game game = new Game();
        game.setName(dto.getName());
        game.setDbGameId(igdbGame.getId());
        game.setImageId(dto.getImageId());

        for (TagDTO genre : dto.getGenres()) {
            Tag t = new Tag();
            t.setName(genre.getName());
            t.setSlug(genre.getSlug());
            t.setType(TagTypeEnum.GENRE);
            game.getTags().add(t);
        }

        for (TagDTO gamemode : dto.getGamemodes()) {
            Tag t = new Tag();
            t.setName(gamemode.getName());
            t.setSlug(gamemode.getSlug());
            t.setType(TagTypeEnum.GAMEMODE);
            game.getTags().add(t);
        }

        return game;
    }
}
