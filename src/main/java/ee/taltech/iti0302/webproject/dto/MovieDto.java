package ee.taltech.iti0302.webproject.dto;


import lombok.Data;

import java.util.Set;

@Data
public class MovieDto {
    private int id;
    private String title;
    private String description;
    private int year;
    private double rating;
    private Set<String> genresList;
    private Set<String> actorsList;
    private Set<String> directorsList;

}
