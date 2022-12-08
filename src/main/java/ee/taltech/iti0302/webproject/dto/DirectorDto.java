package ee.taltech.iti0302.webproject.dto;

import lombok.Builder;
import lombok.Data;
import java.util.Set;

@Data
@Builder
public class DirectorDto {
    private int id;
    private String name;
    private Set<String> directorMovies;
}
