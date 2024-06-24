package projects.keywordextractor.api.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest;
import projects.keywordextractor.business.abstracts.ArticleListService;
import projects.keywordextractor.core.utilities.results.DataResult;
import projects.keywordextractor.entities.concretes.Article;
import projects.keywordextractor.dto.FilterArticleRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ArticleListControllerTest {

    @Mock
    private ArticleListService articleListService;

    @InjectMocks
    private ArticleListController articleListController;

    @Test
    public void testListAllArticles_Success() {
        // Arrange
        FilterArticleRequest filter = new FilterArticleRequest();
        List<Article> articles = new ArrayList<>();
        articles.add(new Article());
        articles.add(new Article());

        DataResult<List<Article>> dataResult = new DataResult<>(articles, true, "Success");
        when(articleListService.listAllArticles(filter)).thenReturn(dataResult);

        // Act
        ResponseEntity<List<Article>> responseEntity = articleListController.listAllArticles(filter);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(articles, responseEntity.getBody());
    }
}
