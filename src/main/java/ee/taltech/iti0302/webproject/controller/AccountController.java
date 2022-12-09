package ee.taltech.iti0302.webproject.controller;

import ee.taltech.iti0302.webproject.account.MovieListRequest;
import ee.taltech.iti0302.webproject.account.login.LoginRequest;
import ee.taltech.iti0302.webproject.account.login.LoginResponse;
import ee.taltech.iti0302.webproject.account.register.CreateAccountRequest;
import ee.taltech.iti0302.webproject.account.register.RegisterResponse;
import ee.taltech.iti0302.webproject.dto.AccountDto;
import ee.taltech.iti0302.webproject.dto.MovieDto;
import ee.taltech.iti0302.webproject.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api")
@RestController
public class AccountController {

    private final AccountService accountService;

    @GetMapping("account/{id}")
    public AccountDto getAccountById(@PathVariable("id") int id) {
        return accountService.findById(id);
    }

    @GetMapping("accounts")
    public List<AccountDto> getAccounts(Principal principal) {
        return accountService.findAll();
    }

    @GetMapping("account")
    public AccountDto getAccount(Principal principal) {
        return accountService.findByName(principal.getName());
    }

    @PostMapping("public/register")
    public RegisterResponse createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        return accountService.createAccount(createAccountRequest.getUsername(), createAccountRequest.getPassword());
    }

    @PostMapping("public/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return accountService.login(loginRequest.getUsername(), loginRequest.getPassword());
    }

    @DeleteMapping("account/delete/{id}")
    public void delete(@PathVariable Integer id) {
        accountService.delete(id);
    }

    @PostMapping("account/movielist/add")
    public void addMovie(@RequestBody MovieListRequest movieListRequest) {
        accountService.addMovieToAccount(movieListRequest.getAccountId(), movieListRequest.getMovieId(), movieListRequest.getState());
    }

    @PutMapping("account/movielist/change")
    public void changeMovieState(@RequestBody MovieListRequest movieListRequest){
        accountService.changeMovieState(movieListRequest.getAccountId(), movieListRequest.getMovieId(), movieListRequest.getState());
    }
    @GetMapping("account/watchlist/{id}/{state}")
    public List<MovieDto> getMovieMap(@PathVariable Integer id, @PathVariable String state) {
        return accountService.findWatchlist(id, state);
    }

    @DeleteMapping("account/movie/delete/{movieId}")
    public void deleteMovieFromList(@PathVariable Integer movieId, Principal principal) {
        accountService.deleteMovieFromList(movieId, principal.getName());
    }

}
