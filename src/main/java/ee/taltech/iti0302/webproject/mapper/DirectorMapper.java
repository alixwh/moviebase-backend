package ee.taltech.iti0302.webproject.mapper;

import ee.taltech.iti0302.webproject.dto.DirectorDto;
import ee.taltech.iti0302.webproject.repository.Director;
import ee.taltech.iti0302.webproject.repository.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DirectorMapper {

    @Mapping(source = "movies", target = "directorMovies")
    DirectorDto toDto(Director director);

    default String mapToMovieTitle(Movie movie) {
        return movie.getTitle();
    }

    List<DirectorDto> toDtoList(List<Director> all);
}
