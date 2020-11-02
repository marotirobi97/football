package com.example.football.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Nationality {
    HUNGARIAN("Hungarian"),
    BRITISH("British"),
    ITALIAN("Italian"),
    PORTUGUESE("Portuguese"),
    GERMAN("German");

    private final String nationality;

    public String getNationality() {
        return nationality;
    }
}
