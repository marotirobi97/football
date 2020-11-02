package com.example.football;
import com.example.football.enums.Nationality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "nationality")
    @Enumerated(EnumType.STRING)
    private Nationality nationality;

    @Column(name = "number_of_players")
    private Integer numberOfPlayers;

    @Column(name = "number_of_victories")
    private Integer numberOfVictories;

}
