package projects.keywordextractor.business.abstracts;

import projects.keywordextractor.core.utilities.results.DataResult;
import projects.keywordextractor.core.utilities.results.Result;
import projects.keywordextractor.entities.concretes.Article;

import java.util.List;


public interface ArticleService {
    DataResult<List<Article>> getAll(); //tüm dataları getir



    Result add(Article article); ////ekleme operasyonu

    Result delete(int id); // silme operasyonu

}
