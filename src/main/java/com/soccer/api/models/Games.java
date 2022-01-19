package com.soccer.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "games")
public class Games {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String title;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date;

    @JsonFormat(pattern = "dd-mm-yyyy HH:mm:ss")
    private Date gamestime;

    @NotBlank
    @Size(max = 100)
    private String address;

    @Size(max = 1000)
    private String description;

    public Games() {
    }

    public Games(Date date, String address, String description) {
        this.date = date;
        this.address = address;
        this.description = description;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Date getGamestime() {
        return gamestime;
    }

    public void setGamestime(Date gamestime) {
        this.gamestime = gamestime;
    }
}
