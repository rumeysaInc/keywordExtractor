package projects.keywordextractor.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import projects.keywordextractor.business.abstracts.ArticleService;
import projects.keywordextractor.core.utilities.results.DataResult;
import projects.keywordextractor.core.utilities.results.Result;
import projects.keywordextractor.entities.concretes.Article;

import java.util.List;

@RestController  //dış arayüzler tarafından tanınmak için
@RequestMapping("/api/articles")
public class ArticleController {
    private ArticleService articleService;

    @Autowired  //arka tarafta productmanagerı newleyerek metodlarını kullanılabilir hale getiriyo
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/getall")  //tüm verileri getirme
    public DataResult<List<Article>> getAll(){
        return this.articleService.getAll();
    }




    //biz gönderdiğimiz için post
    @PostMapping("/add") ////ekleme operasyonu
    public Result add(@RequestBody Article text){
        return this.articleService.add(text);

    }

    @PostMapping("/delete/{id}") // silme operasyonu
    public Result delete(@PathVariable int id) {
        return this.articleService.delete(id);
    }


}
