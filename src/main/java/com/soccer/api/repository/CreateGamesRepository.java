package com.soccer.api.repository;

import com.soccer.api.models.CreateGames;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreateGamesRepository extends JpaRepository<CreateGames, Long> {
}
