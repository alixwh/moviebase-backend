package ee.taltech.iti0302.webproject.service;

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
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public List<MovieDto> findAll() {
        return movieMapper.toDtoList(movieRepository.findAll());
    }

    public MovieDto findById(int id) {
        return movieMapper.toDto(movieRepository.findById(id).orElse(null));
    }

    public Movie save(Movie movie) {
        if (movieRepository.existsById(movie.getId())) {
            return null;
        }
        return movieRepository.save(movie);
    }
}
