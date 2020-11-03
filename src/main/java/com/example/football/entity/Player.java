package com.example.football.entity;

import com.example.football.enums.DominantLeg;
import com.example.football.enums.Nationality;
import com.example.football.enums.Positions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "height")
    private Integer height;

    @Column(name = "nationality")
    @Enumerated(EnumType.STRING)
    private Nationality nationality;

    @Column(name = "skill_level")
    private Integer skillLevel;

    @Column(name = "dominant_leg")
    @Enumerated(EnumType.STRING)
    private DominantLeg dominantLeg;

    @Column(name = "positions")
    @Enumerated(EnumType.STRING)
    private Positions positions;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
