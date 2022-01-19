package com.soccer.api.controllers;


import com.soccer.api.models.Games;
import com.soccer.api.models.TrainingsPlan;
import com.soccer.api.playload.response.MessageResponse;
import com.soccer.api.security.services.CreateGamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/games")
public class CreateGamesController {

    @Autowired
    CreateGamesService createGamesService;

    @PostMapping("/postgame")
    public ResponseEntity<?> PostMessage(@Valid @RequestBody Games createGames) {
        createGamesService.postGames(createGames);
        return ResponseEntity.ok(new MessageResponse("Send message successfully!"));
    }

    @GetMapping("/getcreatedgames")
    public ResponseEntity<List<Games>> GetFeedback() {
        List<Games> feedback = createGamesService.GetAllCreatedGames();
        return ResponseEntity.ok(feedback);  // return 200, with json body
    }

    @GetMapping("/{id}")
    public ResponseEntity<Games> gettrainingsPlanById(@PathVariable("id") Long id) {
        Games game = createGamesService.getGameById(id);
        return ResponseEntity.ok(game);  // return 200, with json body
    }
}
