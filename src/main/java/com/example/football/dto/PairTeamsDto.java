package com.example.football.dto;

import com.example.football.entity.Match;
import com.example.football.entity.Team;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PairTeamsDto {

    private List<Team> selectedTeams = new ArrayList<>();
    private List<Team> allTeam = new ArrayList<>();
    private List<Match> matches = new ArrayList<>();

}
