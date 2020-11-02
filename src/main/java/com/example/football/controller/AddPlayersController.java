package com.example.football.controller;

import com.example.football.Player;
import com.example.football.dto.PlayerDto;
import com.example.football.enums.Nationality;
import com.example.football.repository.PlayerRepository;
import com.example.football.repository.TeamRepository;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@Builder
@RequestMapping
public class AddPlayersController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    @ModelAttribute
    public PlayerDto playerDto() {
        return new PlayerDto();
    }

    @PostMapping("/addPlayer")
    public String AddPlayer(@ModelAttribute PlayerDto playerDto, RedirectAttributes redirectAttributes){
        if(playerDto.getName().isEmpty() || playerDto.getHeight() == null || playerDto.getSkillLevel() == null){
            redirectAttributes.addFlashAttribute("createPlayerError", "Cannot create playerDto.");
            return "redirect:/playerAndTeam";
        }
        Player player = getBuildPlayer(playerDto);
        playerRepository.save(player);

        return "redirect:/players";
    }

    private Player getBuildPlayer(PlayerDto playerDto) {
        return Player.builder().id(playerDto.getId()).name(playerDto.getName()).dominantLeg(playerDto.getDominantLeg()).height(playerDto.getHeight()).nationality(playerDto.getNationality()).positions(playerDto.getPositions()).skillLevel(playerDto.getSkillLevel()).build();
    }

    @GetMapping("/players")
    public String Players(Model model){
        model.addAttribute("players",playerRepository.findAll());
        return "players";
    }

//    @GetMapping("/list")
//    public String ListTeams(Model model, @ModelAttribute("teams") Team team){
//        model.addAttribute("teams", teamRepository.findAll());
//        model.addAttribute("team", team);
//        return "redirect:/playerAndTeam";
//    }

    @GetMapping("/requestedPlayer/{playerId}")
    public String requestedPlayer(Model model, @PathVariable("playerId") int playerId){
        Player player = playerRepository.findPlayer(playerId);
        model.addAttribute("requestedPlayer", player);
        return "requested-player";
    }
}
