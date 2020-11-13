package com.example.football.controller;

import com.example.football.dto.PairTeamsDto;
import com.example.football.dto.MatchDto;
import com.example.football.entity.Championship;
import com.example.football.entity.Match;
import com.example.football.repository.TeamRepository;
import com.example.football.service.PairTeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@RequestMapping
@SessionAttributes("pairTeamsDto")
@Controller
public class SelectForDuelController {

    @ModelAttribute
    public PairTeamsDto pairTeamsDto() {
        return new PairTeamsDto();
    }

    @ModelAttribute
    public MatchDto matchDto() {
        return new MatchDto();
    }

    @Autowired
    private PairTeamsService pairTeamsService;

    @Autowired
    private TeamRepository teamRepository;

    private List<Match> matchesForSave;

    @GetMapping("/selected/team/{teamId}")
    public String selectedTeam(Model model,@PathVariable("teamId") Integer teamId,@ModelAttribute PairTeamsDto pairTeamsDto){
        if(pairTeamsDto.getSelectedTeams().contains(teamRepository.finById(teamId))){
            model.addAttribute("SameTeam","You can't choose the same Team");
            return "matches/pair-team";
        }
        pairTeamsDto.getSelectedTeams().add(teamRepository.finById(teamId));

        return "redirect:/select/duel";
    }

    @GetMapping("/select/team")
    public String selectTeamsToDuel(Model model, RedirectAttributes redirectAttributes, @ModelAttribute PairTeamsDto pairTeamsDto){

        String x = pairTeamsService.checkIfSelectedTeamSizeEight(model, pairTeamsDto);
        if (x != null) return x;
        pairTeamsDto.getAllTeam().addAll(pairTeamsDto.getSelectedTeams());
        pairTeamsService.allTeamSelectedToDuel(pairTeamsDto);
        redirectAttributes.addFlashAttribute("pairTeamsDto", pairTeamsDto);

        return "redirect:/select/duel";
    }

    @GetMapping("/select/duel")
    public String duelSelector(Model model,@ModelAttribute("pairTeamsDto") PairTeamsDto pairTeamsDto){
        model.addAttribute("pairTeamsDto", pairTeamsDto);
        model.addAttribute("selectedTeamList", pairTeamsDto.getSelectedTeams());
        model.addAttribute("teams", teamRepository.findAll());
        matchesForSave = new ArrayList<>();
        matchesForSave.addAll(pairTeamsDto.getMatches());

        return "matches/pair-team";
    }

    @PostMapping("/save")
    public String saveMatches(@ModelAttribute PairTeamsDto pairTeamsDto){
        Championship championship = new Championship();

        pairTeamsService.setAndSaveRepository(championship, matchesForSave);

        pairTeamsDto.getSelectedTeams().removeAll(pairTeamsDto().getSelectedTeams());

        return "redirect:/select/duel";
    }
}