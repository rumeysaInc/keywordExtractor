package projects.keywordextractor.request.concretes;

import lombok.Data;

import java.util.List;
@Data
public class FilterArticleRequest {
    private int userId;
    private List<String> categories;
    private boolean isPublic;
}
