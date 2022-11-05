package ee.taltech.iti0302.webproject.api.externallistdto;

import ee.taltech.iti0302.webproject.api.externaldto.GenreExternalDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
@RequiredArgsConstructor
public class GenreListExternalDto {
    private Set<GenreExternalDto> genres;
}
