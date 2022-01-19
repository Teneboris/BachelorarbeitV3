package com.soccer.api.models;

import javax.persistence.*;

@Entity
@Table(name = "playersposition")
public class PlayerPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EPlayersPosition name;

    public PlayerPosition() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EPlayersPosition getName() {
        return name;
    }

    public void setName(EPlayersPosition name) {
        this.name = name;
    }
}
