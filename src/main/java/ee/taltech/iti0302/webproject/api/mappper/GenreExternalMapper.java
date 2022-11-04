package ee.taltech.iti0302.webproject.api.mappper;

import ee.taltech.iti0302.webproject.api.externaldto.GenreExternalDto;
import ee.taltech.iti0302.webproject.entities.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GenreExternalMapper {
    Genre genreExternalDtoToGenre(GenreExternalDto genreExternalDto);
}
