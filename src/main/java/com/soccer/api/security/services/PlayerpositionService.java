package com.soccer.api.security.services;

import com.soccer.api.models.EPlayersPosition;
import com.soccer.api.models.User;
import com.soccer.api.repository.PlayerPositionRepository;
import com.soccer.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerpositionService {

    @Autowired
    PlayerPositionRepository playerPositionRepository;

    @Autowired
    UserRepository userRepository;

    public User getAllGOALKEEPER(EPlayersPosition name) {
        UserDetailsImpl actuelUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> player = userRepository.findById(actuelUser.getId());
        if (player.isPresent()) {
            playerPositionRepository.getAllUserWherePlayerpositionEqaulsGOalKEEPER(name);
        }
        return null;
    }

    public User UpdatePlayerPosition(Long id) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findById(userDetails.getId());
        if (user.isPresent()) {
            playerPositionRepository.setplayerpositionById(user.get(), id);
        }
        return null;
    }
}
