package io.swagslash.gametrackerserver.igdbconsumer;

import io.swagslash.gametrackerserver.igdbconsumer.model.Cover;
import io.swagslash.gametrackerserver.igdbconsumer.model.Game;
import io.swagslash.gametrackerserver.igdbconsumer.model.GameMode;
import io.swagslash.gametrackerserver.igdbconsumer.model.Genre;

import java.util.List;

public interface IGDBApi {

    List<Game> getGames(IGDBQuery query);

    List<GameMode> getGameModes(Game game);

    List<Genre> getGenres(Game game);

    Cover getCover(Game game);

    List<Game> searchGames(String searchTerm);
}
