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

    public Page<MovieDto> findAll(int page, String orderBy, boolean ascending) {
        Pageable pageable = getPageable(page, orderBy, ascending);
        System.out.println(pageable);
        Page<Movie> movieList = movieRepository.findAll(pageable);
        System.out.println(movieList);
        return getMovieDtoPage(pageable, movieList);
    }

    private PageImpl<MovieDto> getMovieDtoPage(Pageable pageable, Page<Movie> movieList) {
        System.out.println(movieList.getContent());
        List<MovieDto> movieDtos = movieMapper.toDtoList(movieList.getContent());
        return new PageImpl<>(movieDtos, pageable, movieList.getTotalElements());
    }

    private static Pageable getPageable(int page, String orderBy, boolean ascending) {
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
        return movieMapper.toDtoList(movieRepository.findAllByReleaseDateYear(year));
    }

//    public Page<MovieDto> findByMultipleYears(List<Integer> years,  int page, String orderBy, boolean ascending) {
//        Pageable pageable = getPageable(page, orderBy, ascending);
//        Page<Movie> movieList = movieRepository.findAllByReleaseDateYearIn(years, pageable);
//        return getMovieDtoPage(pageable, movieList);
//    }
//
//    public Page<MovieDto> findByMultipleGenres(List<Integer> genres, int page, String orderBy, boolean ascending) {
//        Pageable pageable = getPageable(page, orderBy, ascending);
//        Page<Movie> movieList = movieRepository.findAllByGenresIn(genres, pageable);
//        return getMovieDtoPage(pageable, movieList);
//    }

    public Page<MovieDto> findByMultipleYearsAndGenres(List<Integer> genres, List<Integer> years, int page, String orderBy, boolean ascending ) {
        Pageable pageable = getPageable(page, orderBy, ascending);
        Page<Movie> movieList;
        if (genres == null && years == null) {
            System.out.println("all movies");
            movieList = movieRepository.findAll(pageable);
        } else if (genres == null) {
            System.out.println("years");
            movieList = movieRepository.findAllByReleaseDateYearIn(years, pageable);
        } else if (years == null) {
            System.out.println("genres");
            movieList = movieRepository.findAllByGenresIn(genres, pageable);
        } else {
            System.out.println("genres and years");
            movieList = movieRepository.findAllByGenresInAndReleaseDateYearIn(genres, years, pageable);
        }
        System.out.println(movieList);
        return getMovieDtoPage(pageable, movieList);
    }
}
