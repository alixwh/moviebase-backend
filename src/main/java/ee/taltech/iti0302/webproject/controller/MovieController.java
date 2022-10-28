package ee.taltech.iti0302.webproject.controller;

import ee.taltech.iti0302.webproject.dto.MovieDto;
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

    @GetMapping("movies/{id}")
    public MovieDto getMovieById(@PathVariable("id") int id) {
        return movieService.findById(id);
    }

    @GetMapping("movies")
    public List<MovieDto> getMovies() {
        return movieService.findAll();
    }
}
