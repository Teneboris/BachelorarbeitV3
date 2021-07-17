package com.soccer.api.models;

import javax.persistence.*;

@Entity
@Table(name = "targetdestinationmessage")
public class TargetDestinationMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EMessageReceiver name;

    public TargetDestinationMessage() {

    }

    public TargetDestinationMessage(EMessageReceiver name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EMessageReceiver getName() {
        return name;
    }

    public void setName(EMessageReceiver name) {
        this.name = name;
    }
}
