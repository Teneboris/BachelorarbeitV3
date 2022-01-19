package com.soccer.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.TimeZone;

@Entity
@Table(name = "trainingsplan")
public class TrainingsPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private Date date;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private Date trainingstime;

    @NotBlank
    @Size(max = 200)
    private String title;

    @Size(max = 1000)
    private String description;

    public TrainingsPlan() {

    }

    public TrainingsPlan(Date date, Date trainingstime, String title, String description) {
        this.date = date;
        this.trainingstime = trainingstime;
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTrainingstime() {
        return trainingstime;
    }

    public void setTrainingstime(Date trainingstime) {
        this.trainingstime = trainingstime;
    }

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}
