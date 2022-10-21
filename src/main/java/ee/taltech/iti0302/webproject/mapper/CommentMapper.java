package ee.taltech.iti0302.webproject.mapper;

import ee.taltech.iti0302.webproject.dto.CommentDto;
import ee.taltech.iti0302.webproject.repository.Comment;
import ee.taltech.iti0302.webproject.repository.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {
    @Mappings({
            @Mapping(source = "movie.title", target = "movie"),
            @Mapping(source = "account.username", target = "account")})
    CommentDto toDto(Comment comment);

    default String mapToMovieTitle(Movie movie) {
        return movie.getTitle();
    }

    List<CommentDto> toDtoList(List<Comment> comments);

}