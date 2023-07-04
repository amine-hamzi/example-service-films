package com.example.service.films.services;

import com.example.service.films.dto.FilmsFile;
import com.example.service.films.entities.FilmEntity;
import com.example.service.films.repositories.FilmRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Getter
@Setter
public class FilmServiceImpl implements FilmService {

    @Value("${omdb.api.url}" +"?apikey="+ "${omdb.api.key}")
    private String omdbApi;
    private FilmRepository filmRepository;
    private final OkHttpClient client =  new OkHttpClient();

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }


    @Override
    public void parseAndSaveFilms(MultipartFile file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        FilmsFile filmsFile = objectMapper.readValue(file.getInputStream(), new TypeReference<FilmsFile>() {});
        filmsFile.getFilms().forEach(film -> filmRepository.save(FilmEntity.fromDTO(film)));
    }

    @Override
    public List<Object> getNumberOfFilmsByGenre(String filmName) {
        return filmRepository.countFilmsByGenre(filmName);
    }


    public Object searchMovies(String year, String name) throws IOException {

        String url = omdbApi+ "&s=" + name+"&y=" + year;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> res = objectMapper.readValue(responseBody, new TypeReference<Map<String, Object>>() {});
                return res.get("Search");
            } else {
                throw new IOException("API request failed with status code: " + response.code());
            }
        }
    }

    public Object getMovieDetails(String imdbId) throws IOException {

        Request request = new Request.Builder()
                .url(omdbApi+"&i=" + imdbId)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(responseBody, new TypeReference<Map<String, Object>>() {});
            } else {
                throw new IOException("API request failed with status code: " + response.code());
            }
        }
    }
}
