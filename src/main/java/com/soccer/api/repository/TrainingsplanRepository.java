package com.soccer.api.repository;

import com.soccer.api.models.TrainingsPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

public interface TrainingsplanRepository extends JpaRepository<TrainingsPlan, Long> {

    Optional<TrainingsPlan> findByTitle(String title);

    Boolean existsByTitle(String title);

    @Transactional
    @Modifying
    @Query("delete from TrainingsPlan b where b.id = ?1")
    void deletetraining(Long ids);

}
