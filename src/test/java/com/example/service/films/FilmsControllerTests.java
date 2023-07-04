package com.example.service.films;

import com.example.service.films.controllers.FilmsController;
import com.example.service.films.services.FilmService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.when;

class FilmsControllerTests {

    @Mock
    private FilmService filmService;

    @InjectMocks
    private FilmsController filmsController;

    private final MockMvc mockMvc;

    public FilmsControllerTests() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(filmsController).build();
    }

    @Test
    void testGetFilmsByName_Success() throws Exception {
        String filmName = "example";
        when(filmService.getNumberOfFilmsByGenre(filmName)).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/films")
                .param("name", filmName))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }
}
