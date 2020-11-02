package com.example.football.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DominantLeg {
    RIGHT("Right"),
    LEFT("Left");

    private final String dominantLeg;

    public String getDominantLeg() {
        return dominantLeg;
    }
}
