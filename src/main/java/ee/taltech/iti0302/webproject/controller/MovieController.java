package ee.taltech.iti0302.webproject.controller;

import ee.taltech.iti0302.webproject.dto.MovieDto;
import ee.taltech.iti0302.webproject.service.ActorService;
import ee.taltech.iti0302.webproject.service.GenreService;
import ee.taltech.iti0302.webproject.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api")
@RestController
public class MovieController {

    private final MovieService movieService;
    private final ActorService actorService;
    private final GenreService genreService;

    @GetMapping("movies/{id}")
    public MovieDto getMovieById(@PathVariable("id") int id) {
        return movieService.findById(id);
    }

    @GetMapping("movies")
    public List<MovieDto> getMovies() {
        return movieService.findAll();
    }

    @GetMapping("movies/year/{releaseYear}")
    public List<MovieDto> getMoviesByReleaseYear(@PathVariable("releaseYear") int year) {
        return movieService.findByYear(year);
    }

    @GetMapping("actor/{id}")
    public List<MovieDto> getMoviesByActor(@PathVariable("id") int actorId) {
        return actorService.findMoviesByActorId(actorId);
    }

    @GetMapping("genre/{id}")
    public List<MovieDto> getMoviesByCategory(@PathVariable("id") int genreId) {
        return genreService.findMoviesByGenreId(genreId);
    }
}
