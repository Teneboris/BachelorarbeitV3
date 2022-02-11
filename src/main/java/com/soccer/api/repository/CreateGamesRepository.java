package com.soccer.api.repository;

import com.soccer.api.models.Games;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CreateGamesRepository extends JpaRepository<Games, Long> {

    @Transactional
    @Modifying
    @Query("delete from Games b where b.id = ?1")
    void deletegame(Long ids);
}
