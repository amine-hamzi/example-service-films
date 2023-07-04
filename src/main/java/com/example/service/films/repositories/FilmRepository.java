package com.example.service.films.repositories;

import com.example.service.films.entities.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmRepository extends JpaRepository<FilmEntity, Long> {

    List<FilmEntity> findByTitleContains(String title);
    @Query("SELECT fg, count(fg) FROM FilmEntity f JOIN f.genres fg where f.title like %:title% group by fg")
    List<Object> countFilmsByGenre(@Param("title") String title);
}
