package ee.taltech.iti0302.webproject.dto;


import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class MovieDto {
    private int id;
    private String title;
    private String overview;
    private LocalDate releaseDate;
    private double voteAverage;
    private String posterPath;
    private Set<String> genresList;
    private List<Integer> actorsList;
    private Set<String> directorsList;
}
