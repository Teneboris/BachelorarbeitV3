package com.soccer.api.repository;

import com.soccer.api.models.Message;
import com.soccer.api.models.ThemeForMessage;
import com.soccer.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("Select a from Message a where a.userTo =?1 or a.userFrom = ?1 group by a.themeForMessage")
    List<Message> findFeedbackbyUser(User userTOId);
}
