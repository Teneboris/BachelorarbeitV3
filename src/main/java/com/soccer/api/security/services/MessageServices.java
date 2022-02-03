package com.soccer.api.security.services;

import com.soccer.api.models.*;
import com.soccer.api.repository.MessageRepository;
import com.soccer.api.repository.ThemeForMessageRepository;
import com.soccer.api.repository.TrainingsplanRepository;
import com.soccer.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageServices {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TrainingsplanRepository trainingsplanRepository;

    @Autowired
    ThemeForMessageRepository themeForMessageRepository;

    public Message PostMessage(Message message) {

        UserDetailsImpl actuelUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userFrom = userRepository.findById(actuelUser.getId());

        Optional<User> userTo = userRepository.findById(message.getUserTo().getId());

        ThemeForMessage theme = null;
        if (message.getThemeForMessage().getId() != null) {
            Optional<ThemeForMessage> themeOptional = themeForMessageRepository.findById(message.getThemeForMessage().getId());
            if (themeOptional.isPresent()) {
                theme = themeOptional.get();
            }
        }

        if (userTo.isPresent() && userFrom.isPresent()) {
            message.setUserTo(userTo.get());
            message.setUserFrom(userFrom.get());
            if (theme != null) {
                message.setThemeForMessage(theme);
                message = messageRepository.save(message);

            } else {
                Optional<TrainingsPlan> training = trainingsplanRepository.findById(message.getThemeForMessage().getTrainings().getId());
                if (training.isPresent()) {
                    message.getThemeForMessage().setTrainings(training.get());
                }
                ThemeForMessage themes = themeForMessageRepository.save(message.getThemeForMessage());
                message.setThemeForMessage(themes);
                message = messageRepository.save(message);

            }
        }
        return null;
    }

    public List<Message> GetAllFeedback() {
        List<Message> feedback = messageRepository.findAll();
        return feedback;
    }

    public List<ThemeForMessage> findFeedbackByUser(ERole role) {
        List<Message> test = messageRepository.findAll();
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findById(userDetails.getId());
        if (user.isPresent()) {
            List<ThemeForMessage> userFeedback = messageRepository.findFeedbackBySubject(user.get());
            if (role == ERole.ROLE_COACH || role == ERole.ROLE_ADMIN) {
                userFeedback = this.removeUserFrom(userFeedback, user.get().getId());
            }
            return userFeedback;
        }
        return null;
    }

    public List<ThemeForMessage> removeUserFrom(List<ThemeForMessage> themeForMessages, Long userId) {
        themeForMessages.forEach(theme -> {
            theme.getMessage().forEach(message -> {
                if (!message.getUserTo().getId().equals(userId)) {
                    message.setUserTo(new User(message.getUserTo().getId()));
                } else if (!message.getUserFrom().getId().equals(userId)) {
                    message.setUserFrom(new User(message.getUserFrom().getId()));
                }
            });
        });
        return themeForMessages;
    }

    public List<ThemeForMessage> findFeedbackBySubject(Long id) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findById(userDetails.getId());
        Optional<ThemeForMessage> theme = themeForMessageRepository.findById(id);
        if (user.isPresent() && theme.isPresent()) {
            List<ThemeForMessage> userFeedback = messageRepository.findFeedbackBySubject(user.get());
            return getList(userFeedback);
        }
        return null;
    }

    public List<ThemeForMessage> getList(List<ThemeForMessage> list) {
        list.forEach(l -> {
            l.getMessage().forEach(message -> {
                message.setThemeForMessage(null);
            });
        });

        return list;
    }

    public void deleteSubject(Long id) {
        messageRepository.deleteSubject(id);
    }

    public List<User> getAllplayers() {
        List<User> players = userRepository.findAll().stream()
                .filter(p -> !p.getPlayer().isEmpty())
                .collect(Collectors.toList());
        return players;
    }
}
