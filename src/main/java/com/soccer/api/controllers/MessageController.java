package com.soccer.api.controllers;

import com.soccer.api.models.*;
import com.soccer.api.playload.request.MessageRequest;
import com.soccer.api.playload.response.MessageResponse;
import com.soccer.api.repository.MessageRepository;
import com.soccer.api.security.services.MessageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    MessageServices messageService;

    @Autowired
    MessageRepository messageRepository;


    @PostMapping("/postmessage")
    public ResponseEntity<?> PostMessage(@Valid @RequestBody Message message) {
        messageService.PostMessage(message);
        return ResponseEntity.ok(new MessageResponse("Send message successfully!"));
    }

    @GetMapping("/getfeedback")
    public ResponseEntity<List<Message>> GetFeedback() {
        List<Message> feedback = messageService.GetAllFeedback();
        return ResponseEntity.ok(feedback);  // return 200, with json body
    }


    @GetMapping("/getfeedbackbyuser/{role}")
    public ResponseEntity<List<ThemeForMessage>> GetFeedbackByUser(@PathVariable("role") ERole role) {
        List<ThemeForMessage> feedback = messageService.findFeedbackByUser(role);
        return ResponseEntity.ok(feedback);  // return 200, with json body
    }

    @GetMapping("/getfeedbackbysubject/{id}")
    public ResponseEntity<List<ThemeForMessage>> GetFeedbackBySuject(@PathVariable("id") Long id) {
        List<ThemeForMessage> feedback = messageService.findFeedbackBySubject(id);
        return ResponseEntity.ok(feedback);  // return 200, with json body
    }

    @DeleteMapping("/deletesubject/{id}")
    public void DeleteSubject(@PathVariable Long id) {
        messageService.deleteSubject(id);
    }
}
