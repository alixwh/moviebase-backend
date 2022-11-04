package ee.taltech.iti0302.webproject.api.service;

import ee.taltech.iti0302.webproject.api.externaldto.GenreExternalDto;
import ee.taltech.iti0302.webproject.api.externaldto.MovieExternalDto;
import ee.taltech.iti0302.webproject.api.externallistdto.GenreListExternalDto;
import ee.taltech.iti0302.webproject.api.externallistdto.MovieListExternalDto;
import ee.taltech.iti0302.webproject.api.mappper.GenreExternalMapper;
import ee.taltech.iti0302.webproject.api.mappper.MovieExternalMapper;
import ee.taltech.iti0302.webproject.entities.Genre;
import ee.taltech.iti0302.webproject.entities.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ApiService {
    private static final String API_KEY = "ee997f75fb7f7e80dc5adc5aabac24ff";
    private final MovieExternalService movieExternalService;
    private final GenreExternalService genreExternalService;
    private final MovieExternalMapper movieExternalMapper;
    private final GenreExternalMapper genreExternalMapper;

    public void saveGenres() {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = String.format("https://api.themoviedb.org/3/genre/movie/list?api_key=%s&language=en-US", API_KEY);
        GenreListExternalDto response = restTemplate.getForObject(resourceUrl, GenreListExternalDto.class);
        for (GenreExternalDto genreExternalDto: response.getGenres()) {
            Genre genre = genreExternalMapper.genreExternalDtoToGenre(genreExternalDto);
            genreExternalService.save(genre);
        }
    }

    public void saveMovies(int limit) {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 1; i <= limit && i < 500; i++) {
            String resourceUrl = String.format("https://api.themoviedb.org/3/movie/popular?api_key=%1$s&language=en-US&page=%2$s", API_KEY, i);
            MovieListExternalDto response = restTemplate.getForObject(resourceUrl, MovieListExternalDto.class);
            for (MovieExternalDto movieExternalDto: response.getResults()) {
                Set<Genre> genres = new HashSet<>();
                for(Integer genreId: movieExternalDto.getGenreIds()) {
                    genres.add(genreExternalService.findById(genreId));
                }
                Movie movie = movieExternalMapper.movieExternalDtoToMovie(movieExternalDto);
                movie.setGenres(genres);
                Integer movieId = movieExternalDto.getId();
                if (!movieExternalService.isInDatabase(movieId)) {
                    //saveCredits(movieId, movieExternalDto);
                    movieExternalService.save(movie);
                }
            }
        }
    }

}
