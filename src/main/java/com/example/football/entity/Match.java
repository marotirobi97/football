package com.example.football.entity;

import com.example.football.enums.Round;
import com.example.football.enums.Status;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "match_database")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "team_a")
    private Team teamA;

    @ManyToOne
    @JoinColumn(name = "team_b")
    private Team teamB;

    @Enumerated(EnumType.STRING)
    @Column(name = "round")
    private Round round;

    @OneToOne
    @JoinColumn(name = "championship_id")
    private Championship championship;

    @OneToOne
    @JoinColumn(name = "victory_team")
    private Team victoryTeam;

    @OneToOne
    @JoinColumn(name = "lost_team")
    private Team lostTeam;

    @Column(name = "team_a_chance")
    private Integer teamAChance;

    @Column(name = "team_b_chance")
    private Integer teamBChance;

    @Column(name = "team_a_rnd_number")
    private Integer teamARndNumber;

    @Column(name = "team_b_rnd_number")
    private Integer teamBRndNumber;




}
