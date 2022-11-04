package ee.taltech.iti0302.webproject.api.mappper;

import ee.taltech.iti0302.webproject.api.externaldto.DirectorExternalDto;
import ee.taltech.iti0302.webproject.entities.Director;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DirectorExternalMapper {
    Director directorExternalDtoToDirector(DirectorExternalDto directorExternalDto);
}
