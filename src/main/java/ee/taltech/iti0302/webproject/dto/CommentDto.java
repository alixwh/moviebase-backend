package ee.taltech.iti0302.webproject.dto;

import lombok.Data;

@Data
public class CommentDto {
    private int id;
    private String description;
    private String accountList;
    private String moviesList;
}
