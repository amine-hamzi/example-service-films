package com.example.service.films.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Film {

    private String title;
    private String year;
    private List<Genre> genres;
    private Type type;

    @JsonCreator
    public Film(@JsonProperty("Title") String title,
                @JsonProperty("Year") String year,
                @JsonProperty("Genre") String genres,
                @JsonProperty("Type") Type type) {
        this.title = title;
        this.year = year;
        this.type = type;
        this.genres = genres.isEmpty() ? Collections.emptyList() : Arrays.stream(genres.split(","))
                .map(String::trim)
                .map(genre->genre.replace("-","_"))
                .map(String::toUpperCase)
                .map(Genre::valueOf)
                .collect(Collectors.toList());
    }
}
