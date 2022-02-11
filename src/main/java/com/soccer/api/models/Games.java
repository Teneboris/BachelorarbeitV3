package com.soccer.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "games")
public class Games {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String title;

    private LocalDate gamesDate;

    private LocalTime gamesTime;

    @NotBlank
    @Size(max = 100)
    private String address;

    @Size(max = 1000)
    private String description;

    public Games() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String location) {
        this.address = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getGamesDate() {
        return gamesDate;
    }

    public void setGamesDate(LocalDate gamesDate) {
        this.gamesDate = gamesDate;
    }

    public LocalTime getGamesTime() {
        return gamesTime;
    }

    public void setGamesTime(LocalTime gamesTime) {
        this.gamesTime = gamesTime;
    }

}
