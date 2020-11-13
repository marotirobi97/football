package com.example.football.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Round {
    QUARTERFINAL("Quarter-final"),
    SEMIFINAL("Semi-final"),
    FINAL("Final");

    private final String round;

    public String getRound() {
        return round;
    }
}
