package com.soccer.api.controllers;

import com.soccer.api.models.*;
import com.soccer.api.playload.request.LoginRequest;
import com.soccer.api.playload.request.SignupRequest;
import com.soccer.api.playload.response.JwtResponse;
import com.soccer.api.playload.response.MessageResponse;
import com.soccer.api.repository.PlayerPositionRepository;
import com.soccer.api.repository.RoleRepository;
import com.soccer.api.repository.UserRepository;
import com.soccer.api.security.jwt.JwtUtils;
import com.soccer.api.security.services.MessageServices;
import com.soccer.api.security.services.UserDetailsImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageServices messageService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PlayerPositionRepository playerPositionRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getFirstName(),
                userDetails.getLastName(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    @Transactional
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        /**
         * Create new user's account
         */
        User user = new User(
                signUpRequest.getUsername(),
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getEmail(),
                signUpRequest.getBirthdate(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRoles();
        //strRoles = new HashSet<>(Arrays.asList("admin"));
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_PLAYER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "coach":
                        Role modRole = roleRepository.findByName(ERole.ROLE_COACH)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_PLAYER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        Set<String> strPlayerPosition = signUpRequest.getPlayerposition();
        Set<PlayerPosition> players = new HashSet<>();

        if (strPlayerPosition == null) {
            PlayerPosition userPlayer = playerPositionRepository.findByName(EPlayersPosition.DEFENCE)
                    .orElseThrow(() -> new RuntimeException("Error: position defence is not found."));
            players.add(userPlayer);
        } else {
            strPlayerPosition.forEach(player -> {
                switch (player) {
                    case "defence":
                        PlayerPosition defense = playerPositionRepository.findByName(EPlayersPosition.DEFENCE)
                                .orElseThrow(() -> new RuntimeException("Error:  position defence is not found."));
                        players.add(defense);

                        break;
                    case "goalkeeper":
                        PlayerPosition adminRole = playerPositionRepository.findByName(EPlayersPosition.GOALKEEPER)
                                .orElseThrow(() -> new RuntimeException("Error:position goalkeeper is not found."));
                        players.add(adminRole);

                        break;
                    case "midfield":
                        PlayerPosition modRole = playerPositionRepository.findByName(EPlayersPosition.MIDFIELD)
                                .orElseThrow(() -> new RuntimeException("Error: midfield is not found."));
                        players.add(modRole);

                        break;
                    default:
                        PlayerPosition userRole = playerPositionRepository.findByName(EPlayersPosition.STORM)
                                .orElseThrow(() -> new RuntimeException("Error:position storm is not found."));
                        players.add(userRole);
                }
            });
        }
        user.setPlayer(players);
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


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

    @GetMapping("/getplayers")
    public ResponseEntity<List<User>> getPlayers() {
        List<User> players = messageService.getAllplayers();
        return ResponseEntity.ok(players);
    }
}
