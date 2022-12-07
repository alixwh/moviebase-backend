package ee.taltech.iti0302.webproject.dto;

import lombok.Builder;
import lombok.Data;
import java.util.Set;

@Data
@Builder
public class GenreDto {
    private int id;
    private String name;
    private Set<String> moviesList;
}
