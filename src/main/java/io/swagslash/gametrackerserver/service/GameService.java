package io.swagslash.gametrackerserver.service;

import io.swagslash.gametrackerserver.dto.webapp.GameDTO;

import java.util.List;

public interface GameService {

    List<GameDTO> findAll();

    List<GameDTO> findAllByUser();

    List<GameDTO> findBySearch(String term);

    void markGamesAsOwned(List<String> games);
}
