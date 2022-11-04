package ee.taltech.iti0302.webproject.api.mappper;

import ee.taltech.iti0302.webproject.api.externaldto.ActorExternalDto;
import ee.taltech.iti0302.webproject.entities.Actor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ActorExternalMapper {
    Actor actorExternalDtoToActor(ActorExternalDto actorExternalDto);
}
