package projects.keywordextractor.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projects.keywordextractor.business.abstracts.ArticleListService;
import projects.keywordextractor.core.utilities.results.DataResult;
import projects.keywordextractor.entities.concretes.Article;
import projects.keywordextractor.request.concretes.FilterArticleRequest;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleListController {
    private projects.keywordextractor.business.abstracts.ArticleListService ArticleListService;

    @Autowired
    public ArticleListController(ArticleListService ArticleListService) {
        this.ArticleListService = ArticleListService;
    }
    @PostMapping("/listAllArticles")
    public ResponseEntity<List<Article>> listAllArticles(@RequestBody FilterArticleRequest filter) {
        DataResult<List<Article>> result = ArticleListService.listAllArticles(filter);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getData());
        } else {
            return ResponseEntity.status(500).body(null);
        }
    }
}
