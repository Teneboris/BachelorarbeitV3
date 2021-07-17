package com.soccer.api.repository;

import com.soccer.api.models.TrainingsPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface TrainingsplanRepository extends JpaRepository<TrainingsPlan, Long> {

    Optional<TrainingsPlan> findByTitle(String title);

    Optional<TrainingsPlan> findByDate(Date date);

    Boolean existsByDate(Date date);

    Boolean existsByTitle(String title);


}
