package projects.keywordextractor.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import projects.keywordextractor.entities.concretes.Article;


public interface ArticleDao extends JpaRepository<Article,Integer> {
}
