package projects.keywordextractor.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projects.keywordextractor.business.abstracts.ArticleService;
import projects.keywordextractor.business.abstracts.ExtractKeywordService;
import projects.keywordextractor.entities.concretes.Article;
import projects.keywordextractor.request.concretes.ArticleRequest;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class KeywordController {
    private ExtractKeywordService extractKeywordService;
    private ArticleService articleService;

    @Autowired
    public KeywordController(ExtractKeywordService extractKeywordService, ArticleService articleService) {
        this.extractKeywordService = extractKeywordService;
        this.articleService = articleService;
    }


    @PostMapping("/extractKeywords")
    public ResponseEntity<List<String>> extractKeywords(@RequestBody ArticleRequest articleRequest) {
        try {

            List<String> keywords = extractKeywordService.extractKeywords(articleRequest);
            Article article = new Article();
            article.setKeywords(keywords);
            article.setTitle(articleRequest.getTitle());
            article.setCategory(articleRequest.getCategory());
            article.setContent(articleRequest.getText());
            article.setUserId(articleRequest.getUserId());
            articleService.add(article);  //burası değişebilir

            return ResponseEntity.ok(keywords);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }
}
