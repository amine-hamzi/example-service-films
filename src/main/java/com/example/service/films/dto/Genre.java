package com.example.service.films.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Genre {
    ACTION("ACTION"),
    ADVENTURE("ADVENTURE"),
    FANTASY("FANTASY"),
    SCI_FI("SCI_FI"),
    ANIMATION("ANIMATION"),
    FAMILY("FAMILY"),
    REALITY_TV("REALITY_TV"),
    DRAMA("DRAMA"),
    MUSIC("MUSIC"),
    ROMANCE("ROMANCE");

    private String value;
}
