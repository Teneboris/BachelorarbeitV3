package com.soccer.api.playload.request;

import javax.validation.constraints.*;
import java.util.Set;

public class SignupRequest {

    @NotBlank
    @Size(min = 6, max = 10)
    private String username;

    @NotBlank
    @Size(max = 50)
    private String firstName;

    @NotBlank
    @Size(max = 50)
    private String lastName;

    @NotBlank
    @Size(min = 6, max = 50)
    @Email
    private String email;

    private Set<String> role;

    private Set<String> playerposition;

    @NotBlank
    @Size(max = 40)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<String> getPlayerposition() {
        return playerposition;
    }

    public void setPlayerposition(Set<String> playerposition) {
        this.playerposition = playerposition;
    }
}
