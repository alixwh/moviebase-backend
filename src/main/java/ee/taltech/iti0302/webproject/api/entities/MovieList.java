package ee.taltech.iti0302.webproject.api.entities;

import ee.taltech.iti0302.webproject.entities.Movie;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
@RequiredArgsConstructor
public class MovieList {
    private Set<Movie> results;
}
