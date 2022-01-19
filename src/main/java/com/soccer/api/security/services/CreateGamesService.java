package com.soccer.api.security.services;

import com.soccer.api.models.Games;
import com.soccer.api.models.TrainingsPlan;
import com.soccer.api.repository.CreateGamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreateGamesService {

    @Autowired
    CreateGamesRepository createGamesRepository;

    public Games postGames(Games createdGames) {
        createdGames = createGamesRepository.save(createdGames);
        return createdGames;
    }

    public List<Games> GetAllCreatedGames() {
        List<Games> game = createGamesRepository.findAll();
        return game;
    }

    public Games getGameById(Long id) {
        Optional<Games> game = createGamesRepository.findById(id);

        return game.orElse(null);
    }
}
