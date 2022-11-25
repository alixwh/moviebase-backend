package ee.taltech.iti0302.webproject.dto;

import lombok.Data;
import java.util.Set;

@Data
public class ActorDto {
    private int id;
    private String name;
    private Set<String> actorMovies;
    private String profilePath;
}
