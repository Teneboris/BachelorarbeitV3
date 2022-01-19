package com.soccer.api.models;

import com.fasterxml.jackson.annotation.*;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    private String subject;

    @Size(max = 1000)
    @NotBlank
    private String message;

    //private String displayby;

    /*  @JsonFormat(pattern = "dd-mm-yyyy HH:mm:ss")
      @JsonSerialize(using = LocalDateTimeSerializer.class)*/
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime postDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "receivermessage_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User userTo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "sendermessage_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User userFrom;

    @ManyToOne()
    @JoinColumn(name = "themeformessage_id")
    @JsonBackReference()
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ThemeForMessage themeForMessage;

    public Message() {

    }

    public Message(String message, String subject, User userTo) {

    }

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserTo() {
        return userTo;
    }

    public void setUserTo(User userTo) {
        this.userTo = userTo;
    }

    /*public String getDisplayby() {
        return displayby;
    }

    public void setDisplayby(String displayby) {
        this.displayby = displayby;
    }
}
