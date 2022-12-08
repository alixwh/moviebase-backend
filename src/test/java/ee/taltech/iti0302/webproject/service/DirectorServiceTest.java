package ee.taltech.iti0302.webproject.service;

import ee.taltech.iti0302.webproject.dto.DirectorDto;
import ee.taltech.iti0302.webproject.dto.GenreDto;
import ee.taltech.iti0302.webproject.dto.MovieDto;
import ee.taltech.iti0302.webproject.entities.Director;
import ee.taltech.iti0302.webproject.entities.Genre;
import ee.taltech.iti0302.webproject.entities.Movie;
import ee.taltech.iti0302.webproject.mapper.DirectorMapper;
import ee.taltech.iti0302.webproject.mapper.DirectorMapperImpl;
import ee.taltech.iti0302.webproject.repository.DirectorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class DirectorServiceTest {
    @Mock
    private DirectorRepository directorRepository;
    @Spy
    private DirectorMapper directorMapper = new DirectorMapperImpl();
    @InjectMocks
    private DirectorService directorService;

    public static final Movie movie = Movie.builder().id(1).title("Wakanda")
            .overview("As the Wakandans strive to embrace their next chapter...")
            .releaseDate(LocalDate.now()).voteAverage(8.1).posterPath("/ps2oKfhY6DL3alynlSqY97gHSsg.jpg").build();
    public static final MovieDto movieDto = MovieDto.builder().id(1).title("Wakanda")
            .overview("As the Wakandans strive to embrace their next chapter...")
            .releaseDate(LocalDate.now()).voteAverage(8.1).posterPath("/ps2oKfhY6DL3alynlSqY97gHSsg.jpg").build();
    public static final Director director = Director.builder().id(1).name("Ryan Coogler").movies(Set.of(movie)).build();
    public static final DirectorDto directorDto = DirectorDto.builder().id(1).name("Ryan Coogler").directorMovies(Set.of("Wakanda")).build();


    @Test
    void findAll() {
        given(directorRepository.findAll()).willReturn(List.of(director));

        var result= directorService.findAll();

        then(directorMapper).should().toDto(director);
        then(directorRepository).should().findAll();
        assertEquals(List.of(directorDto), result);
    }
}