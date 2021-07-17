package com.soccer.api.security.services;

import com.soccer.api.models.TrainingsPlan;
import com.soccer.api.repository.TrainingsplanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainingsPlanService {

    @Autowired
    TrainingsplanRepository trainainsplanRepository;

    public TrainingsPlan getTrainingById(Long id) {
        Optional<TrainingsPlan> trainingsPlan = trainainsplanRepository.findById(id);

        return trainingsPlan.orElse(null);
    }
}
