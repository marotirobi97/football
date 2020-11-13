package com.example.football.dto;

import com.example.football.entity.Team;
import lombok.Data;

@Data
public class MatchDto {

    private Team team;
    private Team opponent;
    private int skillAvgTeamA;
    private int skillAvgTeamB;
    private int rndNumberTeamA;
    private int rndNumberTeamB;
    private Team victoryTeam;
    private Team lostTeam;

}
