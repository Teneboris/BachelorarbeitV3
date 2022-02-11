package com.soccer.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.TimeZone;

@Entity
@Table(name = "trainingsplan")
public class TrainingsPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate trainingsdate;

    //private LocalTime trainingstime;

    @NotBlank
    @Size(max = 200)
    private String title;

    @Size(max = 1000)
    private String description;

    public TrainingsPlan() {

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }


    public LocalDate getTrainingsdate() {
        return trainingsdate;
    }

    public void setTrainingsdate(LocalDate trainingsdate) {
        this.trainingsdate = trainingsdate;
    }

  /*  public LocalTime getTrainingstime() {
        return trainingstime;
    }

    public void setTrainingstime(LocalTime trainingstime) {
        this.trainingstime = trainingstime;
    }*/
}
