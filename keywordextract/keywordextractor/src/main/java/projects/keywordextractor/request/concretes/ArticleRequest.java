package projects.keywordextractor.request.concretes;

import lombok.Data;

@Data
public class ArticleRequest {
    private String title;
    private String category;
    private String text;
    private int userId;
}
