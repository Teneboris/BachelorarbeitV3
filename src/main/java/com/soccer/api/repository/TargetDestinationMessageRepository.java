package com.soccer.api.repository;

import com.soccer.api.models.EMessageReceiver;
import com.soccer.api.models.TargetDestinationMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TargetDestinationMessageRepository extends JpaRepository<TargetDestinationMessage, Long> {

    Optional<TargetDestinationMessage> findByName(EMessageReceiver name);
}
