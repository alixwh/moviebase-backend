package ee.taltech.iti0302.webproject.account;

import lombok.Data;

@Data
public class MovieListRequest {
    private int accountId;
    private int movieId;
    private String state;
}
