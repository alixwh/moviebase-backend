package ee.taltech.iti0302.webproject.mapper;

import ee.taltech.iti0302.webproject.dto.GenreDto;
import ee.taltech.iti0302.webproject.entities.Genre;
import ee.taltech.iti0302.webproject.entities.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GenreMapper {
    @Mapping(source = "movies", target = "moviesList")
    GenreDto toDto(Genre genre);

    default String mapToMovieTitle(Movie movie) {
        return movie.getTitle();
    }

    List<GenreDto> toDtoList(List<Genre> genres);

}
