package projects.keywordextractor.business.abstracts;

import projects.keywordextractor.core.utilities.results.DataResult;
import projects.keywordextractor.entities.concretes.Article;
import projects.keywordextractor.request.concretes.FilterArticleRequest;

import java.util.List;

public interface ArticleListService {
    DataResult<List<Article>> listAllArticles(FilterArticleRequest filter);
}
