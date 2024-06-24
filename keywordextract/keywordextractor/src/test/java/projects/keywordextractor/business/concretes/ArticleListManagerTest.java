package projects.keywordextractor.business.concretes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import projects.keywordextractor.dataAccess.abstracts.ArticleDao;
import projects.keywordextractor.dto.FilterArticleRequest;
import projects.keywordextractor.entities.concretes.Article;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ArticleListManagerTest {

    @Mock
    private ArticleDao articleDao;

    @InjectMocks
    private ArticleListManager articleListManager;

    @Test
    public void testListAllArticles_Success() {
        // Arrange
        FilterArticleRequest filter = new FilterArticleRequest();
        filter.setUserId(1);
        filter.setPublic(true);

        List<Article> articles = new ArrayList<>();
        articles.add(new Article());

        when(articleDao.findAll()).thenReturn(articles);

        // Act
        List<Article> result = articleListManager.listAllArticles(filter).getData();

        // Assert
        assertEquals(articles, result);
    }
}

