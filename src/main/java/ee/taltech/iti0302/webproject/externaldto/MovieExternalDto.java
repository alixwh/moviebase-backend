package ee.taltech.iti0302.webproject.externaldto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ee.taltech.iti0302.webproject.entities.Actor;
import ee.taltech.iti0302.webproject.entities.Comment;
import ee.taltech.iti0302.webproject.entities.Director;
import ee.taltech.iti0302.webproject.entities.Genre;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter @Setter
public class MovieExternalDto {
    private int id;
    private String title;
    private String overview;
    @JsonProperty("release_date")
    private LocalDate releaseDate;
    @JsonProperty("vote_average")
    private double voteAverage;
    @JsonProperty("genre_ids")
    private Set<Integer> genreIds;

}
