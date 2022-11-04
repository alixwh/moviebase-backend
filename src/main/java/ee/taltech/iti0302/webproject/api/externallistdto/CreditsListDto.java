package ee.taltech.iti0302.webproject.api.externallistdto;

import ee.taltech.iti0302.webproject.api.externaldto.ActorExternalDto;
import ee.taltech.iti0302.webproject.api.externaldto.DirectorExternalDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter @Setter
@RequiredArgsConstructor
public class CreditsListDto {
    private List<ActorExternalDto> cast;
    private Set<DirectorExternalDto> crew;
}
