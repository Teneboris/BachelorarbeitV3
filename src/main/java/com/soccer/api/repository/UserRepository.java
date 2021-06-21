package com.soccer.api.repository;

import com.soccer.api.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByFirstName(String firstName);

    Boolean existsByLastName(String lastName);

    Boolean existsByEmail(String email);
}