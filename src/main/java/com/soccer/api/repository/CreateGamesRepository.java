package com.soccer.api.repository;

import com.soccer.api.models.Games;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreateGamesRepository extends JpaRepository<Games, Long> {
}
