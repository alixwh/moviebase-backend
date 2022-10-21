package ee.taltech.iti0302.webproject.mapper;

import ee.taltech.iti0302.webproject.dto.ActorDto;
import ee.taltech.iti0302.webproject.repository.Actor;
import ee.taltech.iti0302.webproject.repository.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ActorMapper {

    @Mapping(source = "movies", target = "actorMovies")
    ActorDto toDto(Actor actor);

    default String mapToMovieTitle(Movie movie) {
        return movie.getTitle();
    }

    List<ActorDto> toDtoList(List<Actor> actors);
}
