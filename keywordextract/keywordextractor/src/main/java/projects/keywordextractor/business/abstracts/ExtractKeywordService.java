package projects.keywordextractor.business.abstracts;

import projects.keywordextractor.request.concretes.ArticleRequest;

import java.util.List;

public interface ExtractKeywordService {
    List<String> extractKeywords(ArticleRequest articleRequest);
}
