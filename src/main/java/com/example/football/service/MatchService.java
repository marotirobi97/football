package com.example.football.service;

import com.example.football.dto.MatchDto;
import com.example.football.dto.PairTeamsDto;
import com.example.football.entity.Championship;
import com.example.football.entity.Match;
import com.example.football.entity.Player;
import com.example.football.entity.Team;
import com.example.football.enums.Round;
import com.example.football.repository.MatchRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    public int chanceOfVictoryForTeamA(Team team, MatchDto matchDto){
        List<Integer> list = calculateRandomNumberAndSkillAvg(team);

        Integer rndNumber = list.get(0);
        Integer skillAvg = list.get(1);

        matchDto.setRndNumberTeamA(rndNumber);
        matchDto.setSkillAvgTeamA(skillAvg);

        return rndNumber * skillAvg;
    }

    public int chanceOfVictoryForTeamB(Team team, MatchDto matchDto){
        List<Integer> list = calculateRandomNumberAndSkillAvg(team);

        Integer rndNumber = list.get(0);
        Integer skillAvg = list.get(1);

        matchDto.setRndNumberTeamB(rndNumber);
        matchDto.setSkillAvgTeamB(skillAvg);

        return rndNumber * skillAvg;
    }

    private List<Integer> calculateRandomNumberAndSkillAvg(Team team) {
        List<Player> playerList = team.getPlayerList();

        int skillLevelAvg = 0;

        for (Player player : playerList) {
            skillLevelAvg += player.getSkillLevel();
        }
        int rnd = (int) (Math.random() * (21 - 1))+1;
        List<Integer> rndNumberAndSkillAvg = new ArrayList<>();
        rndNumberAndSkillAvg.add(rnd);
        rndNumberAndSkillAvg.add(skillLevelAvg);

        return rndNumberAndSkillAvg;
    }


    public void increaseSkillLevelWith2(Team team){
        List<Player> playerList = team.getPlayerList();
        for (Player player : playerList) {
            player.addSkillLevel(2);
        }
    }

    public void decreaseSkillLevelWith2(Team team){
        List<Player> playerList = team.getPlayerList();
        for (Player player : playerList) {
            player.subtractSkillLevel(2);
        }
    }

    public void matchHandler(MatchDto matchDto, List<Match> matches) {
        for (Match match : matches) {
            Team currentTeamA = match.getTeamA();
            Team currentTeamB = match.getTeamB();

            int chanceForA = chanceOfVictoryForTeamA(currentTeamA, matchDto);
            int chanceForB = chanceOfVictoryForTeamB(currentTeamB, matchDto);

            matchDto.setTeam(currentTeamA);
            matchDto.setOpponent(currentTeamB);

            if (chanceForA > chanceForB) {
                increaseSkillLevelWith2(currentTeamA);
                decreaseSkillLevelWith2(currentTeamB);
                match.setVictoryTeam(currentTeamA);
                match.setLostTeam(currentTeamB);
            }else{
                increaseSkillLevelWith2(currentTeamB);
                decreaseSkillLevelWith2(currentTeamA);
                match.setVictoryTeam(currentTeamB);
                match.setLostTeam(currentTeamA);
            }
            match.setTeamAChance(matchDto.getSkillAvgTeamA());
            match.setTeamARndNumber(matchDto.getRndNumberTeamA());
            match.setTeamBChance(matchDto.getSkillAvgTeamB());
            match.setTeamBRndNumber(matchDto.getRndNumberTeamB());
            matchRepository.save(match);
        }
    }

    public void checkRound(Model model, Round currentRound) {
        if(currentRound == Round.QUARTERFINAL || currentRound == Round.SEMIFINAL){
            model.addAttribute("beforeFinal", "Next Round");
        }else{
            model.addAttribute("Done", "Done");
        }
    }

    public void setNextRound(PairTeamsDto pairTeamsDto, Round currentRound, Championship championship) {
        for (Match match : pairTeamsDto.getMatches()) {
            match.setChampionship(championship);

            if(currentRound == Round.QUARTERFINAL){
                match.setRound(Round.SEMIFINAL);
            }else{
                match.setRound(Round.FINAL);
            }
            matchRepository.save(match);
        }
    }
}
