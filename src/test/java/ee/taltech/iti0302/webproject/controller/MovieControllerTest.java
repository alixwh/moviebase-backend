package ee.taltech.iti0302.webproject.controller;

import ee.taltech.iti0302.webproject.util.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
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
        mvc.perform(get("http://localhost:8080/api/public/movies/1").with(user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Black Panther: Wakanda Forever"));
    }

    @Test
    void getMovies() throws Exception {
        mvc.perform(get("http://localhost:8080/api/public/movies").with(user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title").value("Black Panther: Wakanda Forever"));
    }

    @Test
    void getMoviesByActor() {
    }

    @Test
    void getMoviesByGenreId() {
    }

    @Test
    void getMoviesByReleaseYear() {
    }

    @Test
    void getMoviesByName() {
    }

    @Test
    void getMoviesByYearsAndGenres() {
    }
}