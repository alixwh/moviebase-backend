package ee.taltech.iti0302.webproject.controller;

import ee.taltech.iti0302.webproject.dto.GenreDto;
import ee.taltech.iti0302.webproject.service.GenreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("genres/{id}")
    public GenreDto getGenreById(@PathVariable("id") int id) {
        return genreService.findById(id);
    }

    @GetMapping("genres")
    public List<GenreDto> getGenres() {
        return genreService.findAll();
    }
}
