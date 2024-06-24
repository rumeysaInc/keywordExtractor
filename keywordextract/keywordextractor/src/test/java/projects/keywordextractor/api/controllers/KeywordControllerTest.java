package projects.keywordextractor.api.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import projects.keywordextractor.business.abstracts.ArticleService;
import projects.keywordextractor.business.abstracts.ExtractKeywordService;
import projects.keywordextractor.dto.ArticleRequest;
import projects.keywordextractor.entities.concretes.Article;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class KeywordControllerTest {

    @Mock
    private ExtractKeywordService extractKeywordService;

    @Mock
    private ArticleService articleService;

    @InjectMocks
    private KeywordController keywordController;

    @Test
    public void testExtractKeywords_Success() {
        // Arrange
        ArticleRequest articleRequest = new ArticleRequest();
        articleRequest.setTitle("Test Article");
        articleRequest.setCategory("Test Category");
        articleRequest.setText("This is a test article text.");
        articleRequest.setUserId(1);

        List<String> expectedKeywords = Arrays.asList("test", "article");

        when(extractKeywordService.extractKeywords(articleRequest)).thenReturn(expectedKeywords);

        // Act
        ResponseEntity<List<String>> response = keywordController.extractKeywords(articleRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedKeywords, response.getBody());

        // Verify
        verify(articleService, times(1)).add(any(Article.class));
    }

    @Test
    public void testExtractKeywords_Exception() {
        // Arrange
        ArticleRequest articleRequest = new ArticleRequest();

        when(extractKeywordService.extractKeywords(articleRequest)).thenThrow(new RuntimeException());

        // Act
        ResponseEntity<List<String>> response = keywordController.extractKeywords(articleRequest);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(0, response.getBody().size()); // Empty list when an exception occurs

        // Verify
        verify(articleService, never()).add(any(Article.class));
    }
}
