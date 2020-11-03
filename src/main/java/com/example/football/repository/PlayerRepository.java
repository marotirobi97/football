package com.example.football.repository;

import com.example.football.entity.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {

    @Query("SELECT p FROM Player p WHERE p.id = :id")
    Player findPlayer(Integer id);

    @Query("SELECT p FROM Player p WHERE p.team is null")
    List<Player> findAllPlayerWhoIsNotInATeam();

    List<Player> findAll();
}
