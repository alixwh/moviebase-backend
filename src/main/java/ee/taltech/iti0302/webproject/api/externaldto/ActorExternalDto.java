package ee.taltech.iti0302.webproject.api.externaldto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ActorExternalDto {
    private int id;
    private String name;
    @JsonProperty("profile_path")
    private String profilePath;
}
