package com.example.service.films.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Type {
    @JsonProperty("serie")
    SERIE,
    @JsonProperty("movie")
    MOVIE;

}
