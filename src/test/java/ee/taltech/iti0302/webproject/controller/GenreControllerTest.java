package ee.taltech.iti0302.webproject.controller;

import ee.taltech.iti0302.webproject.util.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class GenreControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getGenreById() throws Exception {
        mvc.perform(get("/api/public/genres/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("action"));
    }

    @Test
    void getGenres() throws Exception {
        mvc.perform(get("/api/public/genres"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("action"))
                .andExpect(jsonPath("$.[1].name").value("thriller"));
    }
}