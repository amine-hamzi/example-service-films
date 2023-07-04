package com.example.service.films.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FilmService {

    void parseAndSaveFilms(MultipartFile file) throws IOException;
    List<Object> getNumberOfFilmsByGenre(String filmName);
    Object searchMovies(String year, String name) throws IOException;
    Object getMovieDetails(String imdbId) throws IOException;
}
