package com.example.football.service;

import com.example.football.entity.Player;
import com.example.football.dto.TeamDto;
import com.example.football.entity.Team;
import com.example.football.repository.PlayerRepository;
import com.example.football.repository.TeamRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Data
@Slf4j
@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public String addPlayerToTeam(int playerId, TeamDto teamDto, RedirectAttributes redirectAttributes, Player selectedPlayer) {
        List<Player> playerList = teamDto.getPlayerList();
        if(playerList.isEmpty()){
            playerList.add(selectedPlayer);
            return "redirect:/team/manage";
        }else{
            for (Player player : teamDto.getPlayerList()) {
                if (player.getId() == playerId) {
                    redirectAttributes.addFlashAttribute("selectErrorMessage", "You can not select a player that you already chose.");
                    return "redirect:/team/manage";
                }
            }
        }
        playerList.add(selectedPlayer);
        return "redirect:/team/manage";
    }

    public String checkTeamSizeRequirement(RedirectAttributes redirectAttributes, int playerListSize) {
        if(playerListSize < 3){
            redirectAttributes.addFlashAttribute("createTeamError", "You can not create team less than 3 player.");
            return "redirect:/team/manage";
        }
        return null;
    }

    public void createTeam(TeamDto teamDto, RedirectAttributes redirectAttributes, int playerListSize) {
        teamDto.setNumberOfPlayers(playerListSize);
        teamDto.setNumberOfVictories(0);
        Team team = Team.builder().name(teamDto.getName()).address(teamDto.getAddress()).nationality(teamDto.getNationality()).numberOfPlayers(teamDto.getNumberOfPlayers()).numberOfVictories(teamDto.getNumberOfVictories()).build();
        team = teamRepository.save(team);

        for (Player player : teamDto.getPlayerList()) {
            player.setTeam(team);
            playerRepository.save(player);
        }
        teamDto.getPlayerList().removeIf(player -> player.getTeam() != null);

        teamDto.setAddress(null);
        teamDto.setName(null);
        redirectAttributes.addFlashAttribute("teamCreated", "New team has created.");
    }
}
