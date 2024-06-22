package projects.keywordextractor.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projects.keywordextractor.business.abstracts.ArticleListService;
import projects.keywordextractor.core.utilities.results.DataResult;
import projects.keywordextractor.core.utilities.results.SuccessDataResult;
import projects.keywordextractor.dataAccess.abstracts.ArticleDao;
import projects.keywordextractor.entities.concretes.Article;
import projects.keywordextractor.request.concretes.FilterArticleRequest;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ArticleListManager implements ArticleListService {
    private ArticleDao articleDao;

    @Autowired
    public ArticleListManager(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }
    @Override
    public DataResult<List<Article>> listAllArticles(FilterArticleRequest filter) {
        List<Article> articles = this.articleDao.findAll().stream()
                .filter(article -> article.getUserId() == filter.getUserId())
                .filter(article -> filter.getCategories().contains(article.getCategory()))
                .filter(article -> article.isPublic() == filter.isPublic())
                .collect(Collectors.toList());
        return new SuccessDataResult<List<Article>>(articles, "Filtrelenmiş makaleler listelendi");
    }
}
