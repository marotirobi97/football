package com.example.football.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Positions {
    GK("GoalKeeper"),
    DEF("Defender"),
    MF("Midfield"),
    F("Striker");

    private final String position;

    public String getPosition() {
        return position;
    }
}
