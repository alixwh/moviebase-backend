package ee.taltech.iti0302.webproject.api;

import ee.taltech.iti0302.webproject.api.externallistdto.CreditsList;
import ee.taltech.iti0302.webproject.api.externallistdto.GenreList;
import ee.taltech.iti0302.webproject.api.externallistdto.MovieListExternalDto;
import ee.taltech.iti0302.webproject.entities.Actor;
import ee.taltech.iti0302.webproject.entities.Director;
import ee.taltech.iti0302.webproject.entities.Genre;
import ee.taltech.iti0302.webproject.entities.Movie;
import ee.taltech.iti0302.webproject.api.externaldto.MovieExternalDto;
import ee.taltech.iti0302.webproject.service.ActorService;
import ee.taltech.iti0302.webproject.service.DirectorService;
import ee.taltech.iti0302.webproject.service.GenreService;
import ee.taltech.iti0302.webproject.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;


@RequiredArgsConstructor
@RequestMapping("api")
@RestController
public class ApiController {
    private static final String API_KEY = "ee997f75fb7f7e80dc5adc5aabac24ff";
    private final MovieService movieService;
    private final GenreService genreService;
    private final ActorService actorService;
    private final DirectorService directorService;

    @PostMapping("save/genres")
    public void saveGenres() {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = String.format("https://api.themoviedb.org/3/genre/movie/list?api_key=%s&language=en-US", API_KEY);
        GenreList response = restTemplate.getForObject(resourceUrl, GenreList.class);
        for (Genre genre: response.getGenres()) {
            genreService.save(genre);
        }
    }

    @PostMapping("save/movies/{limit}")
    public void saveMovies(@PathVariable("limit") Integer limit) {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 1; i <= limit && i < 500; i++) {
            String resourceUrl = String.format("https://api.themoviedb.org/3/movie/popular?api_key=%1$s&language=en-US&page=%2$s", API_KEY, i);
            MovieListExternalDto response = restTemplate.getForObject(resourceUrl, MovieListExternalDto.class);
            for (MovieExternalDto movie: response.getResults()) {
                Set<Genre> genres = new HashSet<>();
                for(Integer genre_id: movie.getGenreIds()) {
                    genres.add(genreService.findGenreById(genre_id));
                }
                // TODO mapper externalDTO to entity
                // todo save entity
                movie.setGenres(genres);
                Integer movieId = movie.getId();
                if (!movieService.isMovieInDatabase(movieId)) {
                    saveCredits(movieId, movie);
                    movieService.save(movie);
                }
            }
        }
    }

    public void saveCredits(Integer movieId, Movie movie) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = String.format("https://api.themoviedb.org/3/movie/%1$s/credits?api_key=%2$s&language=en-US", movieId, API_KEY);
        CreditsList response = restTemplate.getForObject(resourceUrl, CreditsList.class);
        int i = 0;
        Set<Actor> actors = new HashSet<>();
        for (Actor actor: response.getCast()) {
            if(i > 20) {
                break;
            }
            actorService.save(actor);
            actors.add(actor);
            i++;
        }
        movie.setActors(actors);
        Set<Director> directors = new HashSet<>();
        for (Director crewMember: response.getCrew()) {
            if (crewMember.getJob().equals("Director")) {
                directorService.save(crewMember);
                directors.add(crewMember);
            }
        }
        movie.setDirectors(directors);
    }
}
