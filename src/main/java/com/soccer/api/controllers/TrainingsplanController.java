package com.soccer.api.controllers;

import com.soccer.api.models.CreateTraining;
import com.soccer.api.playload.request.TrainingsplanRequest;
import com.soccer.api.playload.response.MessageResponse;
import com.soccer.api.repository.TrainingsplanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class TrainingsplanController {

    @Autowired
    TrainingsplanRepository trainainsplanRepository;

    @PostMapping("/createtraining")
    public ResponseEntity<?> CreateTraining(@Valid @RequestBody TrainingsplanRequest trainingsplanRequest) {
        if (trainainsplanRepository.existsByDate(trainingsplanRequest.getDate())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Date is already taken!"));
        }

        if (trainainsplanRepository.existsByTitle(trainingsplanRequest.getTitle())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: title is already in use!"));
        }

        // Create new training's plan
        CreateTraining training = new CreateTraining(
                trainingsplanRequest.getDate(),
                trainingsplanRequest.getTitle(),
                trainingsplanRequest.getDescription());

        trainainsplanRepository.save(training);

        return ResponseEntity.ok(new MessageResponse("training created successfully!"));
    }

    @GetMapping("/getAlltraining")
    public ResponseEntity<List<CreateTraining>> GetAllTraining() {

        List<CreateTraining> trainings = trainainsplanRepository.findAll();
        return ResponseEntity.ok(trainings);  // return 200, with json body
    }

    @DeleteMapping(path = "/deletetraining/{Id}")
    public ResponseEntity<Void> deleteTrainingById(@PathVariable long Id) {
        trainainsplanRepository.deleteById(Id);
        return ResponseEntity.ok().build();
    }
}
