package com.example.football.repository;

import com.example.football.entity.Championship;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ChampionshipRepository extends CrudRepository<Championship,Integer> {

    @Query("FROM Championship c")
    List<Championship> findAllChampionship();

    @Query("FROM Championship c WHERE c.id = :id")
    Championship findChampionshipById(Integer id);
}
