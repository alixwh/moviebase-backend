package ee.taltech.iti0302.webproject.controller;

import ee.taltech.iti0302.webproject.util.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getMovieById() throws Exception {
        mvc.perform(get("/api/public/movies/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Black Panther: Wakanda Forever"));
    }

    @Test
    void getMovies() throws Exception {
        mvc.perform(get("/api/public/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value("1"));
    }

    @Test
    void getMoviesByActor() throws Exception {
        mvc.perform(get("/api/public/movies/actor/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].title").value("Black Panther: Wakanda Forever"));
    }

    @Test
    void getMoviesByGenreId() throws Exception {
        mvc.perform(get("/api/public/movies/genre/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].title").value("Black Panther: Wakanda Forever"));
    }

    @Test
    void getMoviesByReleaseYear() throws Exception {
        mvc.perform(get("/api/public/movies/year/2022"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].title").value("Black Panther: Wakanda Forever"))
                .andExpect(jsonPath("$.[1].title").value("Fall"));
    }

    @Test
    void getMoviesByName() throws Exception{
        mvc.perform(get("/api/public/search?query=Fall"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].title").value("Fall"));
    }

    @Test
    void getMoviesByYearsAndGenres() throws Exception {
        mvc.perform(get("/api/public/filter?genre=2&year=2022"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title").value("Fall"));
    }
}
