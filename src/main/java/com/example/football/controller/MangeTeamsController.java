package com.example.football.controller;

import com.example.football.Player;
import com.example.football.dto.PlayerDto;
import com.example.football.dto.TeamDto;
import com.example.football.repository.PlayerRepository;
import com.example.football.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@RequestMapping
@SessionAttributes("teamDto")
public class MangeTeamsController {

    @ModelAttribute
    public PlayerDto playerDto() {
        return new PlayerDto();
    }

    @ModelAttribute
    public TeamDto teamDto() {
        return new TeamDto();
    }

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping("/team/manage")
    public String manageTeams(Model model,@ModelAttribute TeamDto teamDto){
        model.addAttribute("players", playerRepository.findAll());
        model.addAttribute("selectedPlayerList", teamDto.getPlayerList());
        return "manage-teams";
    }

    @GetMapping("/playerToTeam/{playerId}")
    public String playerToTeam(Model model, @PathVariable("playerId") int playerId, @ModelAttribute TeamDto teamDto,
                               RedirectAttributes redirectAttributes){

        Player selectedPlayer = playerRepository.findPlayer(playerId);
        List<Player> allPlayer = playerRepository.findAll();

        return teamService.AddPlayerToTeam(playerId, teamDto, redirectAttributes, selectedPlayer);
    }


    @GetMapping("/delete/{playerId}")
    public String deleteFromPlayerList(Model model,@PathVariable("playerId") Integer playerId, @ModelAttribute TeamDto teamDto){

        List<Player> playerList = teamDto.getPlayerList();
        playerList.removeIf(player -> playerId.equals(player.getId()));

        return "redirect:/team/manage";
    }

    @PostMapping("/team/create")
    public String createTeam(Model model,@ModelAttribute TeamDto teamDto,RedirectAttributes redirectAttributes){
        if(teamDto.getPlayerList().size() < 3){
            redirectAttributes.addFlashAttribute("createTeamError", "You can not create team less than 3 player.");
            return "redirect:/team/manage";
        }

        return "redirect:/team/manage";
    }
}
