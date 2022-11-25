package ee.taltech.iti0302.webproject.mapper;

import ee.taltech.iti0302.webproject.dto.MovieDto;
import ee.taltech.iti0302.webproject.entities.Actor;
import ee.taltech.iti0302.webproject.entities.Director;
import ee.taltech.iti0302.webproject.entities.Genre;
import ee.taltech.iti0302.webproject.entities.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MovieMapper {
    @Mapping(source = "genres", target = "genresList")
    @Mapping(source = "actors", target = "actorsList")
    @Mapping(source = "directors", target = "directorsList")
    MovieDto toDto(Movie movie);

    // Genre to movie
    default String mapToGenreName(Genre genre) {
        return genre.getName();
    }

    // Actors to movie
    default int mapToActorName(Actor actor) {
        return actor.getId();
    }

    // Directors to movie
    default String mapToDirectorName(Director director) {
        return director.getName();
    }

    List<MovieDto> toDtoList(List<Movie> movies);

}
