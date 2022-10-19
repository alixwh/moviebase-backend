package ee.taltech.iti0302.webproject.dto;

import java.util.Set;

public class GenreDto {
    private int id;
    private String name;
    private Set<String> moviesList;

    public int getId() {
        return id;
    }

    public Set<String> getMoviesList() {
        return moviesList;
    }

    public void setMoviesList(Set<String> moviesList) {
        this.moviesList = moviesList;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
