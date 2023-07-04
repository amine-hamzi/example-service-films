package com.example.service.films.controllers;

import com.example.service.films.services.FilmService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class FilmsController {

    private FilmService filmService;

    @PostMapping("films/upload")
    public ResponseEntity<String> uploadBulkFilms(@RequestParam("file") MultipartFile file) {

        try {
            filmService.parseAndSaveFilms(file);
            return ResponseEntity.ok("File processed successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @GetMapping("films")
    public ResponseEntity<List<Object>> getNumberOfFilmsByGenre(@RequestParam("name") String filmName) {
        return ResponseEntity.ok().body(filmService.getNumberOfFilmsByGenre(filmName));
    }

    @GetMapping("omdb/films")
    public ResponseEntity<Object> getOMDBFilmsByNameAndYear(@RequestParam(value = "name")  String filmName, @RequestParam(value = "year") String year) {
        try {
            return ResponseEntity.ok().body(filmService.searchMovies(year, filmName));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }

    @GetMapping("omdb/films/{imdbID}")
    public ResponseEntity<Object> getOMDBFilmsByNameAndYear(@PathVariable("imdbID") String imdbID) {
        try {
            return ResponseEntity.ok().body(filmService.getMovieDetails(imdbID));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }


}
