package ee.taltech.iti0302.webproject.service;

import ee.taltech.iti0302.webproject.api.service.MovieExternalService;
import ee.taltech.iti0302.webproject.dto.MovieDto;
import ee.taltech.iti0302.webproject.entities.Movie;
import ee.taltech.iti0302.webproject.mapper.MovieMapper;
import ee.taltech.iti0302.webproject.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieService {
    private final MovieExternalService movieExternalService;
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public List<MovieDto> findAll() {
        return movieMapper.toDtoList(movieRepository.findAll());
    }

    public MovieDto findById(int id) {
        return movieMapper.toDto(movieRepository.findById(id).orElse(null));
    }

    public List<MovieDto> findByYear(int year) {
        return movieMapper.toDtoList(movieRepository.findAllByReleaseDateYear(year));
    }

    public List<MovieDto> findByMultipleYears(List<Integer> years) {
        return movieMapper.toDtoList(movieRepository.findAllByReleaseDateYearIn(years));
    }

    public List<MovieDto> findByName(String movieName) {
        List<Movie> moviesWithGivenName = movieRepository.findByTitleContainingIgnoreCase(movieName);
        if (moviesWithGivenName.isEmpty()) {
            moviesWithGivenName = movieExternalService.saveMoviesByName(movieName);
        }
        return movieMapper.toDtoList(moviesWithGivenName);
    }

    public List<MovieDto> findByMultipleYearsAndGenres(List<Integer> years, List<Integer> genres) {
        return movieMapper.toDtoList(movieRepository.findAllByGenresInAndReleaseDateYearIn(genres, years));
    }
}
