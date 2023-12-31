package ee.taltech.iti0302.webproject.service;

import ee.taltech.iti0302.webproject.dto.MovieDto;
import ee.taltech.iti0302.webproject.entities.Genre;
import ee.taltech.iti0302.webproject.entities.Movie;
import ee.taltech.iti0302.webproject.mapper.MovieMapper;
import ee.taltech.iti0302.webproject.mapper.MovieMapperImpl;
import ee.taltech.iti0302.webproject.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    //private final MovieExternalService movieExternalService;
    @Mock
    private MovieRepository movieRepository;
    @Spy
    private MovieMapper movieMapper = new MovieMapperImpl();
    @InjectMocks
    private MovieService movieService;

    public static final Genre war = Genre.builder().name("war").id(1).build();
    public static final Movie movie = Movie.builder().id(1).title("Wakanda")
            .overview("As the Wakandans strive to embrace their next chapter...")
            .releaseDate(LocalDate.now()).voteAverage(8.1).posterPath("/ps2oKfhY6DL3alynlSqY97gHSsg.jpg")
            .genres(Set.of(war)).build();
    public static final MovieDto movieDto = MovieDto.builder().id(1).title("Wakanda")
            .overview("As the Wakandans strive to embrace their next chapter...")
            .releaseDate(LocalDate.now()).voteAverage(8.1).posterPath("/ps2oKfhY6DL3alynlSqY97gHSsg.jpg")
            .genresList(Set.of("war")).build();
    @Test
    void findAll_ShouldReturnPageOfMovies() {
        Sort sort = Sort.by("voteAverage").descending();
        given(movieRepository.findAll(PageRequest.of(1, 20, sort))).willReturn(new PageImpl<>(List.of(movie)));

        var result= movieService.findAll(1, "voteAverage", false);

        then(movieMapper).should().toDtoList(List.of(movie));
        then(movieRepository).should().findAll(PageRequest.of(1, 20, sort));
        assertEquals(new PageImpl<>(List.of(movieDto),PageRequest.of(1, 20, sort), 1), result);
    }

    @Test
    void findById() {
        given(movieRepository.findById(1)).willReturn(Optional.of(movie));

        var result= movieService.findById(1);

        then(movieMapper).should().toDto(movie);
        then(movieRepository).should().findById(1);
        assertEquals(movieDto, result);
    }

    @Test
    void findByName() {
        given(movieRepository.findByTitleContainingIgnoreCase("wakanda")).willReturn(List.of(movie));

        var result= movieService.findByName("wakanda");

        then(movieMapper).should().toDto(movie);
        then(movieRepository).should().findByTitleContainingIgnoreCase("wakanda");
        assertEquals(List.of(movieDto), result);
    }

    @Test
    void findByYear() {
        given(movieRepository.findAllByReleaseDate(LocalDate.now().getYear())).willReturn(List.of(movie));

        var result= movieService.findByYear(LocalDate.now().getYear());

        then(movieMapper).should().toDtoList(List.of(movie));
        then(movieRepository).should().findAllByReleaseDate(LocalDate.now().getYear());
        assertEquals(List.of(movieDto), result);
    }

    @Test
    void findByMultipleYears() {
        Sort sort = Sort.by("voteAverage").descending();
        given(movieRepository.findAllByReleaseDateIn(List.of(2022), PageRequest.of(1, 20, sort)))
                .willReturn(new PageImpl<>(List.of(movie)));

        var result= movieService.findByMultipleYears(List.of(2022), 1, "voteAverage", false);

        then(movieMapper).should().toDtoList(List.of(movie));
        then(movieRepository).should().findAllByReleaseDateIn(List.of(2022), PageRequest.of(1, 20, sort));
        assertEquals(new PageImpl<>(List.of(movieDto),PageRequest.of(1, 20, sort), 1), result);
    }

    @Test
    void findByMultipleGenres() {
        Sort sort = Sort.by("voteAverage").descending();
        given(movieRepository.findAllByGenresIn(List.of(1), PageRequest.of(1, 20, sort)))
                .willReturn(new PageImpl<>(List.of(movie)));

        var result= movieService.findByMultipleGenres(List.of(1), 1, "voteAverage", false);

        then(movieMapper).should().toDtoList(List.of(movie));
        then(movieRepository).should().findAllByGenresIn(List.of(1), PageRequest.of(1, 20, sort));
        assertEquals(new PageImpl<>(List.of(movieDto),PageRequest.of(1, 20, sort), 1), result);
    }

    @Test
    void findByMultipleYearsAndGenres() {
        Sort sort = Sort.by("voteAverage").ascending();
        given(movieRepository.findAllByGenresInAndReleaseDateIn(List.of(1), List.of(2022), PageRequest.of(1, 20, sort)))
                .willReturn(new PageImpl<>(List.of(movie)));

        var result= movieService.findByMultipleYearsAndGenres(List.of(1), List.of(2022), 1, "voteAverage", true);

        then(movieMapper).should().toDtoList(List.of(movie));
        then(movieRepository).should().findAllByGenresInAndReleaseDateIn(List.of(1), List.of(2022), PageRequest.of(1, 20, sort));
        assertEquals(new PageImpl<>(List.of(movieDto),PageRequest.of(1, 20, sort), 1), result);
    }
    @Test
    void findByMultipleYearsAndGenresNoYearsAndNoGenres() {
        Sort sort = Sort.by("voteAverage").ascending();
        given(movieRepository.findAll(PageRequest.of(1, 20, sort)))
                .willReturn(new PageImpl<>(List.of(movie)));

        var result= movieService.findByMultipleYearsAndGenres(null, null, 1, "voteAverage", true);

        then(movieMapper).should().toDtoList(List.of(movie));
        then(movieRepository).should().findAll(PageRequest.of(1, 20, sort));
        assertEquals(new PageImpl<>(List.of(movieDto),PageRequest.of(1, 20, sort), 1), result);
    }
    @Test
    void findByMultipleYearsAndGenresOnlyYears() {
        Sort sort = Sort.by("voteAverage").ascending();
        given(movieRepository.findAllByReleaseDateIn(List.of(2022), PageRequest.of(1, 20, sort)))
                .willReturn(new PageImpl<>(List.of(movie)));

        var result= movieService.findByMultipleYearsAndGenres(null, List.of(2022), 1, "voteAverage", true);

        then(movieMapper).should().toDtoList(List.of(movie));
        then(movieRepository).should().findAllByReleaseDateIn(List.of(2022), PageRequest.of(1, 20, sort));
        assertEquals(new PageImpl<>(List.of(movieDto),PageRequest.of(1, 20, sort), 1), result);
    }
    @Test
    void findByMultipleYearsAndGenresOnlyGenres() {
        Sort sort = Sort.by("voteAverage").ascending();
        given(movieRepository.findAllByGenresIn(List.of(1), PageRequest.of(1, 20, sort)))
                .willReturn(new PageImpl<>(List.of(movie)));

        var result= movieService.findByMultipleYearsAndGenres(List.of(1), null, 1, "voteAverage", true);

        then(movieMapper).should().toDtoList(List.of(movie));
        then(movieRepository).should().findAllByGenresIn(List.of(1), PageRequest.of(1, 20, sort));
        assertEquals(new PageImpl<>(List.of(movieDto),PageRequest.of(1, 20, sort), 1), result);
    }

}