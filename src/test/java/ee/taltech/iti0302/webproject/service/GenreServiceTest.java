package ee.taltech.iti0302.webproject.service;

import ee.taltech.iti0302.webproject.dto.GenreDto;
import ee.taltech.iti0302.webproject.dto.MovieDto;
import ee.taltech.iti0302.webproject.entities.Genre;
import ee.taltech.iti0302.webproject.entities.Movie;
import ee.taltech.iti0302.webproject.mapper.GenreMapper;
import ee.taltech.iti0302.webproject.mapper.GenreMapperImpl;
import ee.taltech.iti0302.webproject.mapper.MovieMapper;
import ee.taltech.iti0302.webproject.mapper.MovieMapperImpl;
import ee.taltech.iti0302.webproject.repository.GenreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class GenreServiceTest {
    @Mock
    private GenreRepository genreRepository;
    @Spy
    private GenreMapper genreMapper = new GenreMapperImpl();
    @Spy
    private MovieMapper movieMapper = new MovieMapperImpl();
    @InjectMocks
    private GenreService genreService;

    public static final Movie movie = Movie.builder().id(1).title("Wakanda")
            .overview("As the Wakandans strive to embrace their next chapter...")
            .releaseDate(LocalDate.now()).voteAverage(8.1).posterPath("/ps2oKfhY6DL3alynlSqY97gHSsg.jpg").build();
    public static final MovieDto movieDto = MovieDto.builder().id(1).title("Wakanda")
            .overview("As the Wakandans strive to embrace their next chapter...")
            .releaseDate(LocalDate.now()).voteAverage(8.1).posterPath("/ps2oKfhY6DL3alynlSqY97gHSsg.jpg").build();
    public static final Genre genre = Genre.builder().id(1).name("action").movies(List.of(movie)).build();
    public static final GenreDto genreDto = GenreDto.builder().id(1).name("action").moviesList(Set.of("Wakanda")).build();


    @Test
    void findById() {
        given(genreRepository.findById(1)).willReturn(Optional.of(genre));

        var result= genreService.findById(1);

        then(genreMapper).should().toDto(genre);
        then(genreRepository).should().findById(1);
        assertEquals(genreDto, result);
    }

    @Test
    void findAll() {
        given(genreRepository.findAll()).willReturn(List.of(genre));

        var result= genreService.findAll();

        then(genreMapper).should().toDto(genre);
        then(genreRepository).should().findAll();
        assertEquals(List.of(genreDto), result);
    }

    @Test
    void findMoviesByGenreId() {
        given(genreRepository.findById(1)).willReturn(Optional.of(genre));

        var result= genreService.findMoviesByGenreId(1);

        then(genreRepository).should().findById(1);
        then(movieMapper).should().toDtoList(List.of(movie));
        assertEquals(List.of(movieDto), result);
    }

    @Test
    void findMoviesByGenreId_NoSuchGenre_ShouldReturnEmptyList() {
        given(genreRepository.findById(2)).willReturn(Optional.empty());

        var result= genreService.findMoviesByGenreId(2);

        then(genreRepository).should().findById(2);
        assertEquals(new ArrayList<>(), result);
    }
}