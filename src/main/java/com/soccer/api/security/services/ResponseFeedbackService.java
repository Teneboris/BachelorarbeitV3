package com.soccer.api.security.services;

import com.soccer.api.models.ResponseFeedback;
import com.soccer.api.models.User;
import com.soccer.api.repository.ResponseFeedbackRepository;
import com.soccer.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResponseFeedbackService {

    @Autowired
    ResponseFeedbackRepository responseFeedbackRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseFeedback PostFeedback(ResponseFeedback responseFeedback) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findById(userDetails.getId());
        if (user.isPresent()) {
            responseFeedback = responseFeedbackRepository.save(responseFeedback);
            return responseFeedback;
        }
        return null;
    }
}



