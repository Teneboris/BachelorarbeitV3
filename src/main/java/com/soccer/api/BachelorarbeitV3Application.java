package com.soccer.api;

import com.soccer.api.models.Role;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.soccer.api.models.ERole.*;

@SpringBootApplication
public class BachelorarbeitV3Application {

    public static void main(String[] args) {
 /*       Role role = new Role();
        role.setName(ROLE_PLAYER);
        role.setName(ROLE_COACH);
        role.setName(ROLE_ADMIN);*/
        SpringApplication.run(BachelorarbeitV3Application.class, args);
    }

}
