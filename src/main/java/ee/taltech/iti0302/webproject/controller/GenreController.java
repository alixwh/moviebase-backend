package ee.taltech.iti0302.webproject.controller;

import ee.taltech.iti0302.webproject.dto.GenreDto;
import ee.taltech.iti0302.webproject.dto.MovieDto;
import ee.taltech.iti0302.webproject.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api")
@RestController
public class GenreController {
    private final GenreService genreService;

    @GetMapping("genres/{id}")
    public GenreDto getGenreById(@PathVariable("id") int id) {
        return genreService.findById(id);
    }

    @GetMapping("genres")
    public List<GenreDto> getGenres() {
        return genreService.findAll();
    }
}
