package com.example.football.repository;

import com.example.football.entity.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends CrudRepository<Team, Integer> {

    @Query("FROM Team t WHERE t.id = :teamId")
    Team finById(Integer teamId);

    List<Team> findAll();

}
