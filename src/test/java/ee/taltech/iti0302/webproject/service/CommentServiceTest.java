package ee.taltech.iti0302.webproject.service;

import ee.taltech.iti0302.webproject.account.AccountRole;
import ee.taltech.iti0302.webproject.dto.CommentDto;
import ee.taltech.iti0302.webproject.entities.Account;
import ee.taltech.iti0302.webproject.entities.Comment;
import ee.taltech.iti0302.webproject.entities.Movie;
import ee.taltech.iti0302.webproject.mapper.CommentMapper;
import ee.taltech.iti0302.webproject.mapper.CommentMapperImpl;
import ee.taltech.iti0302.webproject.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {
    @Mock
    private CommentRepository commentRepository;
    @Spy
    private CommentMapper commentMapper = new CommentMapperImpl();
    @InjectMocks
    private CommentService commentService;

    public static final Movie movie = Movie.builder().id(1).title("Wakanda")
            .overview("As the Wakandans strive to embrace their next chapter...")
            .releaseDate(LocalDate.now()).voteAverage(8.1).posterPath("/ps2oKfhY6DL3alynlSqY97gHSsg.jpg").build();
    public static final Account account = Account.builder().id(1).username("username").password("password")
            .accountRole(AccountRole.ROLE_USER).build();
    public static final Comment comment = Comment.builder().id(1).description("cool").movie(movie)
            .account(account).build();
    public static final CommentDto commentDto = CommentDto.builder().id(1).description("cool").movie("Wakanda")
            .account("username").build();



    @Test
    void findById() {
        given(commentRepository.findById(1)).willReturn(Optional.of(comment));

        var result= commentService.findById(1);

        then(commentMapper).should().toDto(comment);
        then(commentRepository).should().findById(1);
        assertEquals(commentDto, result);
    }

    @Test
    void findById_NoSuchId_ShouldReturnNull() {
        given(commentRepository.findById(2)).willReturn(Optional.empty());

        var result= commentService.findById(2);

        then(commentMapper).should().toDto(null);
        then(commentRepository).should().findById(2);
        assertNull(result);
    }

    @Test
    void findAll() {
        given(commentRepository.findAll()).willReturn(List.of(comment));

        var result= commentService.findAll();

        then(commentMapper).should().toDtoList(List.of(comment));
        then(commentRepository).should().findAll();
        assertEquals(List.of(commentDto), result);
    }
}