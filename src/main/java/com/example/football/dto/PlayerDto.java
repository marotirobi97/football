package com.example.football.dto;

import com.example.football.enums.DominantLeg;
import com.example.football.enums.Nationality;
import com.example.football.enums.Positions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {

    private Integer id;
    private String name;
    private Integer height;
    private Nationality nationality;
    private Integer skillLevel;
    private DominantLeg dominantLeg;
    private Positions positions;
}
