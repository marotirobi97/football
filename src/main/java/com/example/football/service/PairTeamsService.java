package com.example.football.service;

import com.example.football.dto.PairTeamsDto;
import com.example.football.dto.MatchDto;
import com.example.football.entity.*;
import com.example.football.repository.ChampionshipRepository;
import com.example.football.repository.ChampionshipTeamRepository;
import com.example.football.repository.MatchRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class PairTeamsService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private ChampionshipRepository championshipRepository;

    @Autowired
    private ChampionshipTeamRepository championshipTeamRepository;

    @Autowired
    private MatchRepository sortedTeamsRepository;

    public void allTeamSelectedToDuel(PairTeamsDto pairTeamsDto) {

        List<Match> matchList = new ArrayList<>();
        List<Team> allTeam = pairTeamsDto.getAllTeam();
        List<Team> selectedTeams = pairTeamsDto.getSelectedTeams();

        for (int i = 0; i < allTeam.size()/2; i++) {
            int randomTeamIndex = (int) (Math.random() * (allTeam.size()));
            if(selectedTeams.contains(allTeam.get(randomTeamIndex))){
                i--;
                continue;
            }

            Team teamA = allTeam.get(randomTeamIndex);
            selectedTeams.add(teamA);

            Team teamB = searchOpponentTeam(allTeam, selectedTeams);

            Match match = new Match();
            match.setTeamA(teamA);
            match.setTeamB(teamB);
            selectedTeams.add(teamB);
            matchList.add(match);
        }
        pairTeamsDto.setMatches(matchList);
    }

    private Team searchOpponentTeam(List<Team> allTeam, List<Team> selectedTeams) {
        for (int i = 0; i < allTeam.size()/2; i++) {
            int randomTeamIndex = (int) (Math.random() * (allTeam.size()));
            Team opponentTeam = allTeam.get(randomTeamIndex);
            if(selectedTeams.contains(allTeam.get(randomTeamIndex))) {
                i--;
                continue;
            }

            return opponentTeam;
        }

        return null;
    }

    public void setAndSaveRepository(Championship championship, List<Match> matchesForSave) {
        championshipRepository.save(championship);
        championship.setName("Championship_" + championship.getId());

        for (Match match : matchesForSave){
            ChampionshipTeam teamA = new ChampionshipTeam();
            ChampionshipTeam teamB = new ChampionshipTeam();
            teamA.setChampionship(championship);
            teamB.setChampionship(championship);

            match.setRound(championship.getRound());
            match.setChampionship(championship);

            teamA.setTeam(match.getTeamA());
            teamB.setTeam(match.getTeamB());

            championshipTeamRepository.save(teamA);
            championshipTeamRepository.save(teamB);
            matchRepository.save(match);
        }
    }

    public String checkIfSelectedTeamSizeEight(Model model, PairTeamsDto pairTeamsDto) {
        if(pairTeamsDto.getSelectedTeams().size() != 8){
            model.addAttribute("EightTeamError","8 Team needed to create a champion.(Not more or less)");
            return "matches/pair-team";
        }
        return null;
    }
}
