package com.soccer.api.repository;

import com.soccer.api.models.EPlayersPosition;
import com.soccer.api.models.PlayerPosition;
import com.soccer.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlayerPositionRepository extends JpaRepository<PlayerPosition, Long> {

    Optional<PlayerPosition> findByName(EPlayersPosition name);

    @Query("Select a from User a join a.player m where m.name = ?1 group by a.id")
    List<User> getAllUserWherePlayerpositionEqaulsGOalKEEPER(EPlayersPosition name);

    @Modifying
    @Query("update User u set u.player = ?1 where u.id = ?2")
    void setplayerpositionById(User user, Long userId);
}
