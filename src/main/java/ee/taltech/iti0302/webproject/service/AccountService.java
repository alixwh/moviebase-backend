package ee.taltech.iti0302.webproject.service;

import ee.taltech.iti0302.webproject.account.AccountRole;
import ee.taltech.iti0302.webproject.account.login.LoginResponse;
import ee.taltech.iti0302.webproject.account.register.CreateAccountRequest;
import ee.taltech.iti0302.webproject.account.register.RegisterResponse;
import ee.taltech.iti0302.webproject.dto.AccountDto;
import ee.taltech.iti0302.webproject.dto.MovieDto;
import ee.taltech.iti0302.webproject.entities.Account;
import ee.taltech.iti0302.webproject.entities.Movie;
import ee.taltech.iti0302.webproject.mapper.AccountMapper;
import ee.taltech.iti0302.webproject.mapper.MovieMapper;
import ee.taltech.iti0302.webproject.repository.AccountRepository;
import ee.taltech.iti0302.webproject.repository.MovieRepository;
import ee.taltech.iti0302.webproject.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContextException;
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

    public List<AccountDto> findAll() {
        return accountMapper.toDtoList(accountRepository.findAll());
    }

    public AccountDto findById(int id) {
        return accountMapper.toDto(accountRepository.findById(id).orElse(null));
    }

    public AccountDto findByName(String username) {
        return accountMapper.toDto(accountRepository.findByUsername(username).orElse(null));
    }

    public RegisterResponse createAccount(CreateAccountRequest createAccountRequest) {
        Account account = accountMapper.toEntity(createAccountRequest);
        account.setPassword(passwordEncoder.encode(createAccountRequest.getPassword()));
        account.setAccountRole(AccountRole.ROLE_USER);

        accountRepository.save(account);
        // temporary !!!
        return new RegisterResponse("register successful");
    }

    public LoginResponse login(String username, String password) {
        Optional<Account> optionalAccount = accountRepository.findByUsername(username);
        if (optionalAccount.isEmpty()) {
            throw new ApplicationContextException("No user found");
        }
        Account account = optionalAccount.get();
        if (passwordEncoder.matches(password, account.getPassword())) {
            String token = tokenProvider.generateToken(username, account.getId(), account.getAccountRole());
            return new LoginResponse(token);
        } else {
            throw new ApplicationContextException("Wrong username or password");
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
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            Optional<Account> byId = accountRepository.findById(accountId);
            if (byId.isPresent()) {
                Account account = byId.get();
                account.addMovie(movie, state);
                accountRepository.save(account);
            }
        }
    }

    public List<MovieDto> findWatchlist(Integer id, String state) {
        Account byId = accountRepository.findById(id).orElse(null);
        if (byId != null) {
            Map<Movie, String> movieMap = byId.getMovieMap();
            List<Movie> movies = movieMap.keySet()
                    .stream()
                    .filter(movie -> movieMap.get(movie).equals(state))
                    .toList();
            return movieMapper.toDtoList(movies);
        }
        return new ArrayList<>();
    }

    public void deleteMovieFromList(Integer movieId, String name) {
        Account account = accountRepository.findByUsername(name).orElse(null);
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (account != null) {
            account.getMovieMap().remove(movie);
            accountRepository.save(account);
        }
    }
}
