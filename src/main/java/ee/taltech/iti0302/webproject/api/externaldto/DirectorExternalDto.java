package ee.taltech.iti0302.webproject.api.externaldto;

import ee.taltech.iti0302.webproject.api.externallistdto.CrewMemberDto;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DirectorExternalDto extends CrewMemberDto {
    private int id;
    private String name;
}
