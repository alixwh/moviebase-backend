package ee.taltech.iti0302.webproject.api.mappper;

import ee.taltech.iti0302.webproject.api.externaldto.MovieExternalDto;
import ee.taltech.iti0302.webproject.entities.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MovieExternalMapper {
    Movie movieExternalDtoToMovie(MovieExternalDto movieExternalDto);
}
