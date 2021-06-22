package com.soccer.api.repository;

import com.soccer.api.models.CreateTraining;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface TrainingsplanRepository extends JpaRepository<CreateTraining, Long> {

    Optional<CreateTraining> findByTitle(String title);

    Optional<CreateTraining> findByDate(Date date);

    Boolean existsByDate(Date date);

    Boolean existsByTitle(String title);


}
