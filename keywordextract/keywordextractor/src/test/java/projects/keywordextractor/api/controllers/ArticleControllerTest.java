package projects.keywordextractor.api.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import projects.keywordextractor.business.abstracts.ArticleService;
import projects.keywordextractor.core.utilities.results.DataResult;
import projects.keywordextractor.core.utilities.results.Result;
import projects.keywordextractor.entities.concretes.Article;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ArticleControllerTest {

    @Mock
    private ArticleService articleService;

    @InjectMocks
    private ArticleController articleController;

    @Test
    public void testGetAll() {
        // Arrange
        List<Article> articles = new ArrayList<>();
        articles.add(new Article(1, 1, "Title 1", "Content 1", "Category 1", "Abstract 1", true, new ArrayList<>()));
        articles.add(new Article(2, 1, "Title 2", "Content 2", "Category 2", "Abstract 2", true, new ArrayList<>()));

        when(articleService.getAll()).thenReturn(new DataResult<>(articles, true, "Data listelendi"));

        // Act
        DataResult<List<Article>> result = articleController.getAll();

        // Assert
        assertEquals(articles.size(), result.getData().size());
        assertEquals("Data listelendi", result.getMessage());
        assertEquals(true, result.isSuccess());
        verify(articleService, times(1)).getAll();
    }

    @Test
    public void testAdd() {
        // Arrange
        Article article = new Article(1, 1, "Title", "Content", "Category", "Abstract", true, new ArrayList<>());

        when(articleService.add(article)).thenReturn(new Result(true, "Metin eklendi"));

        // Act
        Result result = articleController.add(article);

        // Assert
        assertEquals("Metin eklendi", result.getMessage());
        assertEquals(true, result.isSuccess());
        verify(articleService, times(1)).add(article);
    }

    @Test
    public void testDelete() {
        // Arrange
        int id = 1;

        when(articleService.delete(id)).thenReturn(new Result(true, "Metin silindi"));

        // Act
        Result result = articleController.delete(id);

        // Assert
        assertEquals("Metin silindi", result.getMessage());
        assertEquals(true, result.isSuccess());
        verify(articleService, times(1)).delete(id);
    }
}

