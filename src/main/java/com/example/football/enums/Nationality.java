package com.example.football.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public enum Nationality {
    HUNGARIAN("Hungarian"),
    BRITISH("British"),
    ITALIAN("Italian"),
    PORTUGUESE("Portuguese"),
    GERMAN("German");

    private final String nationality;

    Nationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }
}
