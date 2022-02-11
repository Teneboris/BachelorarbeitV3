package com.soccer.api.controllers;

import com.soccer.api.models.TrainingsPlan;
import com.soccer.api.playload.response.MessageResponse;
import com.soccer.api.repository.TrainingsplanRepository;

import com.soccer.api.security.services.TrainingsPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    public ResponseEntity<?> CreateTraining(@Valid @RequestBody TrainingsPlan trainingsplan) {
        trainingsPlanService.postTrainingsplan(trainingsplan);
        return ResponseEntity.ok(new MessageResponse("training created successfully!"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingsPlan> gettrainingsPlanById(@PathVariable("id") Long id) {
        TrainingsPlan trainings = trainingsPlanService.getTrainingById(id);
        return ResponseEntity.ok(trainings);  // return 200, with json body
    }

   /* @DeleteMapping(path = "/deletetraining/{Id}")
    public ResponseEntity<Void> deleteTrainingById(@PathVariable long Id) {
        trainainsplanRepository.deleteById(Id);
        return ResponseEntity.ok().build();
    }*/

    @GetMapping("/getAlltraining")
    public ResponseEntity<List<TrainingsPlan>> GetAllTraining() {
        List<TrainingsPlan> trainings = trainainsplanRepository.findAll();
        return ResponseEntity.ok(trainings);  // return 200, with json body
    }

    @DeleteMapping("/deletetraining/{Id}")
    public void DeleteTrainingsplan(@PathVariable long Id) {
        trainingsPlanService.DeleteTrainings(Id);
    }
}
