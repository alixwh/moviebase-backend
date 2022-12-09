package ee.taltech.iti0302.webproject.api.controller;

import ee.taltech.iti0302.webproject.util.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.MediaType;




@SpringBootTest
@AutoConfigureMockMvc
class ApiControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void saveGenres() throws Exception{
        mvc.perform(post("/api/public/save/genres"))
                .andDo(print()).andExpect(status().isOk());
        mvc.perform(get("/api/public/genres/27"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Horror"));
    }

    @Test
    void saveMovies() {
    }

    @Test
    void saveMovie() throws Exception{
        mvc.perform(post("/api/public/save/movie/Wakanda"))
                .andDo(print()).andExpect(status().isOk());
        mvc.perform(get("/api/public/search?query=Wakanda"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"));
    }
}