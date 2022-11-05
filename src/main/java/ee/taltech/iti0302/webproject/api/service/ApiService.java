package ee.taltech.iti0302.webproject.api.service;

import ee.taltech.iti0302.webproject.api.externaldto.ActorExternalDto;
import ee.taltech.iti0302.webproject.api.externaldto.DirectorExternalDto;
import ee.taltech.iti0302.webproject.api.externaldto.GenreExternalDto;
import ee.taltech.iti0302.webproject.api.externaldto.MovieExternalDto;
import ee.taltech.iti0302.webproject.api.externallistdto.CreditsListDto;
import ee.taltech.iti0302.webproject.api.externallistdto.GenreListExternalDto;
import ee.taltech.iti0302.webproject.api.externallistdto.MovieListExternalDto;
import ee.taltech.iti0302.webproject.api.mappper.ActorExternalMapper;
import ee.taltech.iti0302.webproject.api.mappper.DirectorExternalMapper;
import ee.taltech.iti0302.webproject.api.mappper.GenreExternalMapper;
import ee.taltech.iti0302.webproject.api.mappper.MovieExternalMapper;
import ee.taltech.iti0302.webproject.entities.Actor;
import ee.taltech.iti0302.webproject.entities.Director;
import ee.taltech.iti0302.webproject.entities.Genre;
import ee.taltech.iti0302.webproject.entities.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ApiService {
    private static final String API_KEY = "ee997f75fb7f7e80dc5adc5aabac24ff";
    private final MovieExternalService movieExternalService;
    private final GenreExternalService genreExternalService;
    private final ActorExternalService actorExternalService;
    private final DirectorExternalService directorExternalService;
    private final MovieExternalMapper movieExternalMapper;
    private final GenreExternalMapper genreExternalMapper;
    private final ActorExternalMapper actorExternalMapper;
    private final DirectorExternalMapper directorExternalMapper;


    public void saveGenres() {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = String.format("https://api.themoviedb.org/3/genre/movie/list?api_key=%s&language=en-US", API_KEY);
        GenreListExternalDto response = restTemplate.getForObject(resourceUrl, GenreListExternalDto.class);
        for (GenreExternalDto genreExternalDto: Objects.requireNonNull(response).getGenres()) {
            Genre genre = genreExternalMapper.genreExternalDtoToGenre(genreExternalDto);
            genreExternalService.save(genre);
        }
    }

    public void saveMovies(int limit) {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 1; i <= limit && i < 500; i++) {
            String resourceUrl = String.format("https://api.themoviedb.org/3/movie/popular?api_key=%1$s&language=en-US&page=%2$s", API_KEY, i);
            MovieListExternalDto response = restTemplate.getForObject(resourceUrl, MovieListExternalDto.class);
            for (MovieExternalDto movieExternalDto: Objects.requireNonNull(response).getResults()) {
                Set<Genre> genres = new HashSet<>();
                for(Integer genreId: movieExternalDto.getGenreIds()) {
                    genres.add(genreExternalService.findById(genreId));
                }
                Movie movie = movieExternalMapper.movieExternalDtoToMovie(movieExternalDto);
                movie.setGenres(genres);
                Integer movieId = movieExternalDto.getId();
                if (!movieExternalService.isInDatabase(movieId)) {
                    saveCredits(movieId, movie);
                    movieExternalService.save(movie);
                }
            }
        }
    }

    public void saveCredits(Integer movieId, Movie movie) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = String.format("https://api.themoviedb.org/3/movie/%1$s/credits?api_key=%2$s&language=en-US", movieId, API_KEY);
        CreditsListDto response = restTemplate.getForObject(resourceUrl, CreditsListDto.class);
        int i = 0;
        Set<Actor> actors = new HashSet<>();
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
        Set<Director> directors = new HashSet<>();
        for (DirectorExternalDto crewMemberDto: response.getCrew()) {
            if (crewMemberDto.getJob().equals("Director")) {
                Director director = directorExternalMapper.directorExternalDtoToDirector(crewMemberDto);
                directorExternalService.save(director);
                directors.add(director);
            }
        }
        movie.setDirectors(directors);
    }

    public void saveMovie(String movieName) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = String.format("https://api.themoviedb.org/3/search/movie?api_key=%1$s&query=%2$s", API_KEY, movieName);
        MovieListExternalDto response = restTemplate.getForObject(resourceUrl, MovieListExternalDto.class);
        for (MovieExternalDto movieExternalDto: Objects.requireNonNull(response).getResults()) {
            Set<Genre> genres = new HashSet<>();
            for(Integer genreId: movieExternalDto.getGenreIds()) {
                genres.add(genreExternalService.findById(genreId));
            }
            Movie movie = movieExternalMapper.movieExternalDtoToMovie(movieExternalDto);
            movie.setGenres(genres);
            Integer movieId = movieExternalDto.getId();
            if (!movieExternalService.isInDatabase(movieId)) {
                saveCredits(movieId, movie);
                movieExternalService.save(movie);
            }
        }
    }
}
