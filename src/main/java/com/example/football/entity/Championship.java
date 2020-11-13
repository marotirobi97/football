package com.example.football.entity;

import com.example.football.enums.Round;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Championship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "round")
    @Enumerated(EnumType.STRING)
    private Round round = Round.QUARTERFINAL;
}
