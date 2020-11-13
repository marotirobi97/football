package com.example.football.controller;

import com.example.football.dto.MatchDto;
import com.example.football.dto.PairTeamsDto;
import com.example.football.entity.Championship;
import com.example.football.entity.Match;
import com.example.football.entity.Team;
import com.example.football.enums.Round;
import com.example.football.repository.ChampionshipRepository;
import com.example.football.repository.MatchRepository;
import com.example.football.service.MatchService;
import com.example.football.service.PairTeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class MatchController {

    @ModelAttribute
    public MatchDto matchDto() {
        return new MatchDto();
    }

    @ModelAttribute
    public PairTeamsDto pairTeamsDto(){
        return new PairTeamsDto();
    }

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private ChampionshipRepository championshipRepository;

    @Autowired
    private PairTeamsService pairTeamsService;

    @Autowired
    private MatchService matchService;

    @GetMapping("/list/championships")
    public String listChampionships(Model model){

        List<Championship> championships = championshipRepository.findAllChampionship();
        model.addAttribute("championships", championships);

        return "matches/list-championships";
    }

    @GetMapping("/list/match/{championshipId}")
    public String listMatch (Model model, @PathVariable("championshipId") Integer championshipId, RedirectAttributes redirectAttributes){

        Round currentRound = matchRepository.findCurrentRound(championshipId);
        List<Match> matches = matchRepository.findMatchesById(championshipId,currentRound);

        redirectAttributes.addFlashAttribute("matches", matches);
        model.addAttribute("matches", matches);
        model.addAttribute("championshipId", championshipId);
        model.addAttribute("currentRound", currentRound.toString());

        return "matches/list-match";
    }

    @GetMapping("/play/match/{championshipId}")
    public String playMatch(Model model,@PathVariable("championshipId") Integer championshipId ,@ModelAttribute MatchDto matchDto,RedirectAttributes redirectAttributes){

        Round currentRound = matchRepository.findCurrentRound(championshipId);
        List<Match> matches = matchRepository.findMatchesById(championshipId,currentRound);

        matchService.matchHandler(matchDto, matches);
        List<Match> result = matchRepository.findMatchesById(championshipId,currentRound);

        model.addAttribute("results", result);
        model.addAttribute("championshipId", championshipId);

        matchService.checkRound(model, currentRound);

        return "matches/matches-quarter-semi-final";
    }

    @GetMapping("/generate/match/{championshipId}")
    public String generatePairsWithVictoryTeams(Model model,@PathVariable("championshipId") Integer championshipId,@ModelAttribute PairTeamsDto pairTeamsDto){

        Round currentRound = matchRepository.findCurrentRound(championshipId);
        List<Team> winnerTeams = matchRepository.findAllTheWinnerTeams(championshipId,currentRound);

        pairTeamsDto.setAllTeam(winnerTeams);
        pairTeamsService.allTeamSelectedToDuel(pairTeamsDto);

        Championship championship = championshipRepository.findChampionshipById(championshipId);

        matchService.setNextRound(pairTeamsDto, currentRound, championship);

        return "redirect:/list/match/" + championshipId;
    }
}