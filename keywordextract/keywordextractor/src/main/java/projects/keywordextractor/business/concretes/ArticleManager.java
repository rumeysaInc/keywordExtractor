package projects.keywordextractor.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projects.keywordextractor.business.abstracts.ArticleService;
import projects.keywordextractor.core.utilities.results.DataResult;
import projects.keywordextractor.core.utilities.results.Result;
import projects.keywordextractor.core.utilities.results.SuccessDataResult;
import projects.keywordextractor.core.utilities.results.SuccessResult;
import projects.keywordextractor.dataAccess.abstracts.ArticleDao;
import projects.keywordextractor.entities.concretes.Article;

import java.util.List;

@Service
public class ArticleManager implements ArticleService {

    private ArticleDao articleDao;

    @Autowired //gidiyo projeyi tarıyor productdaoya karşılık gelen sınıf varsa onu yerleştiriyor
    public ArticleManager(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Override
    public DataResult<List<Article>> getAll() { //tüm verileri getirme operasyonu
        return new SuccessDataResult<List<Article>>(this.articleDao.findAll(),"data listelendi");
    }

    @Override
    public Result add(Article article) { //ekleme operasyonu
        this.articleDao.save(article);
        return new SuccessResult("Metin eklendi");
    }

    @Override
    public Result delete(int id) {
        this.articleDao.deleteById(id);
        return new SuccessResult("Metin silindi");
    }
}
