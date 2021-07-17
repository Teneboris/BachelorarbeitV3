package com.soccer.api.playload.request;

import com.soccer.api.models.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class MessageRequest {

    @NotBlank
    @Size(max = 1000)
    private String message;

    private User userTo;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUserTo() {
        return userTo;
    }

    public void setUserTo(User userTo) {
        this.userTo = userTo;
    }
}
