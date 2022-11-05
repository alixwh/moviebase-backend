package ee.taltech.iti0302.webproject.api.service;

import ee.taltech.iti0302.webproject.entities.Movie;
import ee.taltech.iti0302.webproject.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MovieExternalService {
    private final MovieRepository movieRepository;

    public boolean isInDatabase(Integer movieId) {
        return movieRepository.existsById(movieId);
    }

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }
}
