package ee.taltech.iti0302.webproject.service;

import ee.taltech.iti0302.webproject.api.service.MovieExternalService;
import ee.taltech.iti0302.webproject.dto.MovieDto;
import ee.taltech.iti0302.webproject.entities.Movie;
import ee.taltech.iti0302.webproject.mapper.MovieMapper;
import ee.taltech.iti0302.webproject.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieService {
    private final MovieExternalService movieExternalService;
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private static final int PAGE_SIZE = 20;


    public List<MovieDto> findAll() {
        return movieMapper.toDtoList(movieRepository.findAll());
    }

    private Page<MovieDto> getMovieDtoPage(Pageable pageable, Page<Movie> movieList) {
        List<MovieDto> movieDtos = movieMapper.toDtoList(movieList.getContent());
        return new PageImpl<>(movieDtos, pageable, movieList.getTotalElements());
    }

    private Pageable getPageable(int page, String orderBy, boolean ascending) {
        Sort sort;
        if (ascending) {
            sort = Sort.by(orderBy).ascending();
        } else {
            sort = Sort.by(orderBy).descending();
        }
        return PageRequest.of(page, PAGE_SIZE, sort);
    }

    public MovieDto findById(int id) {
        return movieMapper.toDto(movieRepository.findById(id).orElse(null));
    }

    public List<MovieDto> findByName(String movieName) {
        List<Movie> moviesWithGivenName = movieRepository.findByTitleContainingIgnoreCase(movieName);
        if (moviesWithGivenName.isEmpty()) {
            moviesWithGivenName = movieExternalService.saveMoviesByName(movieName);
        }
        return movieMapper.toDtoList(moviesWithGivenName);
    }

    public List<MovieDto> findByYear(int year) {
        return movieMapper.toDtoList(movieRepository.findAllByReleaseDate(year));
    }

    public Page<MovieDto> findByMultipleYears(List<Integer> years,  int page, String orderBy, boolean ascending) {
        Pageable pageable = getPageable(page, orderBy, ascending);
        Page<Movie> movieList = movieRepository.findAllByReleaseDateIn(years, pageable);
        return getMovieDtoPage(pageable, movieList);
    }

    public Page<MovieDto> findByMultipleGenres(List<Integer> genres, int page, String orderBy, boolean ascending) {
        Pageable pageable = getPageable(page, orderBy, ascending);
        Page<Movie> movieList = movieRepository.findAllByGenresIn(genres, pageable);
        return getMovieDtoPage(pageable, movieList);
    }

    public Page<MovieDto> findByMultipleYearsAndGenres(List<Integer> genres, List<Integer> years, int page, String orderBy, boolean ascending ) {
        if (genres == null && years == null) {
            Pageable pageable = getPageable(page, orderBy, ascending);
            Page<Movie> all = movieRepository.findAll(pageable);
            return getMovieDtoPage(pageable, all);
        } else if (genres == null) {
            return findByMultipleYears(years, page, orderBy, ascending);
        } else if (years == null) {
            return findByMultipleGenres(genres, page, orderBy, ascending);
        } else {

            Pageable pageable = getPageable(page, orderBy, ascending);
            Page<Movie> movieList = movieRepository.findAllByGenresInAndReleaseDateIn(genres, years, pageable);
            return getMovieDtoPage(pageable, movieList);
        }
    }
}
