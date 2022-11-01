package ee.taltech.iti0302.webproject.dto;


import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class MovieDto {
    private int id;
    private String title;
    private String overview;
    private LocalDate release_date;
    private double vote_average;
    private Set<String> genresList;
    private Set<String> actorsList;
    private Set<String> directorsList;

}
