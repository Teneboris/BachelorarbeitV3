package com.soccer.api.repository;

import com.soccer.api.models.Message;
import com.soccer.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("Select a from Message a where a.userTo =?1")
    List<Message> findFeedbackbyUser(User userTOId);
}
