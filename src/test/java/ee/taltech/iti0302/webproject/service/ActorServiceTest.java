package ee.taltech.iti0302.webproject.service;

import ee.taltech.iti0302.webproject.dto.ActorDto;
import ee.taltech.iti0302.webproject.dto.MovieDto;
import ee.taltech.iti0302.webproject.entities.Actor;
import ee.taltech.iti0302.webproject.entities.Movie;
import ee.taltech.iti0302.webproject.mapper.ActorMapper;
import ee.taltech.iti0302.webproject.mapper.ActorMapperImpl;
import ee.taltech.iti0302.webproject.mapper.MovieMapper;
import ee.taltech.iti0302.webproject.mapper.MovieMapperImpl;
import ee.taltech.iti0302.webproject.repository.ActorRepository;
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
class ActorServiceTest {
    @Mock
    private ActorRepository actorRepository;
    @Spy
    private ActorMapper actorMapper = new ActorMapperImpl();
    @Spy
    private MovieMapper movieMapper = new MovieMapperImpl();
    @InjectMocks
    private ActorService actorService;

    public static final Movie movie = Movie.builder().id(1).title("Wakanda")
            .overview("As the Wakandans strive to embrace their next chapter...")
            .releaseDate(LocalDate.now()).voteAverage(8.1).posterPath("/ps2oKfhY6DL3alynlSqY97gHSsg.jpg").build();
    public static final MovieDto movieDto = MovieDto.builder().id(1).title("Wakanda")
            .overview("As the Wakandans strive to embrace their next chapter...")
            .releaseDate(LocalDate.now()).voteAverage(8.1).posterPath("/ps2oKfhY6DL3alynlSqY97gHSsg.jpg").build();
    public static final Actor actor = Actor.builder().id(1).name("Letitia Wright").movies(List.of(movie)).build();
    public static final ActorDto actorDto = ActorDto.builder().id(1).name("Letitia Wright").actorMovies(Set.of(1)).build();

    @Test
    void findById() {
        given(actorRepository.findById(1)).willReturn(Optional.of(actor));

        var result= actorService.findById(1);

        then(actorMapper).should().toDto(actor);
        then(actorRepository).should().findById(1);
        assertEquals(actorDto, result);
    }

    @Test
    void findAll() {
        given(actorRepository.findAll()).willReturn(List.of(actor));

        var result= actorService.findAll();

        then(actorMapper).should().toDto(actor);
        then(actorRepository).should().findAll();
        assertEquals(List.of(actorDto), result);
    }

    @Test
    void findMoviesByActorId() {
        given(actorRepository.findById(1)).willReturn(Optional.of(actor));

        var result= actorService.findMoviesByActorId(1);

        then(actorRepository).should().findById(1);
        then(movieMapper).should().toDtoList(List.of(movie));
        assertEquals(List.of(movieDto), result);
    }

    @Test
    void findMoviesByActorId_NoSuchActor_ShouldReturnEmptyList() {
        given(actorRepository.findById(2)).willReturn(Optional.empty());

        var result= actorService.findMoviesByActorId(2);

        then(actorRepository).should().findById(2);
        assertEquals(new ArrayList<>(), result);
    }
}