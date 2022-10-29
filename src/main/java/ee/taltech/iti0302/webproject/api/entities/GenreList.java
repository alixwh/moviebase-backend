package ee.taltech.iti0302.webproject.api.entities;

import ee.taltech.iti0302.webproject.entities.Genre;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
@RequiredArgsConstructor
public class GenreList {
    private Set<Genre> genres;
}
