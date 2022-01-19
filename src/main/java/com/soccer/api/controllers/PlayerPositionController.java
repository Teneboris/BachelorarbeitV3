package com.soccer.api.controllers;


import com.soccer.api.models.EPlayersPosition;
import com.soccer.api.models.TrainingsPlan;
import com.soccer.api.models.User;
import com.soccer.api.security.services.PlayerpositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/player")
public class PlayerPositionController {

    @Autowired
    PlayerpositionService playerpositionService;

    @GetMapping("/getAllGoalkeeper")
    public ResponseEntity<List<User>> GetAllGOALKEEPERUser(EPlayersPosition name) {
        List<User> player = (List<User>) playerpositionService.getAllGOALKEEPER(name);
        return ResponseEntity.ok(player);  // return 200, with json body
    }
}
