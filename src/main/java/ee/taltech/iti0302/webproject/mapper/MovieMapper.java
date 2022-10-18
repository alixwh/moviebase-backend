package ee.taltech.iti0302.webproject.mapper;

import ee.taltech.iti0302.webproject.dto.MovieDto;
import ee.taltech.iti0302.webproject.repository.Genre;
import ee.taltech.iti0302.webproject.repository.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MovieMapper {
    @Mapping(source = "genres", target = "genresList")
    MovieDto toDto(Movie movie);

    default String mapToGenreName(Genre genre) {
        return genre.getName();
    }

    List<MovieDto> toDtoList(List<Movie> movies);
}
