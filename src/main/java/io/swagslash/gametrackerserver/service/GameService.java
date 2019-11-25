package io.swagslash.gametrackerserver.service;

import io.swagslash.gametrackerserver.dto.GameDTO;

import java.util.List;

public interface GameService {

    List<GameDTO> findAll();

    List<GameDTO> findAllByUser();
}
