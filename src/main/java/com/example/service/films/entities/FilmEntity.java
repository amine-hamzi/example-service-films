package com.example.service.films.entities;

import com.example.service.films.dto.Film;
import com.example.service.films.dto.Genre;
import com.example.service.films.dto.Type;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class FilmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String _year;
    @NotNull
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Genre> genres;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Type type;

    public static FilmEntity fromDTO(Film dto) {
        return FilmEntity.builder()
                .title(dto.getTitle())
                .genres(dto.getGenres())
                .type(dto.getType())
                ._year(dto.getYear())
                .build();
    }

    public Film toDTO() {
        return Film.builder()
                .title(title)
                .type(type)
                .genres(genres)
                .year(_year)
                .build();
    }
}
