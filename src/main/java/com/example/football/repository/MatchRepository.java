package com.example.football.repository;

import com.example.football.entity.Match;
import com.example.football.entity.Team;
import com.example.football.enums.Round;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends CrudRepository<Match,Integer> {

    @Query("FROM Match m WHERE m.championship.id = :id AND m.round = :currentRound")
    List<Match> findMatchesById(Integer id, Round currentRound);

    @Query("SELECT m.victoryTeam FROM Match m WHERE m.championship.id = :championshipId AND m.round = :currentRound")
    List<Team> findAllTheWinnerTeams(Integer championshipId,Round currentRound);

    @Query("SELECT m.round FROM Match m WHERE m.championship.id = :championshipId AND m.id=(SELECT MAX(m.id) FROM Match m)")
    Round findCurrentRound(Integer championshipId);
}
