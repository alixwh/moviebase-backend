package ee.taltech.iti0302.webproject.service;

import ee.taltech.iti0302.webproject.api.service.MovieExternalService;
import ee.taltech.iti0302.webproject.dto.MovieDto;
import ee.taltech.iti0302.webproject.entities.Genre;
import ee.taltech.iti0302.webproject.entities.Movie;
import ee.taltech.iti0302.webproject.mapper.MovieMapper;
import ee.taltech.iti0302.webproject.repository.GenreRepository;
import ee.taltech.iti0302.webproject.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

@RequiredArgsConstructor
@Service
public class MovieService {
    private final MovieExternalService movieExternalService;
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final MovieMapper movieMapper;

    public List<MovieDto> findAll() {
        return movieMapper.toDtoList(movieRepository.findAll());
    }

    public MovieDto findById(int id) {
        return movieMapper.toDto(movieRepository.findById(id).orElse(null));
    }

    public List<MovieDto> findByYear(int year) {
        LocalDate instance = LocalDate.now().withYear(year);
        LocalDate startDate = instance.with(firstDayOfYear());
        LocalDate endDate = instance.with(lastDayOfYear());
        return movieMapper.toDtoList(movieRepository.findByReleaseDateBetween(startDate, endDate));
    }

    public List<MovieDto> findByMultipleYears(int[] years) {
        List<MovieDto> moviesByYears = new ArrayList<>();
        for (int year: years) {
            moviesByYears.addAll(findByYear(year));
        }
        return moviesByYears;
    }

    public List<MovieDto> findByName(String movieName) {
        List<Movie> moviesWithGivenName = movieRepository.findByTitleContainingIgnoreCase(movieName);
        if (moviesWithGivenName.isEmpty()) {
            moviesWithGivenName = movieExternalService.saveMoviesByName(movieName);
        }
        return movieMapper.toDtoList(moviesWithGivenName);
    }

    public Set<MovieDto> findByMultipleYearsAndGenres(List<Integer> years, int[] genres) {
        List<Genre> byIdIn = genreRepository.findByIdIn(genres);
        Set<MovieDto> movies = new HashSet<>();
        for (int year : years) {
            LocalDate instance = LocalDate.now().withYear(year);
            LocalDate startDate = instance.with(firstDayOfYear());
            LocalDate endDate = instance.with(lastDayOfYear());
            movies.addAll(movieMapper.toDtoList(movieRepository.findByGenresInAndReleaseDateBetween(byIdIn, startDate, endDate)));
        }
        return movies;
    }
}
