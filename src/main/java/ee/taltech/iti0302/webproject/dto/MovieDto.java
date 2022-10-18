package ee.taltech.iti0302.webproject.dto;


import java.util.Set;

public class MovieDto {
    private int id;
    private String title;
    private String description;
    private int year;
    private Set<String> genresList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Set<String> getGenresList() {
        return genresList;
    }

    public void setGenresList(Set<String> genresList) {
        this.genresList = genresList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
