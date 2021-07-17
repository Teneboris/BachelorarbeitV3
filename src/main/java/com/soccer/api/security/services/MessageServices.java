package com.soccer.api.security.services;

import com.soccer.api.models.Message;
import com.soccer.api.models.TrainingsPlan;
import com.soccer.api.models.User;
import com.soccer.api.repository.MessageRepository;
import com.soccer.api.repository.TrainingsplanRepository;
import com.soccer.api.repository.UserRepository;
import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServices {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TrainingsplanRepository trainingsplanRepository;

    public Message PostMessage(Message message) {

        Optional<User> user = userRepository.findById(message.getUserTo().getId());
        Optional<TrainingsPlan> training = trainingsplanRepository.findById(message.getTrainings().getId());

        if (user.isPresent() && training.isPresent()) {
            message.setTrainings(training.get());
            message.setUserTo(user.get());


            message = messageRepository.save(message);
            return message;
        } else {
            return null;
        }

    }

    public List<Message> GetAllFeedback() {
        List<Message> feedback = messageRepository.findAll();
        return feedback;
    }

    public List<Message> findFeedbackByUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findById(userDetails.getId());
        if (user.isPresent()) {
            List<Message> userFeedback = messageRepository.findFeedbackbyUser(user.get());
            return userFeedback;
        }
        return null;
    }

}
