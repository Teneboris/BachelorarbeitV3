package com.soccer.api.playload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalTime;
import java.util.Date;

public class TrainingsplanRequest {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date Date;


    private Date trainingstime;

    @NotBlank
    @Size(max = 200)
    private String title;

    @Size(max = 1000)
    private String description;

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
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
}
