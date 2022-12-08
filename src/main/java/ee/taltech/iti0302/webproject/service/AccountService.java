package ee.taltech.iti0302.webproject.service;

import ee.taltech.iti0302.webproject.account.AccountRole;
import ee.taltech.iti0302.webproject.account.login.LoginResponse;
import ee.taltech.iti0302.webproject.account.register.CreateAccountRequest;
import ee.taltech.iti0302.webproject.account.register.RegisterResponse;
import ee.taltech.iti0302.webproject.dto.AccountDto;
import ee.taltech.iti0302.webproject.dto.MovieDto;
import ee.taltech.iti0302.webproject.entities.Account;
import ee.taltech.iti0302.webproject.entities.Movie;
import ee.taltech.iti0302.webproject.exception.ApplicationException;
import ee.taltech.iti0302.webproject.mapper.AccountMapper;
import ee.taltech.iti0302.webproject.mapper.MovieMapper;
import ee.taltech.iti0302.webproject.repository.AccountRepository;
import ee.taltech.iti0302.webproject.repository.MovieRepository;
import ee.taltech.iti0302.webproject.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final MovieRepository movieRepository;
    private final AccountMapper accountMapper;
    private final MovieMapper movieMapper;
    private final JwtTokenProvider tokenProvider;
    private final BCryptPasswordEncoder passwordEncoder;
    private static final String NO_ACCOUNT_FOUND = "No account found";

    public List<AccountDto> findAll() {
        return accountMapper.toDtoList(accountRepository.findAll());
    }

    public AccountDto findById(int id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        Account account = optionalAccount.orElseThrow(() -> new ApplicationException("Account not found"));
        return accountMapper.toDto(account);
    }

    public AccountDto findByName(String username) {
        return accountMapper.toDto(accountRepository.findByUsername(username).orElse(null));
    }

    public RegisterResponse createAccount(CreateAccountRequest createAccountRequest) {
        Optional<Account> optionalAccount = accountRepository.findByUsername(createAccountRequest.getUsername());
        optionalAccount.ifPresent(error -> {throw new ApplicationException("Username taken");});
        Account account = accountMapper.toEntity(createAccountRequest);
        account.setPassword(passwordEncoder.encode(createAccountRequest.getPassword()));
        account.setAccountRole(AccountRole.ROLE_USER);
        accountRepository.save(account);
        return new RegisterResponse("register successful");
    }

    public LoginResponse login(String username, String password) {
        Optional<Account> optionalAccount = accountRepository.findByUsername(username);
        Account account = optionalAccount.orElseThrow(() -> new ApplicationException(NO_ACCOUNT_FOUND));
        if (passwordEncoder.matches(password, account.getPassword())) {
            String token = tokenProvider.generateToken(username, account.getId(), account.getAccountRole());
            return new LoginResponse(token);
        } else {
            throw new ApplicationException("Wrong password");
        }
    }

    public void delete(Integer id) {
        accountRepository.deleteById(id);
    }

    public void addMovieToAccount(int accountId, int movieId, String state) {
        addOrChangeMovieState(accountId, movieId, state);
    }

    public void changeMovieState(int accountId, int movieId, String state) {
        addOrChangeMovieState(accountId, movieId, state);
    }

    private void addOrChangeMovieState(int accountId, int movieId, String state) {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        Movie movie = optionalMovie.orElseThrow(() -> new ApplicationException("No movie found"));
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        Account account = optionalAccount.orElseThrow(() -> new ApplicationException(NO_ACCOUNT_FOUND));
        account.addMovie(movie, state);
        accountRepository.save(account);
    }

    public List<MovieDto> findWatchlist(Integer id, String state) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        Account account = optionalAccount.orElseThrow(() -> new ApplicationException(NO_ACCOUNT_FOUND));
        Map<Movie, String> movieMap = account.getMovieMap();
        List<Movie> movies = movieMap.keySet()
                .stream()
                .filter(movie -> movieMap.get(movie).equals(state))
                .toList();
        return movieMapper.toDtoList(movies);
    }

    public void deleteMovieFromList(Integer movieId, String name) {
        Optional<Account> optionalAccount = accountRepository.findByUsername(name);
        Account account = optionalAccount.orElseThrow(() -> new ApplicationException(NO_ACCOUNT_FOUND));
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        Movie movie = optionalMovie.orElseThrow(() -> new ApplicationException("No movie found"));
        account.getMovieMap().remove(movie);
        accountRepository.save(account);
    }
}
