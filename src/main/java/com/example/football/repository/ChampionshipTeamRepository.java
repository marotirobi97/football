package com.example.football.repository;

import com.example.football.entity.ChampionshipTeam;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ChampionshipTeamRepository extends CrudRepository<ChampionshipTeam,Integer> {

    @Query("FROM ChampionshipTeam ct JOIN FETCH Match m WHERE m.victoryTeam is null")
    List<ChampionshipTeam> findQuarterFinalist();

}
