package ee.taltech.iti0302.webproject.service;

import ee.taltech.iti0302.webproject.dto.GenreDto;
import ee.taltech.iti0302.webproject.dto.MovieDto;
import ee.taltech.iti0302.webproject.entities.Genre;
import ee.taltech.iti0302.webproject.mapper.GenreMapper;
import ee.taltech.iti0302.webproject.mapper.MovieMapper;
import ee.taltech.iti0302.webproject.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;
    private final MovieMapper movieMapper;

    public GenreDto findById(int id) {
        return genreMapper.toDto(genreRepository.findById(id).orElse(null));
    }

    public List<GenreDto> findAll() {
        return genreMapper.toDtoList(genreRepository.findAll());
    }

    public List<MovieDto> findMoviesByGenreId(int id) {
        Genre genre = genreRepository.findById(id).orElse(null);
        if (genre != null) {
            return movieMapper.toDtoList(genre.getMovies());
        }
        return new ArrayList<>();
    }

    public Set<MovieDto> findMoviesByMultipleGenreIds(int[] genres) {
        Set<MovieDto> moviesByCategory = new HashSet<>();
        List<Genre> byIdIn = genreRepository.findByIdIn(genres);
        for (Genre genre: byIdIn) {
            if (genre != null) {
                moviesByCategory.addAll(movieMapper.toDtoList(genre.getMovies()));
            }
        }
        return moviesByCategory;
    }
}
