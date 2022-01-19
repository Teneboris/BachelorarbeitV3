package com.soccer.api.controllers;

import com.soccer.api.models.User;
import com.soccer.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    UserRepository userRepository;

    @GetMapping("/all/users")
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);  // return 200, with json body
    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<Optional<User>> getUsersById(@PathVariable long id) {
        Optional<User> user = userRepository.findById(id);
        return ResponseEntity.ok(user);  // return 200, with json body
    }
}

