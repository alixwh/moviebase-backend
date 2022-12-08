package ee.taltech.iti0302.webproject.dto;

import lombok.Builder;
import lombok.Data;
import java.util.Set;

@Data
@Builder
public class ActorDto {
    private int id;
    private String name;
    private Set<Integer> actorMovies;
    private String profilePath;
}
