package com.soccer.api.security.services;

import com.soccer.api.models.CreateGames;
import com.soccer.api.repository.CreateGamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateGamesService {

    @Autowired
    CreateGamesRepository createGamesRepository;

    public CreateGames postGames(CreateGames createdGames) {
        createdGames = createGamesRepository.save(createdGames);
        return createdGames;
    }

    public List<CreateGames> GetAllCreatedGames() {
        List<CreateGames> game = createGamesRepository.findAll();
        return game;
    }
}
