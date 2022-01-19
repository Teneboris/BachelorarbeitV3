package com.soccer.api.controllers;

import com.soccer.api.models.ResponseFeedback;
import com.soccer.api.playload.response.MessageResponse;
import com.soccer.api.security.services.ResponseFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/sendresponse")
public class ResponseFeedbackController {

    @Autowired
    ResponseFeedbackService responseFeedbackService;

    @PostMapping("/postfeedback")
    public ResponseEntity<?> SendResponde(@Valid @RequestBody ResponseFeedback responseFeedback) {
        responseFeedbackService.PostFeedback(responseFeedback);
        return ResponseEntity.ok(new MessageResponse("Send feedback successfully!"));
    }

}
