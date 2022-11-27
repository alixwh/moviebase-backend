package ee.taltech.iti0302.webproject.controller;

import ee.taltech.iti0302.webproject.dto.MovieDto;
import ee.taltech.iti0302.webproject.service.ActorService;
import ee.taltech.iti0302.webproject.service.GenreService;
import ee.taltech.iti0302.webproject.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api/public")
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

    @GetMapping("movies/years/{years}")
    public List<MovieDto> getMoviesMultipleYears(@PathVariable("years") List<Integer> years) {
        return movieService.findByMultipleYears(years);
    }

    @GetMapping("movies/actor/{id}")
    public List<MovieDto> getMoviesByActor(@PathVariable("id") int actorId) {
        return actorService.findMoviesByActorId(actorId);
    }

    @GetMapping("genre/{id}")
    public List<MovieDto> getMoviesByCategory(@PathVariable("id") int genreId) {
        return genreService.findMoviesByGenreId(genreId);
    }

    @GetMapping("search")
    public List<MovieDto> getMoviesByName(@RequestParam("query") String movieName) {
        return movieService.findByName(movieName);
    }
    @GetMapping("movies/genres/{genreIds}")
    public List<MovieDto> getMoviesMultipleGenres(@PathVariable("genreIds") List<Integer> genres) {
        return movieService.findByMultipleGenres(genreService.findGenresByMultipleGenreIds(genres));
    }

    @GetMapping("filter")
    public List<MovieDto> getMoviesByYearsAndGenres(@RequestParam(value="genre", required=false) List<Integer> genres, @RequestParam(value="year", required = false) List<Integer> years) {
        return movieService.findByMultipleYearsAndGenres(years, genres);
    }
}
