package ee.taltech.iti0302.webproject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDto {
    private int id;
    private String description;
    private String account;
    private String movie;
}
