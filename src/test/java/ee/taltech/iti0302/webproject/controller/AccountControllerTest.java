package ee.taltech.iti0302.webproject.controller;

import ee.taltech.iti0302.webproject.account.register.CreateAccountRequest;
import ee.taltech.iti0302.webproject.util.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest extends AbstractIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void getAccountById() {
//        mvc.perform(get("/api/public/accounts"))
    }

    @Test
    void getAccounts() {
    }

    @Test
    void getAccount() {
    }

    @Test
    void createAccount() throws Exception {
        mvc.perform(post("/api/public/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"test\",\"password\":\"test\"}"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.reason").value("register successful"));
    }
    @Test
    void login() {
    }

    @Test
    void delete() {
    }

    @Test
    void addMovie() {
    }

    @Test
    void changeMovieState() {
    }

    @Test
    void getMovieMap() {
    }

    @Test
    void deleteMovieFromList() {
    }
}
