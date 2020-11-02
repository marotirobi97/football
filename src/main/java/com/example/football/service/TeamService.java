package com.example.football.service;

import com.example.football.Player;
import com.example.football.dto.TeamDto;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Data
@Service
public class TeamService {


    public String AddPlayerToTeam(int playerId, TeamDto teamDto, RedirectAttributes redirectAttributes, Player selectedPlayer) {
        List<Player> playerList = teamDto.getPlayerList();
        if(playerList.isEmpty()){
            playerList.add(selectedPlayer);
            return "redirect:/team/manage";
        }else{
            for (Player player : teamDto.getPlayerList()) {
                if (player.getId() == playerId) {
                    redirectAttributes.addFlashAttribute("selectErrorMessage", "You can not choose player that you already selected.");
                    return "redirect:/team/manage";
                }
            }
        }
        playerList.add(selectedPlayer);
        return "redirect:/team/manage";
    }
}
