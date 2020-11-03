package com.example.football.dto;

import com.example.football.entity.Player;
import com.example.football.enums.Nationality;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TeamDto {

    private Integer id;
    private String name;
    private String address;
    private Nationality nationality;
    private Integer numberOfPlayers;
    private Integer numberOfVictories;

    private List<Player> playerList = new ArrayList<>();
}
