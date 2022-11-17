package ee.taltech.iti0302.webproject.api.service;

import ee.taltech.iti0302.webproject.api.externaldto.ActorExternalDto;
import ee.taltech.iti0302.webproject.api.externaldto.DirectorExternalDto;
import ee.taltech.iti0302.webproject.api.externaldto.MovieExternalDto;
import ee.taltech.iti0302.webproject.api.externallistdto.CreditsListDto;
import ee.taltech.iti0302.webproject.api.externallistdto.MovieListExternalDto;
import ee.taltech.iti0302.webproject.api.mappper.ActorExternalMapper;
import ee.taltech.iti0302.webproject.api.mappper.DirectorExternalMapper;
import ee.taltech.iti0302.webproject.api.mappper.MovieExternalMapper;
import ee.taltech.iti0302.webproject.entities.Actor;
import ee.taltech.iti0302.webproject.entities.Director;
import ee.taltech.iti0302.webproject.entities.Genre;
import ee.taltech.iti0302.webproject.entities.Movie;
import ee.taltech.iti0302.webproject.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RequiredArgsConstructor
@Service
public class MovieExternalService {
    private static final String API_KEY = "ee997f75fb7f7e80dc5adc5aabac24ff";
    private final MovieRepository movieRepository;
    private final MovieExternalMapper movieExternalMapper;
    private final ActorExternalMapper actorExternalMapper;
    private final DirectorExternalMapper directorExternalMapper;
    private final GenreExternalService genreExternalService;
    private final ActorExternalService actorExternalService;
    private final DirectorExternalService directorExternalService;

    public boolean isInDatabase(Integer movieId) {
        return movieRepository.existsById(movieId);
    }

    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    public List<Movie> saveMoviesByName(String movieName) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = String.format("https://api.themoviedb.org/3/search/movie?api_key=%1$s&query=%2$s", API_KEY, movieName);
        MovieListExternalDto response = restTemplate.getForObject(resourceUrl, MovieListExternalDto.class);
        return saveMovies(response);
    }

    public void savePopularMovies(int limit) {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 1; i <= limit && i < 500; i++) {
            String resourceUrl = String.format("https://api.themoviedb.org/3/movie/popular?api_key=%1$s&language=en-US&page=%2$s", API_KEY, i);
            MovieListExternalDto response = restTemplate.getForObject(resourceUrl, MovieListExternalDto.class);
            saveMovies(response);
        }
    }

    public List<Movie> saveMovies(MovieListExternalDto response) {
        List<Movie> savedMovies = new ArrayList<>();
        for (MovieExternalDto movieExternalDto: Objects.requireNonNull(response).getResults()) {
            Set<Genre> genres = genreExternalService.getGenresByIds(movieExternalDto.getGenreIds());
            Movie movie = movieExternalMapper.movieExternalDtoToMovie(movieExternalDto);
            String posterPath = movieExternalDto.getPosterPath();
            movie.setPosterPath(posterPath);
            movie.setGenres(genres);
            Integer movieId = movieExternalDto.getId();
            if (!isInDatabase(movieId)) {
                saveCredits(movieId, movie);
                save(movie);
                savedMovies.add(movie);
            }
        }
        return savedMovies;
    }

    public void saveCredits(Integer movieId, Movie movie) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = String.format("https://api.themoviedb.org/3/movie/%1$s/credits?api_key=%2$s&language=en-US", movieId, API_KEY);
        CreditsListDto response = restTemplate.getForObject(resourceUrl, CreditsListDto.class);
        System.out.println(movieId);
        saveActors(response, movie);
        saveDirectors(response, movie);
    }

    public void saveActors(CreditsListDto response, Movie movie) {
        int i = 0;
        Set<Actor> actors = new HashSet<>();
        System.out.println(response.getCast());
        for (ActorExternalDto actorExternalDto: Objects.requireNonNull(response).getCast()) {
            if(i > 20) {
                break;
            }
            Actor actor = actorExternalMapper.actorExternalDtoToActor(actorExternalDto);
            actorExternalService.save(actor);
            actors.add(actor);
            i++;
        }
        movie.setActors(actors);
    }

    public void saveDirectors(CreditsListDto response, Movie movie) {
        Set<Director> directors = new HashSet<>();
        for (DirectorExternalDto crewMemberDto: Objects.requireNonNull(response).getCrew()) {
            if (crewMemberDto.getJob().equals("Director")) {
                Director director = directorExternalMapper.directorExternalDtoToDirector(crewMemberDto);
                directorExternalService.save(director);
                directors.add(director);
            }
        }
        movie.setDirectors(directors);
    }
}
