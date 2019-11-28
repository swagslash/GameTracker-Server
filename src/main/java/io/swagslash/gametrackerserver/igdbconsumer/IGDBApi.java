package io.swagslash.gametrackerserver.igdbconsumer;

import io.swagslash.gametrackerserver.igdbconsumer.model.IGDBCover;
import io.swagslash.gametrackerserver.igdbconsumer.model.IGDBGame;
import io.swagslash.gametrackerserver.igdbconsumer.model.IGDBGameMode;
import io.swagslash.gametrackerserver.igdbconsumer.model.IGDBGenre;

import java.util.List;

public interface IGDBApi {

    List<IGDBGame> getGames(IGDBQuery query);

    List<IGDBGameMode> getGameModes(IGDBGame game);

    List<IGDBGenre> getGenres(IGDBGame game);

    IGDBCover getCover(IGDBGame game);

    List<IGDBGame> searchGames(String searchTerm);
}
