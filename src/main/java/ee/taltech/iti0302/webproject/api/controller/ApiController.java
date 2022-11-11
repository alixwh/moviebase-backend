package ee.taltech.iti0302.webproject.api.controller;

import ee.taltech.iti0302.webproject.api.service.GenreExternalService;
import ee.taltech.iti0302.webproject.api.service.MovieExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("api")
@RestController
public class ApiController {
    private final GenreExternalService genreExternalService;
    private final MovieExternalService movieExternalService;

    @PostMapping("save/genres")
    public void saveGenres() {
        genreExternalService.saveGenres();
    }

    @PostMapping("save/movies/{limit}")
    public void saveMovies(@PathVariable("limit") Integer limit) {
        movieExternalService.savePopularMovies(limit);
    }

    @PostMapping("save/movie/{movieName}")
    public void saveMovie(@PathVariable("movieName") String movieName) {
        movieExternalService.saveMoviesByName(movieName);
    }
}
