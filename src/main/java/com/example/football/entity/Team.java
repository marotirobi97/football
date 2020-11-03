package com.example.football.entity;
import com.example.football.enums.Nationality;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "nationality")
    @Enumerated(EnumType.STRING)
    private Nationality nationality;

    @Column(name = "number_of_players")
    private Integer numberOfPlayers;

    @Column(name = "number_of_victories")
    private Integer numberOfVictories;

    @OneToMany(mappedBy = "team")
    private List<Player> playerList;
}
