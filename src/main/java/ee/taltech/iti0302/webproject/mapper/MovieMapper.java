package ee.taltech.iti0302.webproject.mapper;

import ee.taltech.iti0302.webproject.dto.MovieDto;
import ee.taltech.iti0302.webproject.repository.Actor;
import ee.taltech.iti0302.webproject.repository.Director;
import ee.taltech.iti0302.webproject.repository.Genre;
import ee.taltech.iti0302.webproject.repository.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MovieMapper {
    // Genre to movie
    @Mapping(source = "genres", target = "genresList")
    MovieDto toDto(Movie movie);
    default String mapToGenreName(Genre genre) {
        return genre.getName();
    }
    // Actors to movie
    @Mapping(source = "actors", target = "actorsList")
    default String mapToActorName(Actor actor) {
        return actor.getName();
    }
    // Directors to movie
    @Mapping(source = "directors", target = "directorsList")
    default String mapToDirectorName(Director director) {
        return director.getName();
    }
    List<MovieDto> toDtoList(List<Movie> movies);// Genre to movie
}
