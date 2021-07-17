package com.soccer.api.controllers;

import com.soccer.api.models.TrainingsPlan;
import com.soccer.api.playload.request.TrainingsplanRequest;
import com.soccer.api.playload.response.MessageResponse;
import com.soccer.api.repository.TrainingsplanRepository;

import com.soccer.api.security.services.TrainingsPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/training")
public class TrainingsplanController {

    @Autowired
    TrainingsplanRepository trainainsplanRepository;

    @Autowired
    TrainingsPlanService trainingsPlanService;

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
        TrainingsPlan training = new TrainingsPlan(
                trainingsplanRequest.getDate(),
                trainingsplanRequest.getTitle(),
                trainingsplanRequest.getDescription());

        trainainsplanRepository.save(training);

        return ResponseEntity.ok(new MessageResponse("training created successfully!"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingsPlan> gettrainingsPlanById(@PathVariable("id") Long id) {

        TrainingsPlan trainings = trainingsPlanService.getTrainingById(id);
        return ResponseEntity.ok(trainings);  // return 200, with json body
    }

    @DeleteMapping(path = "/deletetraining/{Id}")
    public ResponseEntity<Void> deleteTrainingById(@PathVariable long Id) {
        trainainsplanRepository.deleteById(Id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAlltraining")
    public ResponseEntity<List<TrainingsPlan>> GetAllTraining() {

        List<TrainingsPlan> trainings = trainainsplanRepository.findAll();
        return ResponseEntity.ok(trainings);  // return 200, with json body
    }
}
