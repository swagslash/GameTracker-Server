package io.swagslash.gametrackerserver.service.impl;

import io.swagslash.gametrackerserver.dto.GameDTO;
import io.swagslash.gametrackerserver.model.Game;
import io.swagslash.gametrackerserver.service.GameService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Override
    public List<GameDTO> findAll() {
        List<GameDTO> games = new ArrayList<>();
        games.add(new GameDTO("Overwatch", "owbild"));
        games.add(new GameDTO("WoW", "wowbild"));
        games.add(new GameDTO("LoL", "lolbild"));
        games.add(new GameDTO("Borderlands", "blbild"));

        return games;
    }
}
